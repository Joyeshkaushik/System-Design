import java.util.*;
interface INotification{
    String getContent();
}
class SimpleNotification implements INotification{
    String text;
    SimpleNotification(String text){
        this.text=text;
    }
    public String getContent(){
        return text;
    }
}
abstract  class INotificationDecorator implements INotification{
    protected INotification notification;
    public INotificationDecorator(INotification notification){
        this.notification=notification;
    }


}
 class TimeStampDecorator extends INotificationDecorator{
    TimeStampDecorator(INotification n){
        super(n);
    }
    public String getContent(){
        return "[2025-04-13 14:22:00] " + notification.getContent();
    }
 }
 class SignatureDecorator extends INotificationDecorator{
    private String Signature;
    SignatureDecorator(INotification n,String s){
        super(n);
        this.Signature=s;
    }
      public String getContent() {
        return notification.getContent() + "\n-- " + Signature + "\n\n";
    }
 }
 interface IObserver{
    void update();
 }
 interface Iobservable{
     void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
 }
 class NotificationObservable implements Iobservable{
    private List<IObserver> observers =new ArrayList<>();
    private INotification currentnotification=null;
    public void addObserver(IObserver observer){
        observers.add(observer);
    } 
    public void removeObserver(IObserver ob){
        observers.remove(ob);
    }
    public void notifyObservers(){
         for (IObserver obs : observers) {
            obs.update();
        }
    }
    public void setNotification(INotification notification){
        this.currentnotification=notification;
        notifyObservers();
    }
    public INotification getnotification(){
        return currentnotification;
    }
    public String getnotificationContent(){
        return currentnotification.getContent();
        }
 }
  class NotificationService {
    private NotificationObservable observable;
    private static NotificationService instance = null;
    private List<INotification> notifications = new ArrayList<>();

    private NotificationService() {
        observable = new NotificationObservable();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    // Expose the observable so observers can attach.
    public NotificationObservable getObservable() {
        return observable;
    }

    // Creates a new Notification and notifies observers.
    public void sendNotification(INotification notification) {
        notifications.add(notification);
        observable.setNotification(notification);
    }
}
class Logger implements IObserver {
    private NotificationObservable notificationObservable;

    public Logger() {
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public Logger(NotificationObservable observable) {
        notificationObservable.addObserver(this);
        this.notificationObservable = observable;
    }

    public void update() {
        System.out.println("Logging New Notification : \n" + notificationObservable.getnotificationContent());
    }
}
interface INotificationStrategy{
    void sendNotification(String content);
}
class EmailStrategy implements INotificationStrategy{
    private String emailID;
    EmailStrategy(String id){
        this.emailID=id;

    }

    public void sendNotification(String content){
         System.out.println("Sending email Notification to: " + emailID + "\n" + content);
    }
}
class SMSStrategy implements INotificationStrategy {
    private String mobileNumber;

    public SMSStrategy(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void sendNotification(String content) {
        // Simulate the process of sending an SMS notification, 
        // representing the dispatch of messages to users via SMS.​
        System.out.println("Sending SMS Notification to: " + mobileNumber + "\n" + content);
    }
}

class PopUpStrategy implements INotificationStrategy {
    public void sendNotification(String content) {
        // Simulate the process of sending popup notification.
        System.out.println("Sending Popup Notification: \n" + content);
    }
}
class NotificationEngine implements IObserver{
     private NotificationObservable notificationObservable;
    private List<INotificationStrategy> notificationStrategies = new ArrayList<>();

    public NotificationEngine() {
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public NotificationEngine(NotificationObservable observable) {
        this.notificationObservable = observable;
    }

    public void addNotificationStrategy(INotificationStrategy ns) {
        this.notificationStrategies.add(ns);
    }
    public void update() {
        String notificationContent = notificationObservable.getnotificationContent();
        for (INotificationStrategy strategy : notificationStrategies) {
            strategy.sendNotification(notificationContent);
        }
    }
}
public class NotificationSystem {
    public static void main(String[] args) {
        NotificationService notificationService=NotificationService.getInstance();
        Logger logger =new Logger();
        NotificationEngine notificationEngine=new NotificationEngine();
     notificationEngine.addNotificationStrategy(new EmailStrategy("random.person@gmail.com"));
        notificationEngine.addNotificationStrategy(new SMSStrategy("+91 9876543210"));
        notificationEngine.addNotificationStrategy(new PopUpStrategy());
         INotification notification = new SimpleNotification("Your order has been shipped!");
        notification = new TimeStampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");

        notificationService.sendNotification(notification);
    }
}

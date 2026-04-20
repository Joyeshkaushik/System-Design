abstract class Moneyhandler{
    protected Moneyhandler nextHandler;
    public Moneyhandler(){
        this.nextHandler=null;
    }
    public void setNextHandler(Moneyhandler next){
        this.nextHandler=next;
    }
    public abstract void dispense(int amount);

}
class ThousandHandler extends Moneyhandler{
    private int numNotes;
    public ThousandHandler(int amt){
        this.numNotes=amt;
    }
    public void dispense(int amount){
        int notesneeded=amount/1000;
        if (notesneeded>numNotes){
            notesneeded=numNotes;
            numNotes=0;
        }
        else{
            numNotes-=notesneeded;
        }
        if(notesneeded>0){
             System.out.println("Dispensing " + notesneeded + " x ₹1000 notes.");
        }
        int remainingamt=amount-(notesneeded*1000);
        if (remainingamt > 0) {
            if (nextHandler != null) nextHandler.dispense(remainingamt);
            else {
                System.out.println("Remaining amount of " + remainingamt + " cannot be fulfilled (Insufficinet fund in ATM)");
            }
        }
    }
}
class FiveHundredHandler extends Moneyhandler {
    private int numNotes;

    public FiveHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 500;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0)
            System.out.println("Dispensing " + notesNeeded + " x ₹500 notes.");

        int remainingAmount = amount - (notesNeeded * 500);
        if (remainingAmount > 0) {
            if (nextHandler != null) nextHandler.dispense(remainingAmount);
            else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficinet fund in ATM)");
            }
        }
    }
}
class TwoHundredHandler extends Moneyhandler {
    private int numNotes;

    public TwoHundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 200;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0)
            System.out.println("Dispensing " + notesNeeded + " x ₹200 notes.");

        int remainingAmount = amount - (notesNeeded * 200);
        if (remainingAmount > 0) {
            if (nextHandler != null) nextHandler.dispense(remainingAmount);
            else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficinet fund in ATM)");
            }
        }
    }
}
class HundredHandler extends Moneyhandler {
    private int numNotes;

    public HundredHandler(int numNotes) {
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount) {
        int notesNeeded = amount / 100;

        if (notesNeeded > numNotes) {
            notesNeeded = numNotes;
            numNotes = 0;
        } else {
            numNotes -= notesNeeded;
        }

        if (notesNeeded > 0)
            System.out.println("Dispensing " + notesNeeded + " x ₹100 notes.");

        int remainingAmount = amount - (notesNeeded * 100);
        if (remainingAmount > 0) {
            if (nextHandler != null) nextHandler.dispense(remainingAmount);
            else {
                System.out.println("Remaining amount of " + remainingAmount + " cannot be fulfilled (Insufficinet fund in ATM)");
            }
        }
    }
}
public class CORDesignpattern {
     public static void main(String[] args) {
        Moneyhandler thousandHandler = new ThousandHandler(3);
        Moneyhandler fiveHundredHandler = new FiveHundredHandler(5);
        Moneyhandler twoHundredHandler = new TwoHundredHandler(10);
        Moneyhandler hundredHandler = new HundredHandler(20);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(twoHundredHandler);
        twoHundredHandler.setNextHandler(hundredHandler);

        int amountToWithdraw = 4000;

        System.out.println("\nDispensing amount: ₹" + amountToWithdraw);
        thousandHandler.dispense(amountToWithdraw);
    }
}

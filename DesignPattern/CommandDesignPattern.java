import java.util.*;
interface Command{
    void execute();
    void undo();
}
class Light {
    public void on()  {
        System.out.println("Light is ON");
    }
    public void off() {
        System.out.println("Light is OFF");
    }
}

class Fan {
    public void on()  {
        System.out.println("Fan is ON");
    }
    public void off() {
        System.out.println("Fan is OFF");
    }
}
class LightCommand implements Command {
    private Light light;

    public LightCommand(Light l) {
        this.light = l;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
class FanCommand implements Command {
    private Fan fan;

    public FanCommand(Fan f) {
        this.fan = f;
    }

    public void execute() {
        fan.on();
    }

    public void undo() {
        fan.off();
    }
}class RemoteController{
    private static final int numbutton=4;
    private Command[] buttons;
    private boolean[] buttonpressed;
    RemoteController(){
        buttons=new Command[numbutton];
        buttonpressed=new boolean[numbutton];
         for (int i = 0; i < numbutton; i++) {
            buttons[i] = null;
            buttonpressed[i] = false;  // false = off, true = on
        }
    }
    
    public void setCommand(int idx, Command cmd) {
        if (idx >= 0 && idx < numbutton) {
            buttons[idx] = cmd;
            buttonpressed[idx] = false;
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < numbutton && buttons[idx] != null) {
            if (!buttonpressed[idx]) {
                buttons[idx].execute();
            } else {
                buttons[idx].undo();
            }
            buttonpressed[idx] = !buttonpressed[idx];
        } else {
            System.out.println("No command assigned at button " + idx);
        }
    }
}
public class CommandDesignPattern{
    public static void main(String[] args) {
         Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        RemoteController remote = new RemoteController();

        remote.setCommand(0, new LightCommand(livingRoomLight));
        remote.setCommand(1, new FanCommand(ceilingFan));

        // Simulate button presses (toggle behavior)
        System.out.println("--- Toggling Light Button 0 ---");
        remote.pressButton(0);  // ON
        remote.pressButton(0);  // OFF

        System.out.println("--- Toggling Fan Button 1 ---");
        remote.pressButton(1);  // ON
        remote.pressButton(1);  // OFF

        // Press unassigned button to show default message
        System.out.println("--- Pressing Unassigned Button 2 ---");
        remote.pressButton(2);
    }
}
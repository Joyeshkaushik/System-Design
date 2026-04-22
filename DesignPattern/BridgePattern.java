interface Engine{
    void start();
}
class PetrolEngine implements Engine{
    public void start() {
        System.out.println("Petrol engine starting with ignition!");
    }
}
class DieselEngine implements Engine {
    
    public void start() {
        System.out.println("Diesel engine roaring to life!");
    }
}

class ElectricEngine implements Engine {
    
    public void start() {
        System.out.println("Electric engine powering up silently!");
    }
}
abstract class Car{
     protected Engine engine;
    public Car(Engine e) {
        this.engine = e;
    }
    public abstract void drive();
}
class Sedan extends Car {
    public Sedan(Engine e) {
        super(e);
    }

    
    public void drive() {
        engine.start();
        System.out.println("Driving a Sedan on the highway.");
    }
}

// Refined Abstraction: SUV
class SUV extends Car {
    public SUV(Engine e) {
        super(e);
    }

    
    public void drive() {
        engine.start();
        System.out.println("Driving an SUV off-road.");
    }
}

public class BridgePattern {
    public static void main(String[] args) {
         Engine petrolEng = new PetrolEngine();
        Engine dieselEng = new DieselEngine();
        Engine electricEng = new ElectricEngine();

    
        Car mySedan = new Sedan(petrolEng);
        Car mySUV = new SUV(electricEng);
        Car yourSUV = new SUV(dieselEng);


        mySedan.drive();   
        mySUV.drive();     
        yourSUV.drive();  
    }
}

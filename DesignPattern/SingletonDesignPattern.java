public class SingletonDesignPattern {

    private static volatile SingletonDesignPattern instance = null;

    private SingletonDesignPattern() {
        System.out.println("Singleton Constructor Called!");
    }

    // Double check locking
    public static SingletonDesignPattern getInstance() {
        if (instance == null) { // First check
            synchronized (SingletonDesignPattern.class) {
                if (instance == null) { // Second check
                    instance = new SingletonDesignPattern();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonDesignPattern s1 = SingletonDesignPattern.getInstance();
        SingletonDesignPattern s2 = SingletonDesignPattern.getInstance();

        System.out.println(s1 == s2); // true
    }
}
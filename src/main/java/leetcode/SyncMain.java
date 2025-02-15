package leetcode;

// deadlock practice
public class SyncMain {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            System.out.println("Поток 1: Удержание (lock1)");
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Ждем lock2 (method1)");
            synchronized (lock2) {
                System.out.println("Поток 1: удержание потока 1 и 2...");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            System.out.println("Поток 2: Удержание (lock1)");
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Ждем lock 2 (method2)");
            synchronized (lock1) {
                System.out.println("Поток 2: удержание потока 1 и 2");
            }
        }
    }

    public static void main(String[] args) {
        SyncMain syncMain = new SyncMain();
        Thread t1 = new Thread(syncMain::method1);
        Thread t2 = new Thread(syncMain::method2);
        t1.start();
        t2.start();
    }
}

package Lab5.Deadlock;

/**
 * Взаимная блокировка.
 */
public class Deadlock implements Runnable {
    A a = new A();
    B b = new B();

    Deadlock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Соперничающий поток");
        t.start();

        // Блокируем a из главного потока.
        a.foo(b);
        System.out.println("Назад в главный поток");
    }

    public void run() {
        // Блокируем b другого потока.
        b.bar(a);
        System.out.println("Назад в другой поток");
    }

    public static void main(String[] args) {
        new Deadlock();
    }
}

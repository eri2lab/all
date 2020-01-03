package Lab1;

import Common.ThreadWorkImitator;

/**
 * Поток исполнения, реализующий интерфейс Runnable.
 */
public class NewThreadRunnable implements Runnable {
    Thread t;

    public NewThreadRunnable() {
        t = new Thread(this);
        System.out.println("Дочерний поток создан: " + t);
        t.start();
    }

    @Override
    public void run() {
        ThreadWorkImitator.run("Дочерний поток", 500);
    }
}

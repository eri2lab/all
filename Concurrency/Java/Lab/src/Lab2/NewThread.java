package Lab2;

import Common.ThreadWorkImitator;

public class NewThread implements Runnable {
    public String name;
    public Thread t;

    public NewThread(String name) {
        this.name = name;
        t = new Thread(this);
        System.out.println("Новый поток создан: " + t);
        t.start();
    }

    @Override
    public void run() {
        ThreadWorkImitator.run(name, 1000);
    }
}

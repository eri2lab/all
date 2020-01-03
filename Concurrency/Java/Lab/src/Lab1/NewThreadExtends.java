package Lab1;

import Common.ThreadWorkImitator;

public class NewThreadExtends extends Thread {
    public NewThreadExtends() {
        super("Демонстрационный поток");
        System.out.println("Дочерний поток создан: " + this);
        start();
    }
    public void run() {
        ThreadWorkImitator.run("Дочерний поток: ", 500);
    }
}

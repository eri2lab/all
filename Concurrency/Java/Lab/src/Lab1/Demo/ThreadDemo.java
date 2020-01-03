package Lab1.Demo;

import Common.ThreadWorkImitator;
import Lab1.NewThreadRunnable;

public class ThreadDemo {
    public static void main(String[] args) {
        new NewThreadRunnable();
        ThreadWorkImitator.run("Главный поток", 1000);
    }
}

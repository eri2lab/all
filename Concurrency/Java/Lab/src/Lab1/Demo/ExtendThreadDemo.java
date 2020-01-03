package Lab1.Demo;

import Common.ThreadWorkImitator;
import Lab1.NewThreadExtends;

public class ExtendThreadDemo {
    public static void main(String[] args) {
        new NewThreadExtends();
        ThreadWorkImitator.run("Главный поток", 1000);
    }
}

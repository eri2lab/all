package Lab1.Demo;

import Common.ThreadWorkImitator;

/**
 * Демонстрация управления главным потоком исполнения.
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println(String.format("Имя текущего потока: %s", t.getName()));

        t.setName("Название потока.");
        System.out.println(String.format("Имя потока после изменения: %s", t.getName()));

        ThreadWorkImitator.run("Главный поток исполнения", 1000);
    }
}

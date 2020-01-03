package Lab2.Demo;

import Lab2.NewThread;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Лабораторная работа №2.
 */
public class DemoJoin {
    /**
     * Входная точка.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Количество потоков: ");
        int threadCount = scanner.nextInt();

        System.out.print("Приоритет: ");
        int priority = scanner.nextInt();

        // Создаем n потоков.
        List<NewThread> threads = IntStream
                .range(0, threadCount)
                .mapToObj(x -> new NewThread("Thread-" + x))
                .collect(Collectors.toList());

        // Изменяем приоритет для двух потоков
        // и выводим информацию о активности потока.
        setPriority(priority, 2, threads);
        printAliveInfo(threads);

        // Ожидаем потоки.
        try {
            for (NewThread thread : threads) {
                thread.t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван");
        }

        // Выводим информацию о том, что потоки завершены.
        printAliveInfo(threads);
    }

    /**
     * Задать нескольким случайным элементам списка (с учетом уникальности)
     * потоков указанный приоритет.
     *
     * @param priority Приоритет.
     * @param count    Количество элементов.
     * @param threads  Список потоков.
     */
    private static void setPriority(int priority, int count, List<NewThread> threads) {
        ThreadLocalRandom
                .current()
                .ints(0, threads.size())
                .distinct()
                .limit(count)
                .forEach(x -> {
                    NewThread thread = threads.get(x);
                    thread.t.setPriority(priority);
                    System.out.println(String.format("Потоку %s задан приоритет %d.",
                            thread.name, priority));
                });
    }

    /**
     * Вывести информацию о активности каждого потока.
     *
     * @param newThreads Список потоков.
     */
    private static void printAliveInfo(List<NewThread> newThreads) {
        for (NewThread t : newThreads) {
            System.out.println(String.format("Поток %s запущен: %s",
                    t.name, t.t.isAlive()));
        }
    }
}

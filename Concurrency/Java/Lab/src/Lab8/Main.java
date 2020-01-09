package Lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Входная точка.
 */
public class Main {
    /**
     * Количество потоков.
     */
    private static final int THREADS_COUNT = 4;

    /**
     * Входная точка.
     *
     * @param args Параметры запуска.
     */
    public static void main(String[] args) {
        benchmark(0, 5000, 10000);
        benchmark(0, 5000, 50000);
        benchmark(0, 5000, 100000);
        benchmark(0, 5000, 200000);
        benchmark(0, 5000, 500000);
        benchmark(0, 5000, 1000000);
        benchmark(0, 5000, 2000000);
        benchmark(0, 5000, 5000000);
    }

    /**
     * Замерить время выполнения сортировки с использованием регулярного набора образцов и быстрой сортировки.
     * @param minValue Минимальное значение элемента списка.
     * @param maxValue Максимальное значение элемента списка.
     * @param listSize Количество элементов списка.
     */
    public static void benchmark(int minValue, int maxValue, int listSize) {
        System.out.printf("Размер списка: %d, минимальное значение: %d, максимальное значение: %d\n",
                listSize, minValue, maxValue);
        List<Integer> unsorted = generateRandomList(minValue, maxValue, listSize);
        List<Integer> unsortedClone = cloneList(unsorted);

        AtomicReference<List<Integer>> PSRSTemp = new AtomicReference<>();
        long msPSRS = timer(() -> {
            PSRSTemp.set(PSRS.sort(THREADS_COUNT, unsorted));
        }, TimeUnit.MILLISECONDS);
        List<Integer> PSRSResult = PSRSTemp.get();

        long msQS = timer(() -> {
            QuickSort.quickSort(unsortedClone, 0, unsortedClone.size() - 1);
        }, TimeUnit.MILLISECONDS);

        System.out.printf("Время выполнения PSRS: %d.\n", msPSRS);
        System.out.printf("Время выполнения быстрой сортировки: %d\n", msQS);

        System.out.println("Сортировка выполнена корректно для обоих алгоритмов: "
                + (isOrdered(PSRSResult) && isOrdered(unsortedClone)) + "\n");
    }

    /**
     * Установить, является ли список отсортированным.
     *
     * @param list Список.
     * @return Отсортирован ли список.
     */
    private static boolean isOrdered(List<Integer> list) {
        int prev = list.get(0);
        for (int val : list) {
            if (val < prev)
                return false;
            prev = val;
        }
        return true;
    }

    /**
     * Сгенерировать список случайных чисел.
     *
     * @param min   Минимальное значение числа.
     * @param max   Максимальное значение числа.
     * @param count Количество чисел.
     * @return Список случайных чисел.
     */
    private static List<Integer> generateRandomList(int min, int max, int count) {
        return ThreadLocalRandom
                .current()
                .ints(min, max)
                .limit(count)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }


    /**
     * Глубокое копирование списка чисел.
     *
     * @param list Список.
     * @return Копию списка.
     */
    private static List<Integer> cloneList(List<Integer> list) {
        List<Integer> clone = new ArrayList<Integer>(list.size());
        for (Integer item : list)
            //noinspection UnnecessaryUnboxing
            clone.add(item.intValue());
        return clone;
    }

    /**
     * Засечь время выполнения метода.
     *
     * @param method   Метод.
     * @param timeUnit Единица измерения.
     * @return Время выполнения метода.
     */
    @SuppressWarnings("SameParameterValue")
    private static long timer(Runnable method, TimeUnit timeUnit) {
        long time = System.nanoTime();
        method.run();
        time = System.nanoTime() - time;
        return timeUnit.convert(time, TimeUnit.NANOSECONDS);
    }
}

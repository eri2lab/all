package Lab8;

import java.util.Collections;
import java.util.List;

/**
 * Быстрая сортировка.
 */
public class QuickSort {
    /**
     * Быстрая сортировка.
     * @param list Список, который нужно отсортировать.
     * @param begin Нижняя граница сортировки.
     * @param end Верхняя граница сортировки.
     */
    public static void quickSort(List<Integer> list, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end);

            quickSort(list, begin, partitionIndex-1);
            quickSort(list, partitionIndex+1, end);
        }
    }

    /**
     * Разделить на две части и обменяться элементами соответственно.
     * @param list Список.
     * @param begin Нижняя граница.
     * @param end Верхняя граница.
     * @return Центральная точка.
     */
    private static int partition(List<Integer> list, int begin, int end) {
        int pivot = list.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (list.get(j) <= pivot) {
                i++;

                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i+1, end);
        return i+1;
    }
}

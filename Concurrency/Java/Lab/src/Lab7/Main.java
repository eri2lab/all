package Lab7;

/**
 * Входная точка.
 */
public class Main {
    /**
     * Входная точка.
     * @param args Параметры запуска.
     */
    public static void main(String[] args) {
        double[][] a = {
                {1, 3, 2},
                {2, 7, 5},
                {1, 4, 6}};
        double[] b = {1., 18., 26.};
        printSLE(a, b, "Базовая");

        EquationSystem system = new EquationSystem(a, b);
        system.GaussParallel();
        printSLE(a, b, "Треугольная");

        System.out.println("Ответ:");
        printVector(system.result, true);
    }

    /**
     * Вывести СЛУ.
     * @param a Матрица коэффициентов.
     * @param b Матрица решений.
     * @param name Наименование матрицы.
     */
    public static void printSLE(double[][] a, double[] b, String name) {
        System.out.printf("%s матрица:\n", name);
        printMatrix(a);
        System.out.println("\nВектор решений:");
        printVector(b, false);
        System.out.println("\n");
    }

    /**
     * Вывести двумерный массив в виде матрицы.
     * @param matrix Двумерный массив.
     */
    public static void printMatrix(double[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%.1f  ", matrix[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Вывести массив.
     * @param vector Массив.
     * @param includeX Включать ли x0, x1... в вывод.
     */
    public static void printVector(double[] vector, boolean includeX) {
        for (int i = 0; i < vector.length; i++) {
            if (includeX)
                System.out.printf("x%d: %.1f\n", i, vector[i]);
            else
                System.out.printf("%.1f  ", vector[i]);
        }
    }
}

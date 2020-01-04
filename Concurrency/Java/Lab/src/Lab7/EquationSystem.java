package Lab7;

/**
 * Система уравнений.
 */
public class EquationSystem {
    /**
     * Матрица коэффициентов.
     */
    double[][] a;

    /**
     * Вектор решений.
     */
    double[] b;

    /**
     * Результат.
     */
    double[] result;

    /**
     * Потоки, в которых выполняются преобразования над строками матрицы.
     */
    private Thread[] threads;

    /**
     * Количество неизвестных.
     */
    int size;

    /**
     * Конструктор.
     * @param a Матрица коэффициентов.
     * @param b Вектор решений.
     */
    public EquationSystem(double[][] a, double[] b) {
        this.a = a;
        this.b = b;
        size = a[0].length;
        threads = new Thread[size];
        result = new double[size];
    }

    /**
     * Решить СЛУ методом Гаусса в параллельном режиме.
     */
    public void GaussParallel() {
        // Итерация по каждой строке.
        for (int row = 0; row < size; row++) {
            // Делаем, чтобы на диагонали были только единицы.
            double value = a[row][row];
            for (int col = row + 1; col < size; col++) {
                a[row][col] /= value;
            }

            b[row] /= value;
            a[row][row] = 1.0;

            // Вычитаем текущую строку из следующих строк в параллельных потоках.
            for (int innerRow = row + 1; innerRow < size; innerRow++) {
                threads[innerRow] = new SubtractionThread(this, innerRow, row);
                threads[innerRow].start();
            }

            // Ожидаем выполнение потоков вычитания.
            for (int innerRow = row + 1; innerRow < size; innerRow++) {
                try {
                    threads[innerRow].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Обратный ход.
        for (int back = size - 1; back >= 0; back--) {
            result[back] = b[back];
            for (int i = back - 1; i >= 0; i--) {
                b[i] -= result[back] * a[i][back];
            }
        }
    }
}

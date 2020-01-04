package Lab7;

/**
 * Поток вычитания одной строки СЛУ из другой.
 */
public class SubtractionThread extends Thread {
    /**
     * Система уравнений.
     */
    private EquationSystem system;

    /**
     * Строка из которой вычитать.
     */
    private int innerRow;

    /**
     * Строка которую вычитать.
     */
    private int row;

    /**
     * Конструктор.
     * @param system Система уравнений.
     * @param innerRow Строка из которой вычитать.
     * @param row Строка которую вычитать.
     */
    public SubtractionThread(EquationSystem system, int innerRow, int row) {
        this.system = system;
        this.innerRow = innerRow;
        this.row = row;
    }

    /**
     * Выполнение вычитания.
     */
    @Override
    public void run() {
        double innerValue = system.a[innerRow][row];
        for (int innerCol = row + 1; innerCol < system.size; innerCol++) {
            system.a[innerRow][innerCol] -= innerValue * system.a[row][innerCol];
        }
        system.b[innerRow] -= system.a[innerRow][row] * system.b[row];
        system.a[innerRow][row] = 0.0;
    }
}

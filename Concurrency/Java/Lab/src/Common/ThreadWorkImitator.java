package Common;

/**
 * Имитация работы потока.
 */
public class ThreadWorkImitator {
    /**
     * Имитация работы потока.
     * Производит обратный отсчет с выводом в консоль
     * раз в {@code time} миллисекунд.
     *
     * @param identifier Наименование потока при выводе.
     * @param time       Задержка между итерациями отсчета.
     */
    public static void run(String identifier, int time) {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(identifier + ": " + i);
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            System.out.println(identifier + " прерван.");
        }
        System.out.println(identifier + " завершен.");
    }
}

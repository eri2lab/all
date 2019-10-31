package Lab4;

/**
 * Сихронизированная очередь.
 */
public class Queue {
    /**
     * Текущий элемент очереди
     */
    private char n;

    /**
     * Присвоено ли элементу следующее значение.
     */
    private boolean valueSet = false;

    /**
     * Получить следующий элемент очереди.
     *
     * @return Значение элемента.
     */
    synchronized char get() {
        while (!valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        System.out.println("Получено: " + n);
        valueSet = false;
        notify();
        return n;
    }

    /**
     * Добавляет элемент в очередь.
     *
     * @param n Значение элемента.
     */
    synchronized void put(char n) {
        while (valueSet)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        this.n = n;
        valueSet = true;
        System.out.println("Отправлено: " + n);
        notify();
    }
}

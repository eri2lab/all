package Lab6;

/**
 * Поток, выводящий информацию о состоянии
 * другого потока указанное число раз.
 */
public class StateInfoThread implements Runnable {
    /**
     * Поток.
     */
    private final Thread thread;
    /**
     * Поток, состояние которого нужно выводить.
     */
    private Thread watchedThread;
    /**
     * Количество раз вывода состояния.
     */
    private int count;

    /**
     * Задержка между выводами состояния.
     */
    private int delay;

    /**
     * Конструктор.
     *
     * @param watchedThread Поток, состояние которого нужно выводить.
     * @param count         Сколько раз вывести состояние.
     * @param delay         Задержка между выводами.
     */
    StateInfoThread(Thread watchedThread, int count, int delay) {
        this.watchedThread = watchedThread;
        this.count = count;
        this.delay = delay;
        thread = new Thread(this);
    }

    /**
     * Создание и запуск потока.
     *
     * @param thread
     * @param count
     * @param delay
     * @return Поток.
     */
    static StateInfoThread createAndStart(Thread thread, int count, int delay) {
        StateInfoThread t = new StateInfoThread(thread, count, delay);
        t.thread.start();
        return t;
    }

    /**
     * Вывод состояния рассматриваемого потока.
     */
    @Override
    public void run() {
        for (; count > 0; count--) {
            System.out.println("Состояние потока: " + watchedThread.getState());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

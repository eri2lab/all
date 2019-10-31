package Lab4;

/**
 * Потребитель очереди.
 */
public class Consumer implements Runnable {
    /**
     * Очередь.
     */
    private Queue queue;

    /**
     * Конструктор.
     *
     * @param queue Очередь.
     */
    public Consumer(Queue queue) {
        this.queue = queue;
        new Thread(this, "Потребитель")
                .start();
    }

    /**
     * Получение элементов очереди в вечном цикле.
     */
    @Override
    public void run() {
        while (true) {
            queue.get();
        }
    }
}

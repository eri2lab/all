package Lab4;

import java.io.FileReader;

/**
 * Источник данных очереди.
 */
public class Producer implements Runnable {
    /**
     * Очередь.
     */
    Queue queue;

    /**
     * Конструктор.
     *
     * @param queue Очередь.
     */
    public Producer(Queue queue) {
        this.queue = queue;
        new Thread(this, "Поставщик")
                .start();
    }

    /**
     * Чтение текстовой информации из файла и добавление в очередь побуквенно.
     */
    @Override
    public void run() {
        FileReader inputStream;
        try {
            inputStream = new FileReader("src/Lab3/Demo/input.txt");
            int c;
            while ((c = inputStream.read()) != -1)
                queue.put((char) c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

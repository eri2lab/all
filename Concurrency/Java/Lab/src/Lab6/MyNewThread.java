package Lab6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Поток вывода информации из текстового файла.
 */
public class MyNewThread implements Runnable {
    /**
     * Наименование потока.
     */
    private final String name;
    /**
     * Поток.
     */
    Thread thread;
    /**
     * Флаг приостановки.
     */
    private boolean suspendFlag;

    /**
     * Конструктор.
     *
     * @param name Наименование потока.
     */
    MyNewThread(String name) {
        this.name = name;
        thread = new Thread(this, name);
        System.out.println("Новый поток: " + thread);
        suspendFlag = false;
    }

    /**
     * Создание и запуск потока.
     *
     * @param name Наименование потока.
     * @return Поток.
     */
    static MyNewThread createAndStart(String name) {
        MyNewThread thread = new MyNewThread(name);
        thread.thread.start();
        return thread;
    }

    /**
     * Тело потока. Считывает информацию
     * из файла и выводит в консоль.
     */
    @Override
    public void run() {
        try {
            File file = new File("src/Lab3/Demo/input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                System.out.println(name + ": " + st);
                Thread.sleep(1000);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Приостановка выполнения потока.
     */
    synchronized void suspend() {
        suspendFlag = true;
    }

    /**
     * Возобновление выполнения потока.
     */
    synchronized void resume() {
        suspendFlag = false;
        notify();
    }
}

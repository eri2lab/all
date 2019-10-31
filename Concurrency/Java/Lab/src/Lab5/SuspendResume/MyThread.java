package Lab5.SuspendResume;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MyThread implements Runnable {
    /**
     * Наименование потока.
     */
    private final String name;
    /**
     * Флаг ожидания.
     */
    private boolean suspendFlag;

    /**
     * Конструктор.
     *
     * @param name Наименование потока.
     */
    MyThread(String name) {
        this.name = name;
        Thread thread = new Thread(this, name);
        System.out.println("Новый поток: " + thread);
        suspendFlag = false;
        thread.start();
    }

    /**
     * Тело потока.
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
     * Приостановить выполнение потока.
     */
    synchronized void suspend() {
        suspendFlag = true;
    }

    /**
     * Возобновить выполнение потока.
     */
    synchronized void resume() {
        suspendFlag = false;
        notify();
    }
}

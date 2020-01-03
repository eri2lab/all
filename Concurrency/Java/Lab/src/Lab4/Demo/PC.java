package Lab4.Demo;

import Lab4.Consumer;
import Lab4.Producer;
import Lab4.Queue;

/**
 * Входная точка.
 */
public class PC {
    public static void main(String args[]) {
        Queue q = new Queue();
        new Producer(q);
        new Consumer(q);

        System.out.println("Для остановки нажмите Ctrl + C");
    }
}

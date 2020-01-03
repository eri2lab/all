package Lab3;

public class Caller implements Runnable {
    public Thread t;
    String message;
    CallMe target;

    public Caller(CallMe target, String message) {
        this.target = target;
        this.message = message;
        t = new Thread(this);
        t.start();
    }

    /**
     * Вызывает метод call цели без синхронизации.
     */
    public void run() {
        target.call(message);
    }
}

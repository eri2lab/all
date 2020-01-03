package Lab3;

public class SynchronizedCaller extends Caller {
    public SynchronizedCaller(CallMe target, String message) {
        super(target, message);
    }

    /**
     * Вызывает метод call цели с синхронизацией.
     */
    @Override
    public void run() {
        synchronized (target) {
            target.call(message);
        }
    }
}

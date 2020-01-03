package Lab5.SuspendResume;


public class Demo {
    public static void main(String[] args) throws Exception{
        MyThread t1 = new MyThread("Первый поток");
        MyThread t2 = new MyThread("Второй поток");

        Thread.sleep(1500);
        t1.suspend();
        Thread.sleep(4000);
        t1.resume();
        t2.suspend();
        Thread.sleep(4000);
        t2.resume();
    }
}

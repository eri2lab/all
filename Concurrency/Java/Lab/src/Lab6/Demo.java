package Lab6;


public class Demo {
    public static void main(String[] args) throws Exception {
        MyNewThread t1 = MyNewThread.createAndStart("Первый поток");
        MyNewThread t2 = MyNewThread.createAndStart("Второй поток");
        StateInfoThread.createAndStart(t1.thread, 12, 1000);
    }
}

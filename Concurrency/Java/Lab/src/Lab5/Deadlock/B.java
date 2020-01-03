package Lab5.Deadlock;

class B {
    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод B.foo()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "пытается вызвать метод a.last()");
        // Вызов метода заблокированного объекта.
        a.last();
    }

    synchronized void last() {
        System.out.println("В методе B.last()");
    }
}

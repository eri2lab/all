package Lab5.Deadlock;

class A {
    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод A.foo()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "пытается вызвать метод B.last()");
        // Вызов метода заблокированного объекта.
        b.last();
    }

    synchronized void last() {
        System.out.println("В методе A.last()");
    }
}

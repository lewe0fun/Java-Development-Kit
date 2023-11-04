package seminar5.task1;

public class DeadLock {
    public static void main(String[] args) {
        Object objectA = new Object();
        Object objectB= new Object();
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" hold A");

            synchronized (objectA) {
                try {
                    Thread.sleep(400);// for deadlock
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+" hold B");
                synchronized (objectB){
                    System.out.println(Thread.currentThread().getName()+" hold A & B");
                }
            }
            System.out.println(Thread.currentThread().getName()+" release");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" hold B");
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName()+" hold A");
                synchronized (objectA){
                    System.out.println(Thread.currentThread().getName()+" hold A & B");
                }
            }
            System.out.println(Thread.currentThread().getName()+" release");
        });
        thread1.start();
        thread2.start();
    }
}

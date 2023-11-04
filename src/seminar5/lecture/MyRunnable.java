package seminar5.lecture;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("hello from 2: "+Thread.currentThread());
    }
}

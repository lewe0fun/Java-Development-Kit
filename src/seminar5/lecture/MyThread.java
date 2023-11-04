package seminar5.lecture;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.print("hello from 1: "+Thread.currentThread());
    }
}

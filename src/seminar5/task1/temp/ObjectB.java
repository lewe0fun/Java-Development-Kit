package seminar5.task1.temp;

public class ObjectB implements Runnable{
    @Override
    public void run() {
        System.out.println("running B");
    }
    public void sleep(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

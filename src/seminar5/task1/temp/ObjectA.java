package seminar5.task1.temp;

public class ObjectA extends Thread{
    @Override
    public void run() {
        System.out.println("running A");
    }
    public void sleep(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

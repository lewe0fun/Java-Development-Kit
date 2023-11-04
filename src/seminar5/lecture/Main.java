package seminar5.lecture;

public class Main {
    public static void main(String[] args) {
        //System.out.println(Thread.currentThread().getName());

/*        for (int i = 0; i < 3; i++) {
            new MyThread().start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new MyRunnable()).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println("hello from 3: "+Thread.currentThread());
            }).start();
        }*/
        Thread tic =new Thread(new TikTak("["));
        Thread tac =new Thread(new TikTak("]"));
        tac.start();
        tic.start();
    }
}

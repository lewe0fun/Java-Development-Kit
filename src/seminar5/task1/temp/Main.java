package seminar5.task1.temp;

/**
 * Создать два класс ObjectA, ObjectB
 * Реализовать класс в котором два потока вызовут DeadLock
 */
public class Main {
    public static void main(String[] args) {
        Thread tr1 =new ObjectA();
        Thread tr2 =new Thread(new ObjectB());

        tr1.start();
        tr2.start();
    }

}

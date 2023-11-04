package seminar5.lecture;

import java.util.Scanner;

public class StatsHelper {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                count++;
            }
        });
        readThread.setDaemon(true);
        readThread.start();
        while (true) {
            System.out.println("Введено " + count);
            Thread.sleep(300);
        }
    }
}

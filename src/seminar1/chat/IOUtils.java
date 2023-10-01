package seminar1.chat;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

;

public class IOUtils {
    public static void writeToFile(String path, String txt) {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.append(txt);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static StringBuilder readFromFile(String path) {
        StringBuilder sb = null;
        try (FileReader reader = new FileReader(path)) {
            int c;
            sb = new StringBuilder();
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return sb;
    }
}

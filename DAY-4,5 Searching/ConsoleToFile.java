import java.io.*;

public class ConsoleToFile {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            FileWriter fw = new FileWriter("output.txt");
            String line;
            while (!(line = br.readLine()).equalsIgnoreCase("exit")) {
                fw.write(line + System.lineSeparator());
            }
            fw.close();
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

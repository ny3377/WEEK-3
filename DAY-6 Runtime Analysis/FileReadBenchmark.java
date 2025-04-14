import java.io.*;

public class FileReadBenchmark {

    public static void main(String[] args) throws IOException {
        String filePath = "sample_500MB.txt"; // Use any file for testing

        System.out.println("Reading file: " + filePath);
        System.out.println();

        long fileReaderTime = readWithFileReader(filePath);
        long inputStreamReaderTime = readWithInputStreamReader(filePath);

        System.out.println("FileReader Time: " + fileReaderTime + "ms");
        System.out.println("InputStreamReader Time: " + inputStreamReaderTime + "ms");
    }

    // Reading using FileReader
    public static long readWithFileReader(String filePath) {
        long start = System.nanoTime();
        try (FileReader reader = new FileReader(filePath)) {
            while (reader.read() != -1) {
                // Do nothing (consume char)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }

    // Reading using InputStreamReader
    public static long readWithInputStreamReader(String filePath) {
        long start = System.nanoTime();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath))) {
            while (reader.read() != -1) {
                // Do nothing (consume char)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }
}

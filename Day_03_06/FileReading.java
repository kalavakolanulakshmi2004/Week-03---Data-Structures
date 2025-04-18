import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
public class FileReading {
    public static void main(String[] args) throws IOException {
        generateFile("1MB.txt", 1 * 1024 * 1024);
        generateFile("100MB.txt", 100 * 1024 * 1024);
        generateFile("500MB.txt", 500 * 1024 * 1024);
    }
    public static void generateFile(String fileName, int sizeInBytes) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        Random random = new Random();
        int bytesWritten = 0;

        while (bytesWritten < sizeInBytes) {
            random.nextBytes(buffer);
            int bytesToWrite = Math.min(buffer.length, sizeInBytes - bytesWritten);
            fos.write(buffer, 0, bytesToWrite);
            bytesWritten += bytesToWrite;
        }
        fos.close();
        System.out.println(fileName + " created with size: " + sizeInBytes / (1024 * 1024) + " MB");
    }
}
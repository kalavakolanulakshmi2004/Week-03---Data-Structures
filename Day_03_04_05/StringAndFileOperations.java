import java.io.*;
import java.util.*;
public class StringAndFileOperations {
    public static void main(String[] args) {
        String str = "hello";
        int repetitions = 1000000;
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            sb.append(str);
        }
        long endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        startTime = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < repetitions; i++) {
            sf.append(str);
        }
        endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;
        System.out.println("StringBuilder time: " + stringBuilderTime);
        System.out.println("StringBuffer time: " + stringBufferTime);
        try {
            FileReader fr = new FileReader("src/input.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int wordCount = 0;
            startTime = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            endTime = System.nanoTime();
            long fileReadTime = endTime - startTime;
            System.out.println("Word count: " + wordCount);
            System.out.println("File reading time: " + fileReadTime);
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
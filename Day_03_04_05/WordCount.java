import java.io.*;
public class WordCount {
    public static void main(String[] args) {
        String fileName = "src/input.txt";
        String targetWord = "Hello";
        int count = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        count++;
                    }
                }
            }
            bufferedReader.close();
            System.out.println("The word '" + targetWord + "' appears " + count + " times in the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
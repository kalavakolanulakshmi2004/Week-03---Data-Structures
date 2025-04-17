import java.util.*;
public class Map {
    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        boolean[] used = new boolean[words.length];
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (!used[i]) {
                List<String> group = new ArrayList<>();
                group.add(words[i]);
                used[i] = true;
                String[] letters = words[i].split("");
                Arrays.sort(letters);
                String key = String.join("", letters);
                for (int j = i + 1; j < words.length; j++) {
                    if (!used[j]) {
                        String[] tempLetters = words[j].split("");
                        Arrays.sort(tempLetters);
                        String tempKey = String.join("", tempLetters);
                        if (key.equals(tempKey)) {
                            group.add(words[j]);
                            used[j] = true;
                        }
                    }
                }
                result.add(group);
            }
        }
        for (List<String> group : result) {
            System.out.print(group);
        }
    }
}
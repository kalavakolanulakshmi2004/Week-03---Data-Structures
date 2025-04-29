import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class SearchComparison {
    public static int linearSearch(List<Integer> data, int target) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == target) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(List<Integer> data, int target) {
        int low = 0, high = data.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = data.get(mid);
            if (midVal == target) {
                return mid;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        List<Integer> data1K = generateSortedList(1000);
        List<Integer> data10K = generateSortedList(10000);
        List<Integer> data1M = generateSortedList(1000000);
        int target1K = data1K.get(new Random().nextInt(1000));
        int target10K = data10K.get(new Random().nextInt(10000));
        int target1M = data1M.get(new Random().nextInt(1000000));
        System.out.println("Dataset Size: 1,000");
        measureSearchTime("Linear Search", data1K, target1K);
        measureSearchTime("Binary Search", data1K, target1K);
        System.out.println("\nDataset Size: 10,000");
        measureSearchTime("Linear Search", data10K, target10K);
        measureSearchTime("Binary Search", data10K, target10K);
        System.out.println("\nDataset Size: 1,000,000");
        measureSearchTime("Linear Search", data1M, target1M);
        measureSearchTime("Binary Search", data1M, target1M);
    }
    public static List<Integer> generateSortedList(int size) {
        List<Integer> data = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(size * 10));
        }
        Collections.sort(data);
        return data;
    }
    public static void measureSearchTime(String searchType, List<Integer>
            data, int target) {
        long startTime = System.nanoTime();
        int result = -1;
        if (searchType.equals("Linear Search")) {
            result = linearSearch(data, target);
        } else if (searchType.equals("Binary Search")) {
            result = binarySearch(data, target);
        }
        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1_000_000;
        System.out.println(searchType + ": Target found at index " + result
                + ", Time taken: " + durationMillis + " ms");
    }
}
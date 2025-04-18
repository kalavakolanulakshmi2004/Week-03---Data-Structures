import java.util.Arrays;
import java.util.Random;
public class SortingComparison {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
    public static void main(String[] args) {
        compareSorting(1000);
        compareSorting(10000);
        compareSorting(1000000);
    }
    public static void compareSorting(int size) {
        int[] data = generateRandomArray(size);
        int[] arrBubble = Arrays.copyOf(data, data.length);
        int[] arrMerge = Arrays.copyOf(data, data.length);
        int[] arrQuick = Arrays.copyOf(data, data.length);
        System.out.println("\n--- Dataset Size: " + size + " ---");
        if (size <= 10000) {
            measureSortingTime("Bubble Sort", arrBubble);
        } else {
            System.out.println("Bubble Sort: Skipped due to large dataset size.");
        }
        measureSortingTime("Merge Sort", arrMerge);
        measureSortingTime("Quick Sort", arrQuick);
    }
    public static void measureSortingTime(String sortType, int[] arr) {
        long startTime = System.nanoTime();
        if (sortType.equals("Bubble Sort")) {
            bubbleSort(arr);
        } else if (sortType.equals("Merge Sort")) {
            mergeSort(arr, 0, arr.length - 1);
        } else if (sortType.equals("Quick Sort")) {
            quickSort(arr, 0, arr.length - 1);
        }
        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1e9;
        System.out.println(sortType + ": Time taken = " + String.format("%.6f", durationSeconds) + " seconds");
    }
    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
        }
        return arr;
    }
}
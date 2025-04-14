import java.util.Arrays;
import java.util.Random;

public class SearchComparison {

    public static void main(String[] args) {
        int[] datasetSizes = {1_000, 10_000, 1_000_000};
        int target = -1; // Ensure worst case by searching for an absent value

        System.out.printf("%-15s %-25s %-25s%n", "Dataset Size", "Linear Search Time", "Binary Search Time");
        

        for (int size : datasetSizes) {
            int[] data = generateRandomArray(size);

            // Linear Search
            long startLinear = System.nanoTime();
            linearSearch(data, target);
            long endLinear = System.nanoTime();
            long linearTimeMs = (endLinear - startLinear) / 1_000_000;

            // Binary Search (data must be sorted first)
            Arrays.sort(data); // O(N log N)
            long startBinary = System.nanoTime();
            binarySearch(data, target);
            long endBinary = System.nanoTime();
            long binaryTimeMs = (endBinary - startBinary) / 1_000_000;

            System.out.printf("%-15d %-25d %-25d%n", size, linearTimeMs, binaryTimeMs);
        }
    }

    // Linear Search: O(N)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // Binary Search: O(log N)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Generate random integer array
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10); // Ensures enough spread
        }
        return arr;
    }
}


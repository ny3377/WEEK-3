import java.util.Arrays;
import java.util.Random;

public class SortingComparison {

    public static void main(String[] args) {
        int[] datasetSizes = {1_000, 10_000, 1_000_000};

        System.out.printf("%-15s %-25s %-25s %-25s%n", "Dataset Size", "Bubble Sort Time", "Merge Sort Time", "Quick Sort Time");

        for (int size : datasetSizes) {
            int[] original = generateRandomArray(size);

            long bubbleTime = -1;
            if (size <= 10_000) { // Avoid massive delay
                int[] bubbleArray = Arrays.copyOf(original, original.length);
                long start = System.nanoTime();
                bubbleSort(bubbleArray);
                long end = System.nanoTime();
                bubbleTime = (end - start) / 1_000_000;
            }

            // Merge Sort
            int[] mergeArray = Arrays.copyOf(original, original.length);
            long startMerge = System.nanoTime();
            mergeSort(mergeArray, 0, mergeArray.length - 1);
            long endMerge = System.nanoTime();
            long mergeTime = (endMerge - startMerge) / 1_000_000;

            // Quick Sort
            int[] quickArray = Arrays.copyOf(original, original.length);
            long startQuick = System.nanoTime();
            quickSort(quickArray, 0, quickArray.length - 1);
            long endQuick = System.nanoTime();
            long quickTime = (endQuick - startQuick) / 1_000_000;

            System.out.printf("%-15d %-25s %-25d %-25d%n", size,
                    (bubbleTime == -1 ? "Unfeasible" : bubbleTime + "ms"),
                    mergeTime + "ms", quickTime + "ms");
        }
    }

    // Bubble Sort - O(N^2)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Merge Sort - O(N log N)
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Quick Sort - O(N log N)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap pivot
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }
}


import java.util.Scanner;

public class HeapSort {

    public static void heapSort(int[] salaries) {
        int n = salaries.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(salaries, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = salaries[0];
            salaries[0] = salaries[i];
            salaries[i] = temp;

            heapify(salaries, i, 0);
        }
    }

    public static void heapify(int[] arr, int heapSize, int i) {
        int largest = i;         
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, heapSize, largest);
        }
    }

    public static void printArray(int[] arr) {
        for (int salary : arr) {
            System.out.print(salary + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of applicants: ");
        int n = scanner.nextInt();

        int[] salaries = new int[n];

        System.out.println("Enter expected salary demands:");
        for (int i = 0; i < n; i++) {
            salaries[i] = scanner.nextInt();
        }

        System.out.println("\nOriginal Salary Demands:");
        printArray(salaries);

        heapSort(salaries);

        System.out.println("\nSorted Salary Demands (Ascending Order):");
        printArray(salaries);

        scanner.close();
    }
}

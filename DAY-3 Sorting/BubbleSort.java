import java.util.*;

class BubbleSort {
    public static void bubbleSort(int[] marks) {
        int n = marks.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (marks[j] > marks[j + 1]) {
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int mark : arr) {
            System.out.print(mark + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] marks = {98, 47, 67, 29, 36, 47};
        bubbleSort(marks);
        System.out.println("Sorted Marks (Ascending Order):");
        printArray(marks);
    }
}

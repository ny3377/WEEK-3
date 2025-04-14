import java.util.Scanner;

public class CountingSort {

    public static void countingSort(int[] ages, int minAge, int maxAge) {
        int range = maxAge - minAge + 1;
        int[] count = new int[range];
        int[] output = new int[ages.length];

        for (int age : ages) {
            count[age - minAge]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = ages.length - 1; i >= 0; i--) {
            int age = ages[i];
            output[count[age - minAge] - 1] = age;
            count[age - minAge]--;
        }

        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void printArray(int[] arr) {
        for (int age : arr) {
            System.out.print(age + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        int[] ages = new int[n];

        System.out.println("Enter ages of " + n + " students (10 to 18):");
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
            if (ages[i] < 10 || ages[i] > 18) {
                System.out.println("Invalid age. Please enter a value between 10 and 18.");
                i--;
            }
        }

        System.out.println("\nOriginal Ages:");
        printArray(ages);

        countingSort(ages, 10, 18);

        System.out.println("\nSorted Ages (Ascending Order):");
        printArray(ages);

        scanner.close();
    }
}

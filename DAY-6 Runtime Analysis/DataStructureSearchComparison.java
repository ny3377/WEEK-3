import java.util.*;

public class DataStructureSearchComparison {

    public static void main(String[] args) {
        int[] sizes = {1_000, 100_000, 1_000_000};

        System.out.printf("%-12s %-20s %-20s %-20s%n", "Size", "Array Search (ms)", "HashSet Search (ms)", "TreeSet Search (ms)");
        System.out.println("----------------------------------------------------------------------");

        for (int size : sizes) {
            // Prepare data
            List<Integer> arrayList = new ArrayList<>();
            Set<Integer> hashSet = new HashSet<>();
            Set<Integer> treeSet = new TreeSet<>();

            for (int i = 0; i < size; i++) {
                arrayList.add(i);
                hashSet.add(i);
                treeSet.add(i);
            }

            int target = size - 1; // Worst-case for array

            // Time array search
            long t1 = System.nanoTime();
            boolean foundArray = arrayList.contains(target);
            long t2 = System.nanoTime();

            // Time HashSet search
            long t3 = System.nanoTime();
            boolean foundHash = hashSet.contains(target);
            long t4 = System.nanoTime();

            // Time TreeSet search
            long t5 = System.nanoTime();
            boolean foundTree = treeSet.contains(target);
            long t6 = System.nanoTime();

            System.out.printf("%-12d %-20.3f %-20.3f %-20.3f%n",
                    size,
                    (t2 - t1) / 1_000_000.0,
                    (t4 - t3) / 1_000_000.0,
                    (t6 - t5) / 1_000_000.0
            );
        }
    }
}


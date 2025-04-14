import java.util.HashMap;
import java.util.Map;

public class FibonacciComparison {

    // Naive Recursive: O(2^n)
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative: O(n)
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // Memoized Recursive: O(n)
    static Map<Integer, Integer> memo = new HashMap<>();
    public static int fibonacciMemoized(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        int value = fibonacciMemoized(n - 1) + fibonacciMemoized(n - 2);
        memo.put(n, value);
        return value;
    }

    // Timing Utility
    public static long timeExecution(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // ms
    }

    public static void main(String[] args) {
        int[] testCases = {10, 30, 50};

        System.out.printf("%-10s %-25s %-25s %-25s%n", "N", "Recursive (ms)", "Iterative (ms)", "Memoized (ms)");
        System.out.println("-------------------------------------------------------------------------------");

        for (int n : testCases) {
            long recTime, iterTime, memoTime;

            // Time Recursive (with overflow handling)
            try {
                recTime = timeExecution(() -> fibonacciRecursive(n));
                if (recTime > 60_000) {
                    System.out.printf("%-10d %-25s", n, "Unfeasible (>1min)");
                } else {
                    System.out.printf("%-10d %-25d", n, recTime);
                }
            } catch (StackOverflowError e) {
                System.out.printf("%-10d %-25s", n, "StackOverflow ❌");
            }

            // Time Iterative
            iterTime = timeExecution(() -> fibonacciIterative(n));
            System.out.printf("%-25d", iterTime);

            // Time Memoized
            memo.clear(); // Clear cache before run
            memoTime = timeExecution(() -> fibonacciMemoized(n));
            System.out.printf("%-25d%n", memoTime);
        }
    }
}

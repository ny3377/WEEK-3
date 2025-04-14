public class StringConcatBenchmark {

    public static void main(String[] args) {
        int[] operationCounts = {1_000, 10_000, 1_000_000};

        System.out.printf("%-20s %-20s %-25s %-25s%n", "Ops Count", "String (+)", "StringBuilder", "StringBuffer");
        System.out.println("-------------------------------------------------------------------------------");

        for (int count : operationCounts) {
            long stringTime = testStringConcat(count);
            long builderTime = testStringBuilderConcat(count);
            long bufferTime = testStringBufferConcat(count);

            System.out.printf("%-20d %-20s %-25d %-25d%n", count,
                    (stringTime == -1 ? "Unusable" : stringTime + "ms"),
                    builderTime, bufferTime);
        }
    }

    // Inefficient String concatenation
    public static long testStringConcat(int count) {
        try {
            long start = System.nanoTime();
            String result = "";
            for (int i = 0; i < count; i++) {
                result += "a";
            }
            long end = System.nanoTime();
            return (end - start) / 1_000_000;
        } catch (OutOfMemoryError | StackOverflowError e) {
            return -1; // Treat as "Unusable"
        }
    }

    // Efficient with StringBuilder
    public static long testStringBuilderConcat(int count) {
        long start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("a");
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }

    // Thread-safe with StringBuffer
    public static long testStringBufferConcat(int count) {
        long start = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append("a");
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }
}


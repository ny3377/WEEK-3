public class BufferVsBuilder {
    public static void main(String[] args) {
        int iterations = 1_000_000;

        long startBuffer = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append("hello");
        }
        long endBuffer = System.nanoTime();
        long durationBuffer = endBuffer - startBuffer;

        long startBuilder = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append("hello");
        }
        long endBuilder = System.nanoTime();
        long durationBuilder = endBuilder - startBuilder;

        System.out.println("StringBuffer time (ns): " + durationBuffer);
        System.out.println("StringBuilder time (ns): " + durationBuilder);
    }
}

public class StringBufferConcat {
    public static String concatenate(String[] words) {
        StringBuffer buffer = new StringBuffer();
        for (String word : words) {
            buffer.append(word);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String[] input = {"Java", "Is", "Fast"};
        String result = concatenate(input);
        System.out.println(result);
    }
}

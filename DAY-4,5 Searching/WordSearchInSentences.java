public class WordSearchInSentences {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.toLowerCase().contains(word.toLowerCase())) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
            "Java is powerful.",
            "Python is for scripting.",
            "Let's build with Java."
        };
        String result = findSentenceWithWord(sentences, "java");
        System.out.println(result);
    }
}


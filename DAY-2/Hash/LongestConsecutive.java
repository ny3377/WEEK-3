import java.util.*;

public class LongestConsecutive {
    public static int longestSequence(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int maxLen = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int len = 1;
                while (set.contains(num + len)) len++;
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestSequence(nums));
    }
}

import java.util.*;

public class PairWithTargetSum {
    public static boolean hasPair(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(target - num)) return true;
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {8, 4, 1, 6};
        int target = 10;
        System.out.println(hasPair(nums, target));
    }
}


import java.util.HashMap;
import java.util.Map;

public class LC560SubarraySumEqualsK {
    // method 1: O(n^2) and O(1) space
    public static int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum =  0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // method 2: hashmap, O(n) time and O(n) space
    // find how many pairs of <i, j> where i < j and prefixSum[j] - prefixSum[i] == k
    // key : prefix sum value
    // value : number of occurrence of the prefix sum
    public static int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum +=  nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // method 3: 如果都是正数，空间如何O(1)，这时候用two pointer
    public static int subarraySum3(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        int i = 0, sum = 0, res = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum > k) {
                sum -= nums[i++];
            }
            if (sum == k) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 1, 5, 3};
        System.out.println(subarraySum1(nums, 6));
        System.out.println(subarraySum2(nums, 6));
        System.out.println(subarraySum3(nums, 6));
    }
}

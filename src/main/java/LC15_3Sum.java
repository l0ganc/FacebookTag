import java.util.*;

public class LC15_3Sum {
    /**
     * time = O(N^2)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1;
            int high = nums.length - 1;
            int target = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }


    // hashmap without sorting
    public static List<List<Integer>> threeSum2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < nums.length; i ++) {
            map.put(nums[i], map.get(nums[i]) - 1);
            if (map.get(nums[i]) == 0)
                map.remove(nums[i]);
            for (int j = 0; j < i; j ++) {
                int tar = - nums[i] - nums[j];
                if (map.containsKey(tar)) {
                    List<Integer> tmp = Arrays.asList(nums[j], nums[i], tar);
                    Collections.sort(tmp);
                    ans.add(tmp);
                }
            }
        }
        List<List<Integer>> rtn = new ArrayList<>();
        rtn.addAll(ans);
        return rtn;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
        System.out.println(threeSum2(nums));
    }
}

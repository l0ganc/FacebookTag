import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
// Each number in candidates may only be used once in the combination.
//
// Note:
//
//
// All numbers (including target) will be positive integers.
// The solution set must not contain duplicate combinations.
//
//
// Example 1:
//
//
//Input: candidates = [10,1,2,7,6,1,5], target = 8,
//A solution set is:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
//
//
// Example 2:
//
//
//Input: candidates = [2,5,2,1,2], target = 5,
//A solution set is:
//[
//  [1,2,2],
//  [5]
//]
//
//
public class LC40CombinationSumII {
    // backtracking, time = O(2^n), space = O(n)
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, new ArrayList<>(), res, 0);
        return res;
    }

    private static void backtracking(int[] candidates, int target, List<Integer> cur, List<List<Integer>> res, int start) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                cur.add(candidates[i]);
                backtracking(candidates, target - candidates[i], cur, res, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(nums, target));
    }

}

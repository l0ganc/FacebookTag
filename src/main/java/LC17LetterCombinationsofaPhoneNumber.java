import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17LetterCombinationsofaPhoneNumber {
    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     *
     * Example:
     *
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     *
     *  Time complexity : O(3 ^ N * 4 ^ M)
     *         where N is the number of digits in the input that maps to 3 letters
     *         where M is the number of digits in the input that maps to 4 letters
     *
     *         Space complexity : O(3 ^ N * 4 ^ M)
     */
    public List<String> letterCombinations(String digits) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        helper(res, digits, 0, new StringBuilder(), map);
        return res;
    }

    private void helper(List<String> res, String digits, int index, StringBuilder sb, Map<Integer, String> map) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }

        int num = digits.charAt(index) - '0';
        String value = map.get(num);
        for (char c : value.toCharArray()) {
            sb.append(c);
            helper(res, digits, index + 1, sb, map);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        LC17LetterCombinationsofaPhoneNumber obj = new LC17LetterCombinationsofaPhoneNumber();
        System.out.println(obj.letterCombinations("23"));
    }

}

import java.util.*;

public class LC336PalindromePairs {
    /**
     首先用一个hashmao把所有的word都存一下，key是word，value是index
     然后遍历这个words，对其中的每个word，来一个for循环把word一分为二，
     分别判断其中的一半跟map中的是否能组成回文，可以的话就加入res

     time = O(n * k ^ 2) ; space = O(n)
     */

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 2) return res;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }


        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalind(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        res.add(Arrays.asList(map.get(str2rvs), i));
                    }
                }

                if (str2.length() != 0 && isPalind(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1) != i) {
                        res.add(Arrays.asList(i, map.get(str1rvs)));
                    }
                }
            }
        }
        return res;
    }

    private static boolean isPalind(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words1 = new String[]{"abcd","dcba","lls","s","sssll"};
        String[] words2 = new String[]{"bat","tab","cat"};
        System.out.println(palindromePairs(words1));
        System.out.println(palindromePairs(words2));
    }
}

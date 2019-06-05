package ProductionEngineer;

import java.util.HashSet;
import java.util.Set;

public class GoatLatin {

    public static String toGoatLatin(String S) {
        Set<Character> vowel = new HashSet<>();
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}) {
            vowel.add(c);
        }

        int t = 1;
        StringBuilder res = new StringBuilder();

        for (String word : S.split(" ")) {
            char first = word.charAt(0);
            if (vowel.contains(first)) {
                res.append(word);
            } else {
                res.append(word.substring(1));
                res.append(first);
            }
            res.append("ma");
            for (int i = 0; i < t; i++) {
                res.append('a');
            }
            res.append(' ');
        }
        return res.toString().trim();
    }
}

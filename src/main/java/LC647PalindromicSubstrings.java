public class LC647PalindromicSubstrings {
    static int count = 1;
    public static int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            checkPalind(s, i, i);       //To check the palindrome of odd length palindromic sub-string
            checkPalind(s, i, i + 1); //To check the palindrome of even length palindromic sub-string
        }
        return count;
    }

    private static void checkPalind(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {  //Check for the palindrome string
            i--;   //To trace string in left direction
            j++;   //To trace string in right direction
            count++;  //Increment the count if palindromin substring found
        }
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aba"));
    }
}

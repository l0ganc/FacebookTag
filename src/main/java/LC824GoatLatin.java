public class LC824GoatLatin {
    public static String toGoatLatin(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }

        StringBuilder sb = new StringBuilder();
        String[] arr = S.split("\\s+");

        for (int i = 0; i < arr.length; i++) {
            String tmp = arr[i];
            if (tmp.charAt(0) == 'a' || tmp.charAt(0) == 'e' || tmp.charAt(0) == 'i'
                    || tmp.charAt(0) == 'o' || tmp.charAt(0) == 'u' ||
                    tmp.charAt(0) == 'A' || tmp.charAt(0) == 'E' || tmp.charAt(0) == 'I'
                    || tmp.charAt(0) == 'O' || tmp.charAt(0) == 'U') {
                sb.append(tmp);
                sb.append("ma");
            } else {
                sb.append(tmp.substring(1)).append(tmp.charAt(0));
                sb.append("ma");
            }
            for (int k = 0; k <= i; k++) {
                sb.append("a");
            }
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
        System.out.println(toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }
}

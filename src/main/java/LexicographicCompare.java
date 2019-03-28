import java.util.Comparator;

public class LexicographicCompare implements Comparator<String> {
    /**
     * Lexographic compare (like words in an English dictionary)
     * between 2 strings with consecutive digits treated as a single element, return the "smaller" string
     * a 12
     * a 9  a
     * 当遇到数字的时候，就要按照数字本身的大小来比较，所以这个是a9a比较小，因为9 < 12
     */

    /**
     * return negative num if l < r
     0  if l == r
     positive if l > r
     */
    public int compare(String l, String r) {
        int lenL = l.length();
        int lenR = r.length();
        int iL = 0;
        int iR = 0;

        while (iL < lenL && iR < lenR) {
            char cL = l.charAt(iL);
            char cR = r.charAt(iR);
            if (!Character.isDigit(cL) && !Character.isDigit(cR)) {  // cL跟cR都不是数字字符
                if (cL != cR) return cL - cR;
                iL++;
                iR++;
                continue;
            }
            if (!Character.isDigit(cL)) {   // cL是字母
                return 1;
            }
            if (!Character.isDigit(cR)) {   // cR是字母
                return -1;
            }

            // 处理数字
            int subL = iL;
            int subR = iR;
            while (iL < lenL && Character.isDigit(l.charAt(iL))) {
                iL++;
            }
            while (iR < lenR && Character.isDigit(r.charAt(iR))) {
                iR++;
            }

            long numL = Long.valueOf(l.substring(subL, iL));
            long numR = Long.valueOf(r.substring(subR, iR));

            if (numL > numR) {
                return 1;
            }

            if (numL < numR) {
                return -1;
            }
        }

        if (iL == lenL && iR == lenR) {
            return 0;
        }

        if (iL == lenL) {
            return -1;
        }
        return 1;
    }

    public static void main(String[] args) {
        LexicographicCompare nc = new LexicographicCompare();
        System.out.println(nc.compare("a123", "a22"));
        System.out.println(nc.compare("a12", "a9a"));
        System.out.println(nc.compare("a008", "a8"));
        System.out.println(nc.compare("a123saf223", "a123saf990"));
        System.out.println(nc.compare("a123", "b"));
        System.out.println(nc.compare("as2223ssswed12", "as2223ssswfd12"));
    }
}

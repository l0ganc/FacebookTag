import java.util.Random;

public class LC277FindtheCelebrity {
    /**
     * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them,
     * there may exist one celebrity. The definition of a celebrity is that
     *          all the other n - 1 people know him/her but he/she does not know any of them.
     */

    static boolean knows(int a, int b) {
        Random rdm = new Random();
        int x = rdm.nextInt(100);
        return x % 2 == 0;
    }

    public static int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                if (knows(candidate, i) || !knows(i, candidate)) {
                    return -1;
                }
            }
        }
        return candidate;
    }
}

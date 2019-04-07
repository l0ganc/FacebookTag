import java.util.*;

public class LC721AccountsMerge {
    /**
     * we would like to merge these accounts. Two accounts definitely belong to the same person
     * if there is some email that is common to both accounts.
     * Note that even if two accounts have the same name,
     * they may belong to different people as people could have the same name
     *
     * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
     * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
     */

    /**
     * The key task here is to connect those emails, and this is a perfect use case for union find.
     * to group these emails, each group need to have a representative, or parent.
     * At the beginning, set each email as its own representative.
     * Emails in each account naturally belong to a same group, and should be joined by assigning to the same parent (let's use the parent of first email in that list);
     * Simple Example:
     *
     * a b c // now b, c have parent a
     * d e f // now e, f have parent d
     * g a d // now abc, def all merged to group g
     *
     * parents populated after parsing 1st account: a b c
     * a->a
     * b->a
     * c->a
     *
     * parents populated after parsing 2nd account: d e f
     * d->d
     * e->d
     * f->d
     *
     * parents populated after parsing 3rd account: g a d
     * g->g
     * a->g
     * d->g
     */

    // time = O(N) average , space = O(N)
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();

        for (List<String> a : accounts) {
            for (int i = 1; i < a.size(); i++) {
                owner.put(a.get(i), a.get(0));
                parents.put(a.get(i), a.get(i));
            }
        }

        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++) {
                parents.put(find(a.get(i), parents), p);
            }
        }

        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) {
                unions.put(p, new TreeSet<>());
            }
            for (int i = 1; i < a.size(); i++) {
                unions.get(p).add(a.get(i));
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList<>(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }

    private static String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));

        System.out.println(accountsMerge(accounts));
    }
}

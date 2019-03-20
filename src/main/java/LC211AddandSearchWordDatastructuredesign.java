public class LC211AddandSearchWordDatastructuredesign {
    /**
     * trie 数据结构
     * addWord() - O(n), n = length of the new word
     * search() - Worst case: O(m), m = the total number of characters in the Trie
     *
     * follow up是虽然这个已经是optimal的⽅法，有没有可能让search更快⼀
     * 点，表示在insert的时候就把“.”也当做⼀个node加进去，⽐如如果insert
     * “cat”, 那么第⼀ 层就是 “c”,".",第⼆层是“a”,"." | "a","."
     */
    static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
            word = "";
        }
    }

    private static TrieNode root;
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
        node.word = word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return find(word, root, 0);
    }

    private static boolean find(String word, TrieNode node, int index) {
        if (index == word.length()) {
            return node.isWord;
        }

        if (word.charAt(index) == '.') {
            for (TrieNode child : node.children) {
                if (child != null && find(word, child, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int j = word.charAt(index) - 'a';
            TrieNode temp = node.children[j];
            return temp != null && find(word, temp, index + 1);
        }
    }
}

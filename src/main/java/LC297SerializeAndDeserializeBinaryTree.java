public class LC297SerializeAndDeserializeBinaryTree {
    /**
     * 空间复杂度要注意，因为要存Null，所以worst case需要O(2n + 1)的space（n是所有node数量
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static int index;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private static void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("*").append(",");
        } else {
            sb.append(node.val).append(",");
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        index = 0;
        String[] res = data.split(",");
        return buildTree(res);
    }

    private static TreeNode buildTree(String[] res) {
        String p = res[index++];
        if (p.equals("*")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(p));
        root.left = buildTree(res);
        root.right = buildTree(res);
        return root;
    }
}

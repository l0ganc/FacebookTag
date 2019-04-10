public class LC449SerializeandDeserializeBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private static final String split = ",";
    private static final String NN = "null";
    private static int index;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        builderString(root, sb);
        return sb.toString();
    }

    private void builderString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NN).append(split);
        } else {
            sb.append(root.val).append(split);
            builderString(root.left, sb);
            builderString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] res = data.split(split);
        index = 0;
        return builderTree(res);
    }

    private TreeNode builderTree(String[] res) {
        String p = res[index++];
        if (p.equals(NN)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(p));
        root.left = builderTree(res);
        root.right = builderTree(res);
        return root;
    }
}

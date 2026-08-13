class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int best;
        int length;

        Node(char ch) {
            leftChar = ch;
            rightChar = ch;
            prefix = 1;
            suffix = 1;
            best = 1;
            length = 1;
        }

        Node() {
        }
    }

    Node[] tree;

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int q = queryIndices.length;
        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            answer[i] = tree[1].best;
        }

        return answer;
    }

    // Build Segment Tree
    private void build(int node, int start, int end, String s) {

        if (start == end) {
            tree[node] = new Node(s.charAt(start));
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid, s);
        build(node * 2 + 1, mid + 1, end, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one character
    private void update(
        int node,
        int start,
        int end,
        int index,
        char ch
    ) {

        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, end, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two adjacent segments
    private Node merge(Node left, Node right) {

        Node parent = new Node();

        parent.length = left.length + right.length;

        parent.leftChar = left.leftChar;
        parent.rightChar = right.rightChar;

        // Prefix
        parent.prefix = left.prefix;

        if (left.prefix == left.length &&
            left.rightChar == right.leftChar) {

            parent.prefix = left.length + right.prefix;
        }

        // Suffix
        parent.suffix = right.suffix;

        if (right.suffix == right.length &&
            left.rightChar == right.leftChar) {

            parent.suffix = right.length + left.suffix;
        }

        // Best
        parent.best = Math.max(left.best, right.best);

        // Check if a repeating sequence crosses the boundary
        if (left.rightChar == right.leftChar) {

            parent.best = Math.max(
                parent.best,
                left.suffix + right.prefix
            );
        }

        return parent;
    }
}
class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {

        for (String word : words) {
            insert(word);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void insert(String word) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.word = word;
    }


    private void dfs(char[][] board,
            int row,
            int col,
            TrieNode node,
            List<String> result) {

        // 1. Boundary check
        if (row < 0 || row >= board.length ||
                col < 0 || col >= board[0].length) {
            return;
        }

        // 2. Already visited
        if (board[row][col] == '#') {
            return;
        }

        char ch = board[row][col];

        // 3. Check Trie
        int index = ch - 'a';

        if (node.children[index] == null) {
            return;
        }

        // Move to next Trie node
        node = node.children[index];

        // 4. Word found
        if (node.word != null) {
            result.add(node.word);

            // Prevent duplicate result
            node.word = null;
        }

        // 5. Mark current cell as visited
        board[row][col] = '#';

        // 6. Explore four directions

        // Up
        dfs(board, row - 1, col, node, result);

        // Down
        dfs(board, row + 1, col, node, result);

        // Left
        dfs(board, row, col - 1, node, result);

        // Right
        dfs(board, row, col + 1, node, result);

        // 7. Backtrack - restore cell
        board[row][col] = ch;
    }
}
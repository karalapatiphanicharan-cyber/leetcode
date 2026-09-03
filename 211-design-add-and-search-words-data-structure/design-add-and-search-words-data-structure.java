class WordDictionary {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int index, TrieNode current) {

        if (index == word.length()) {
            return current.isEnd;
        }

        char ch = word.charAt(index);

        if (ch != '.') {

            int position = ch - 'a';

            if (current.children[position] == null) {
                return false;
            }

            return searchHelper(
                    word,
                    index + 1,
                    current.children[position]);
        }

        for (int i = 0; i < 26; i++) {

            if (current.children[i] != null) {

                if (searchHelper(
                        word,
                        index + 1,
                        current.children[i])) {
                    return true;
                }
            }
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
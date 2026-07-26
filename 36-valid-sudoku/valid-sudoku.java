class Solution 
{
    public boolean isValidSudoku(char[][] board) 
    {
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) 
        {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int row = 0; row < 9; row++) 
        {
            for (int col = 0; col < 9; col++) 
            {
                char current = board[row][col];

                if (current == '.') 
                {
                    continue;
                }

                int box = (row / 3) * 3 + (col / 3);

                if (rows[row].contains(current) ||
                    cols[col].contains(current) ||
                    boxes[box].contains(current)) {
                    return false;
                }

                rows[row].add(current);
                cols[col].add(current);
                boxes[box].add(current);
            }
        }

        return true;
    }
}
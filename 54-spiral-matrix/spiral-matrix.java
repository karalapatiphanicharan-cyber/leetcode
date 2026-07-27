class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;


        while (top <= bottom && left <= right) {

            // 1. Traverse Top Row (Left -> Right)
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;


            // 2. Traverse Right Column (Top -> Bottom)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;


            // 3. Traverse Bottom Row (Right -> Left)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }


            // 4. Traverse Left Column (Bottom -> Top)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}
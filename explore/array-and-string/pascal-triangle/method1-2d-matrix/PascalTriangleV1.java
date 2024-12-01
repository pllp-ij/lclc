import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
    VARS:
        matrix(int[][]): 2d matrix for calculating pascal triangle
        row(List<Integer>): store each line of matrix
        result(List<List<Integer>>): 2d ArrayList for final return
    DESCRIPTION:
        STEP 1
        Initialize matrix array with the size of pascalNum x pascalNum
        STEP 2
        Fill matrix first column and diag(from top left to bottom right) all to 1 by calling method
        STEP 3
        Loop each line of matrix from 0 to pascalNum - 1 with index i
            STEP 4
            Initialize row to ArrayList<>()
            STEP 5
            Iterate each position from 0 to i with index j
                If current index j is the first or last index of current line i
                    STEP 6
                    Directly add the current value into row
                        row.add(matrix[i][j])
                Else if j is in range from 1 to i - 1
                    STEP 7
                    Update each position with above two elements
                        matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1]                    
                    STEP 8
                    Add updated element to row
                        row.add(matrix[i][j]);
            STEP 9
            Add current row to final result
                result.add(row);
        STEP 10
        Return result
    TIME:
        O(n^2), all the elements in the bottom-left half of matrix will be visited
    SPACE:
        O(n^2), extra 2d matrix is used
    TO BE OPTIMIZED:
        Only use two linear arrays
*/


class PascalTriangleV1 {
   
    public static List<List<Integer>> getPascalTriangle(int pascalNum) {
        // STEP 1
        int[][] matrix = new int[pascalNum][pascalNum];
        List<List<Integer>> result = new ArrayList<>();
        // STEP 2
        fillOnes(matrix);
        // STEP 3
        for (int i = 0; i < pascalNum; i++) {
            // STEP 4
            List<Integer> row = new ArrayList<>();
            // STEP 5
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    // STEP 6
                    row.add(matrix[i][j]);
                } else if (j > 0 && j < i) {
                    // STEP 7
                    matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
                    // STEP 8
                    row.add(matrix[i][j]);
                }
            }
            // STEP 9
            result.add(row);
        }
        // STEP 10
        return result;
    }
   
    public static void fillOnes(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || i == j) {
                    matrix[i][j] = 1;
                }
            }
        }
    }
   
    public static void main(String[] args) {
        // int pascalNum = 5;
        int pascalNum = 1;
        System.out.println("pascal number: " + pascalNum);
        List<List<Integer>> pascalTriangle = getPascalTriangle(pascalNum);
        System.out.println("pascal triangle: " + Arrays.deepToString(pascalTriangle.toArray()));
    }
}
import java.util.Arrays;
import java.util.Arrays;

/**
    VARS:
        m(int): row number of the matrix
        n(int): col number of the matrix
        i(int): current iterated row position
        j(int): current iterated col position
        result(int[]): the traversed elements, for final return result
        idxResult(int): index to iterate result for final return result
    DESCRIPTION:
        STEP 1
        Initialize m to the mat.size()
        Initialize n to mat[0].size()
        Initialize i to 0, j to 0
        Initialize result to dynamic ArrayList<Integer>
        Initialize idxResult to 0
        STEP 2
        Loop while i + j <= m + n - 2
            STEP 3
            Add current point to final result array, and increase idxResult by one
                result[idxResult++] = mat[i][j]
            If (i + j) % 2 == 0, (meaning current point (i, j) is on even diagonal)
                STEP 4
                Move from point (i, j) to (i - 1, j + 1)
                    i = i - 1;
                    j = j + 1;
            Else if (i + j) % 2 != 0, (meaning current point (i, j) is on odd diagonal)
                STEP 5
                Move from point (i, j) to (i + 1, j - 1)
                    i = i + 1;
                    j = j - 1;
                
            Now the early stage movement has been finished, following will do some adjustment according to the moved position
                
            If i < 0 and j < n, (meaning the movement stick out the top edge)
                STEP 6
                Move point (i, j) to (i + 1, j)
                    i = i + 1
                    (j = j)
            Else if j < 0 and i < m, (meaning the movement stick out the left edge)
                STEP 7
                Move from point (i, j) to (i, j + 1)
                    (i = i)
                    j = j + 1
            Else if j >= n, (meaning the movement stick out the right edge)
                STEP 8
                Move from point (i, j) to (i + 2, n - 1)
                    i = i + 2;
                    j = n - 1;
            Else if i >= m, (meaning the movement stick out the bottom edge)
                STEP 9
                Move from point (i, j) to (m - 1, j + 2)
                    i = m - 1;
                    j = j + 2;
            
            Now the single movement has already been finished, jump to next iteration here automatically
        STEP 10
        Return result
    TIME:
        O(m * n), scan each element for once
    SPACE:
        O(1), no extra space needed
*/

class DiagonalTraverseV1 {
    
    public static int[] diagonalTraverse(int[][] mat) {
        // STEP 1
        int m = mat.length;
        int n = mat[0].length;
        int i = 0, j = 0;
        int[] result = new int[m * n];
        int idxResult = 0;
        // STEP 2
        while (i + j <= m + n - 2) {
            // STEP 3
            result[idxResult++] = mat[i][j];
            if ((i + j) % 2 == 0) {
                // STEP 4
                i = i - 1;
                j = j + 1;
            } else if ((i + j) % 2 != 0) {
                // STEP 5
                i = i + 1;
                j = j - 1;
            }
            
            if (i < 0 && j < n) {
                // STEP 6
                i = i + 1;
            } else if (j < 0 && i < m) {
                // STEP 7
                j = j + 1;
            } else if (j >= n) {
                // STEP 8
                i = i + 2;
                j = n - 1;
            } else if (i >= m) {
                // STEP 9
                i = m - 1;
                j = j + 2;
            }
        }
        // STEP 10
        return result;
    }
    
    public static void main(String[] args) {
        // int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] mat = {{1, 2}, {3, 4}};
        System.out.println("Before: " + Arrays.deepToString(mat));
        int[] result = diagonalTraverse(mat);
        System.out.println("After: " + Arrays.toString(result));
    }
}
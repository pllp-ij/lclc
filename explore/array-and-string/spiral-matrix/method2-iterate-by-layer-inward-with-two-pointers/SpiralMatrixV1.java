import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
    VARS:
        m(int): the first dimentsion length of matrix
        n(int): the second dimentsion length of matrix
        iTopLeft(int): denote the row index of top left corner point of current concentric rectangle layer
        jTopLeft(int): denote the col index of top left corner point of current concentric rectangle
        iDownRight(int): denote the row index of down right corner point of current concentric rectangle layer
        jDownRight(int): denote the col index of down right corner point of current concentric rectangle layer
        result(List<Integer>): the final return result with the size of (m * n)
    DESCRIPTION:
        STEP 1
        Initialize m to mat.length
        Initialize n to mat[0].length
        Initialize iTopLeft to 0, jTopLeft to 0
        Initialize iDownRight to (m-1), jDownRight to (n-1)
        Initialize result to ArrayList<Integer>
        STEP 2
        Loop while iTopLeft <= iDownRight and jTopLeft <= jDownRight,
        
            There are four conditions:
                iTopLeft < iDownRight and jTopLeft < jDownRight: intersect region is at least 2x2 rectangle
                iTopLeft == iDownRight and jTopLeft <= jDownRight: intersect region is a horizontal linear array
                iTopLeft <= iDownRight and jTopLeft == jDownRight: intersect region is a vertical linear array
                iTopLeft == iDownRight and jTopLeft == jDownRight: intersect region is a single element

            STEP 3
            Iterate each number from point (iTopLeft, jTopLeft) to point (iTopLeft, jDownRight) in right horizontal direction with index j 
                STEP 4
                Add current iterated number to final result
                    result.add(mat[iTopLeft][j]);
            STEP 5
            Iterate each number from point (iTopLeft + 1, jDownRight) to point (iDownRight, jDownRight) in down vertical direction with index i
                STEP 6
                Add current iterated number to final result
                    result.add(mat[i][jDownRight]);
            STEP 7
            If iTopLeft < iDownRight, (meaning the rectangle must have at least height of one)
                STEP 8
                Iterate each number from point (iDownRight, jDownRight - 1) to point (iDownRight, jTopLeft) in left horizontal direction with index j
                    STEP 9
                    Add current iterated number to final result
                        result.add(mat[iDownRight][j]);
            STEP 10
            If jTopLeft < jDownRight, (meaning the rectangle must have at least width of one)            
                STEP 11
                Iterate each number from point (iDownRight, jTopLeft) to point (iTopLeft + 1, jTopLeft) in up vertical direction with index i
                    STEP 12
                    Add current iterated number to final result
                        result.add(mat[i][jTopLeft]);
            STEP 13
            Update point (iTopLeft, jTopLeft) and point (iDownRight, jDownRight)
                iTopLeft++;
                jTopLeft++;
                iDownRight--;
                jDownRight--;
        STEP 14
        Return result
    TIME:
        O(m * n), each element is iterated once
    SPACE:
        O(1)
*/

class SpiralMatrixV1 {
    
    public static List<Integer> getSpiralMatrix(int[][] mat) {
        // STEP 1
        int m = mat.length;
        int n = mat[0].length;
        int iTopLeft = 0, jTopLeft = 0;
        int iDownRight = m - 1, jDownRight = n - 1;
        List<Integer> result = new ArrayList<>();
        // STEP 2
        while (iTopLeft <= iDownRight && jTopLeft <= jDownRight) {
            // STEP 3
            for (int j = jTopLeft; j <= jDownRight; j++) {
                // STEP 4
                result.add(mat[iTopLeft][j]);
            }
            // STEP 5
            for (int i = iTopLeft + 1; i <= iDownRight; i++) {
                // STEP 6
                result.add(mat[i][jDownRight]);
            }
            // STEP 7
            if (iTopLeft < iDownRight) {
                // STEP 8
                for (int j = jDownRight - 1; j >= jTopLeft; j--) {
                    // STEP 9
                    result.add(mat[iDownRight][j]);
                }
            }
            // STEP 10
            if (jTopLeft < jDownRight) {
                // STEP 11
                for (int i = iDownRight - 1;  i > iTopLeft; i--) {
                    // STEP 12
                    result.add(mat[i][jTopLeft]);
                }
            }
            // STEP 13
            iTopLeft++;
            jTopLeft++;
            iDownRight--;
            jDownRight--;
        }
        // STEP 14
        return result;
    }
    
    public static void main(String[] args) {
        // int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // int[][] mat = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // int[][] mat = {{1, 2, 3}, {4, 5, 6}};
        // int[][] mat = {{1, 2}, {3, 4}, {5, 6}};
        // int[][] mat = {{1, 2}};
        int[][] mat = {{1}, {2}, {3}};
        System.out.println("Before: " + Arrays.deepToString(mat));
        List<Integer> result = getSpiralMatrix(mat);
        System.out.println("After: " + result.toString());
    }
}
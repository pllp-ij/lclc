import java.util.Arrays;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
    VARS:
        m(int): row number of the matrix
        n(int): col number of the matrix
        iDiagStart(int): current iterated row position
        jDiagStart(int): current iterated col position
        diagCache(int[]): a temporary array for storing diagonal elements
        result(int[]): the traversed elements, for final return result
        idxResult(int): index to iterate result for final return result
    DESCRIPTION:
        STEP 1
        Initialize m to mat.length
        Initialize n to mat[0].length
        Initialize iDiagStart to 0, jDiagStart to 0
        Initialize diagCache to dynamic ArrayList<Integer>
        Initialize result with the size of (m * n)
        Initialize idxResult to 0
        STEP 2
        Iterate each diag from top-left to bottom-right with index diag
            If diag < n, (meaning currently on top-left triangle region)
                STEP 3
                Set iDiagStart to 0, jDiagStart to diag
                    iDiagStart = 0;
                    jDiagStart = diag;
            Else, (meaning current on bottom-right triangle region)
                STEP 4
                Set jDiagStart to n - 1, iDiagStart to diag - jDiagStart,
                    jDiagStart = n - 1;
                    iDiagStart = diag - jDiagStart;
            STEP 5
            Add current diag from top-right element to bottom-left element to diagCache by calling method
                addDiag(mat, iDiagStart, jDiagStart, diagCache);
            If current diag is even, (meaning 0, 2, 4, .. diag)
                STEP 6
                Reverse diagCache
                    Collections.reverse(diagCache);
            STEP 7
            Add diagCache to final result by iterating each element in diagCache
            STEP 8
            Clear diagCache for next iteration
        STEP 9
        Return result
    TIME:
        O(m * n), iterate each element in 2d matrix
    SPACE:
        O(min(m, n)), extra cache array needed to save diag elements, the size of it is determined by the minimum one between m and n
    OPTIMIZED:
        Avoid using extra space
*/

class DiagonalTraverseV1 {
    
    public static int[] diagonalTraverse(int[][] mat) {
        // STEP 1
        int m = mat.length;
        int n = mat[0].length;
        int iDiagStart = 0;
        int jDiagStart = 0;
        List<Integer> diagCache = new ArrayList<>();
        int[] result = new int[m * n];
        int idxResult = 0;
        // STEP 2
        for (int diag = 0; diag <= m + n - 2; diag++) {
            if (diag < n) {
                // STEP 3
                iDiagStart = 0;
                jDiagStart = diag;
            } else {
                // STEP 4
                jDiagStart = n - 1;
                iDiagStart = diag - jDiagStart;
            }
            
            // STEP 5
            copyDiag(mat, iDiagStart, jDiagStart, diagCache);
            if (diag % 2 == 0) {
                // STEP 6
                Collections.reverse(diagCache);
            }
            // STEP 7
            for (Integer diagEle: diagCache) {
                result[idxResult++] = diagEle;
            }
            // STEP 8
            diagCache.clear();
        }
        // STEP 9
        return result;
    }
    
    public static void copyDiag(int[][] mat, int iDiagStart, int jDiagStart, List<Integer> diagCache) {
        while (iDiagStart < mat.length && jDiagStart >= 0) {
            diagCache.add(mat[iDiagStart][jDiagStart]);
            iDiagStart++;
            jDiagStart--;
        }
    }
    
    public static void main(String[] args) {
        // int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] mat = {{1, 2}, {3, 4}};
        System.out.println("Before: " + Arrays.deepToString(mat));
        int[] result = diagonalTraverse(mat);
        System.out.println("After: " + Arrays.toString(result));
    }
}
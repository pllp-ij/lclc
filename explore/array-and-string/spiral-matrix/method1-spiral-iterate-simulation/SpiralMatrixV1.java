import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
    VARS:
        m(int): the first size of initial matrix
        n(int): the second size of initial matrix
        i(int): row index of current position in matrix
        j(int): col index of current position in matrix
        directionArr(int[]): an array to represent the four directions with five elements
        idxDirection(int): the iterate index of direction array, range from 0 to 3
        visited(booelan[][]): the 2d matrix to record whether the elements are visited
        result(List<Integer>): final result to store the all spirally iterated elements
        idxResult(int): the iterate index to generate final result
    DESCRIPTION:
        STEP 1
        Initialize m to mat.length
        Initialize n to mat[0].length
        Initialize i to 0, j to 0
        Initialize directionArr
            int[] directionArr = {0, 1, 0, -1, 0}
        Initialize idxDirection to 0
        Initialize visited as the same size of initial matrix
        Initialize result to ArrayList<Integer>
        Initialize idxResult to 0
        STEP 2
        Loop while idxResult < m * n, (meaning loop all the elements of matrix)
            STEP 3
            Add current point (i, j) to final result and increase idxResult
                result.add(mat[i][j])
                idxResult++;
            STEP 4
            Update visited matrix to note current point (i, j) has already beeen visited
                visited[i][j] = true;
            STEP 5
            Calculate the next step position, in x-direction, the value indicate exactly the actual direction,  while in y direction, the value indicate reverse actual direction
                int iNext = i + directionArr[idxDirection]
                int jNext = j + directionArr[idxDirection + 1];
            STEP 6
            If the position of the next step is outside the range of matrix or it has been visited
                STEP 7
                Rotate the direction clock-wise by increasing the idxDirection by one and module 4 to avoid index overflow
                    idxDirection = (idxDirection + 1) % 4;
                STEP 8
                Update next step position with updated idxDirection
                    i = i + directionArr[idxDirection];
                    j = j + directionArr[idxDirection + 1];
        STEP 9
        Return result   
    TIME:
        O(m * n), each element is iterated once
    SPACE:
        O(m * n), extra matrix with same size of initial matrix is used to save the status of each cell
    TO BE OPTIMIZED:
        Avoid using extra visited matrix
*/

class SpiralMatrixV1 {
    
    public static List<Integer> getSpiralMatrix(int[][] mat) {
        // STEP 1
        int m = mat.length;
        int n = mat[0].length;
        int i = 0, j = 0;
        int[] directionArr = {0, 1, 0, -1, 0};
        int idxDirection = 0;
        boolean[][] visited = new boolean[m][n];
        List<Integer> result = new ArrayList<>();
        int idxResult = 0;
        // STEP 2
        while (idxResult < m * n) {
            // STEP 3
            result.add(mat[i][j]);
            idxResult++;
            // STEP 4
            visited[i][j] = true;
            // STEP 5
            int iNext = i + directionArr[idxDirection];
            int jNext = j + directionArr[idxDirection + 1];
            // STEP 6
            if (iNext < 0 || iNext >= m || 
                jNext < 0 || jNext >= n ||
                visited[iNext][jNext]) {
                // STEP 7
                idxDirection = (idxDirection + 1) % 4;
            }
            // STEP 8
            i = i + directionArr[idxDirection];
            j = j + directionArr[idxDirection + 1];
        }
        // STEP 9
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
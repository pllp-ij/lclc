import java.util.Arrays;

/*
    VARS:
        islandsNum(int): the total islands number in matrix, also the final return result
        m(int): the row number of matrix
        n(int): the col number of matrix
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        STEP 2
        Iterate each row of matrix with i
            STEP 3
            Iterate each col of matrix with j
                STEP 4
                If matrix[i][j] == '1', (meaning current position is on an islands)
                    STEP 5
                    Do DFS from current position
                        DFS(matrix, i, j, m, n);
                    STEP 6
                    Increase islandsNum by one
                        islandsNum++;
        STEP 7
        Return islandsNum
        
        -FUNC void DFS(char[][] matrix, int i, int j, int m, int n)
        If isInInvalidRegion(matrix, i, j, m, n), (meaning current position (i, j) is in invliad region by calling method to check, this is similar to the null node of binary tree or linked list, you also can return in advance at the node before null, in which case you should check all nodes in current node's next step is invalid, so it will be harder to deal with)
            STEP 2
            Directly return
        
        Now current position (i, j) is valid, so set matrix[i][j] to '0'
            
        STEP 3
        Set matrix[i][j] to '0' to indicate (i, j) is already visited, so current position become an invalid point
            matrix[i][j] = '0';
        STEP 4
        Initialize directions as array {-1, 0, 1, 0, -1};
            int[] directions = {-1, 0, 1, 0, -1};
        Initialize nextI to 0
        Initialize nextJ to 0
        STEP 5
        Iterate each direction of current position (i, j) with idxDirection from 0 to 3
            STEP 6
            Calculate (nextI, nextJ) from (i, j) using directions and idxDirection
                nextI = i + directions[idxDirection];
                nextJ = j + directions[idxDirection + 1];
            STEP 7
            Recursively do DFS using new position (nextI, nextJ)
                DFS(matrix, nextI, nextJ, m, n);
        
        -FUNC boolean isInInvalidRegion(char[][] matrix, int i, int j, int m, int n)
        STEP 1
        If i < 0 || i >= m ||
           j < 0 || j >= n ||
           matrix[i][j] == '0', (meaning (i, j) is now in invalid region)
            STEP 2
            Return true;
        STEP 3
        Return true
    TIME:
        O(m * n), m is the row number of matrix, n is the col number of matrix
    SPACE:
        O(m * n), an extra calling stack is used
*/

public class NumberOfIslandsV1 {
    
    public static int getIslandsNum(char[][] matrix) {
        // STEP 1
        int islandsNum = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == '1') {
                    // STEP 5
                    DFS(matrix, i, j, m, n);
                    // STEP 6
                    islandsNum++;
                }
            }
        }
        // STEP 7
        return islandsNum;
    }
    
    public static void DFS(char[][] matrix, int i, int j, int m, int n) {
        // STEP 1
        if (isInInvalidRegion(matrix, i, j, m, n)) {
            // STEP 2
            return;
        }
        // STEP 3
        matrix[i][j] = '0';
        // STEP 4
        int[] directions = {-1, 0, 1, 0, -1};
        int nextI = 0;
        int nextJ = 0;
        // STEP 5
        for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
            // STEP 6
            nextI = i + directions[idxDirection];
            nextJ = j + directions[idxDirection + 1];
            // STEP 7
            DFS(matrix, nextI, nextJ, m, n);
        }
    }
     
    public static boolean isInInvalidRegion(char[][] matrix, int i, int j, int m, int n) {
        // STEP 1
        if (i < 0 || i >= m ||
            j < 0 || j >= n ||
            matrix[i][j] == '0') {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        char[][] matrix = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };  // 1
        
        // char[][] matrix = {
            // {'1', '1', '0', '0', '0'},
            // {'1', '1', '0', '0', '0'},
            // {'0', '0', '1', '0', '0'},
            // {'0', '0', '0', '1', '1'}
        // };  // 3
        System.out.println("matrix: " + Arrays.deepToString(matrix));
        
        int result = getIslandsNum(matrix);
        System.out.println("islands num: " + result);
    }
}
import java.util.Arrays;

/*
    NOTE:
        The differences between V1 and V2 is that, in V1, the check will put at the beginning, and before recursively enter into next steps with four directions, there is no check(because the check will be on the next entry),
        while in V2, every time before entering into next position of four directions, there will be a check firstly
    VARS:
        islandsNum(int): the total number of islands, also for final return
        m(int): the row number of matrix
        n(int): the col number of matrix
        visited(int[][]): a cache matrix of same size as matrix to indicate if a position is already visited
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize visited as int[m][n]
        STEP 2
        Iterate each row of matrix with i
            STEP 3
            Iterate each col of matrix with j
                STEP 4
                If matrix[i][j] == '1' and visited[i][j] != 1, (meaning current position is in lslandsNum and it is not visited yet)
                    STEP 5
                    Do DFS with current position (i, j)
                        DFS(matrix, i, j, m, n, visited)
                    STEP 6
                    Increase islandsNum by one after each DFS finished
                        islandsNum++;
        STEP 7
        Return islandsNum
        
        -FUNC void DFS(char[][] matrix, int i, int j, int m, int n, int[][] visited)
        STEP 1
        Set current position (i, j) as visited
            visited[i][j] = 1;
        STEP 2
        Initialize directions for next option
            int[] directions = {-1, 0, 1, 0, -1};
        Initialize nextI to 0
        Initialize nextJ to 0
        STEP 3
        Iterate each direction with idxDirection from 0 to 3
            STEP 4
            Generate (nextI, nextJ) from (i, j) using directions and idxDirection
                nextI = i + directions[idxDirection];
                nextJ = j + directions[idxDirection + 1];
            STEP 5
            If isInValidRegion(matrix, nextI, nextJ, m, n, visited), (meaning next position is in valid)
                STEP 6
                Recursively do DFS
                    DFS(matrix, nextI, nextJ, m, n, visited);
        -FUNC boolean isInValidRegion(char[][] matrix, int i, int j, int m, int n, int[][] visited)
        STEP 1
        If i >= 0 && i < m &&
           j >= 0 && j < n &&
           matrix[i][j] == '1' &&
           visited[i][j] != 1, (meaning current position (i, j) is vlaid)
            STEP 2
            return true;
        STEP 3
        return false;
    TIME:
        O(m * n + m * n) ~ O(m * n), first (m * n) for iterating each position in matrix, second (m * n) for the depth of calling stack when doing DFS in worst cases
    SPACE:
        O(m * n + m * n) ~ O(m * n), first (m * n) for visited matrix, second (m * n) for the depth of calling stack when doing DFS in worst cases
*/

public class NumberOfIslandsV2 {
    
    public static int getIslandsNum(char[][] matrix) {
        // STEP 1
        int islandsNum = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == '1' && visited[i][j] != 1) {
                    // STEP 5
                    DFS(matrix, i, j, m, n, visited);
                    // STEP 6
                    islandsNum++;
                }
            }
        }
        // STEP 7
        return islandsNum;
    }
    
    public static void DFS(char[][] matrix, int i, int j, int m, int n, int[][] visited) {
        // STEP 1
        visited[i][j] = 1;
        // STEP 2
        int[] directions = {-1, 0, 1, 0, -1};
        int nextI = 0;
        int nextJ = 0;
        // STEP 3
        for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
            // STEP 4
            nextI = i + directions[idxDirection];
            nextJ = j + directions[idxDirection + 1];
            // STEP 5
            if (isInValidRegion(matrix, nextI, nextJ, m, n, visited)) {
                // STEP 6
                DFS(matrix, nextI, nextJ, m, n, visited);
            }
        }
    }
    
    public static boolean isInValidRegion(char[][] matrix, int i, int j, int m, int n, int[][] visited) {
        // STEP 1
        if (i >= 0 && i < m &&
            j >= 0 && j < n &&
            matrix[i][j] == '1' &&
            visited[i][j] != 1) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        // char[][] matrix = {
            // {'1', '1', '1', '1', '0'},
            // {'1', '1', '0', '1', '0'},
            // {'1', '1', '0', '0', '0'},
            // {'0', '0', '0', '0', '0'}
        // };  // 1
        
        char[][] matrix = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };  // 3
        System.out.println("matrix: " + Arrays.deepToString(matrix));
        
        int result = getIslandsNum(matrix);
        System.out.println("islands num: " + result);
    }
}
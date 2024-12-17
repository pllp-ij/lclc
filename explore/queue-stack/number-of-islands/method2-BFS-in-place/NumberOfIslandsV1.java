import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        islandsNum(int): the final return islands number
        m(int): the row number of matrix
        n(int): the col number of matrix
        queue(Queue<int[]>): a queue for iterate each node in matrix when BFS
        curNode(int[]): each popped node from queue when BFS
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        STEP 2
        Iterate each row of matrix with index i
            STEP 3
            Iterate each col of matrix with index j
                STEP 4
                If matrix[i][j] == '1', (meaning current is islands)
                    STEP 5
                    Do BFS from current position with index (i, j) by calling method
                        BFS(matrix, i, j, m, n);
                    STEP 6
                    After BFS finished, increase islandsNum by one
                        islandsNum++;
        STEP 7
        Return islandsNum
        
        -FUNC BFS(char[][] matrix, int startI, int startJ, int m, int n)
        STEP 1
        Initialize queue to Queue<int[]>
        Initialize directions = {-1, 0, 1, 0, -1};
        Initialize curNode to null
        STEP 2
        Add current position to queue with index (startI, startJ)
            queue.offer(new int[]{startI, startJ});
        STEP 3
        Set current position in original matrix to '0' to indicate current position is already visited
            matrix[startI][startJ] = '0';
        STEP 4
        Loop while queue is not empty, 
            STEP 5
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
            STEP 6
            Get curI, curJ from curNode
                int curI = curNode[0]
                int curJ = curNode[1]
            STEP 7
            Iterate each direction with idxDirection from 0 to 3
                STEP 8
                Calculate next position with index (nextI, nextJ)
                    int nextI = curI + directions[idxDirection];
                    nit nextJ = curJ + directions[idxDirection + 1];
                STEP 9
                If isValid(matrix, nextI, nextJ, m, n), (meaning next position is valid)
                    STEP 10
                    Add next position to queue with index (nextI, nextJ)
                        queue.offer(new int[]{nextI, nextJ});
                    STEP 11
                    Set next position in original matrix to 0 to indicate next position is visited
                        matrix[nextI][nextJ] = '0';
        
        -FUNC isValid(char[][] matrix, int i, int j, int m, int n)
        STEP 1
        If i >= 0 && i < m &&
           j >= 0 && j < n &&
           matrix[i][j] == 1,
            STEP 2
            Return true
        STEP 3
        Return false;
    TIME:
        O(m * n), m is row number of matrix, n is col number of matrix
    SPACE:
        O(m + n), m is row number of matrix, n is col number of matrix, there should be at most (m + n) nodes in queue for BFS
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
                    BFS(matrix, i, j, m, n);
                    // STEP 6
                    islandsNum++;
                }
            }
        }
        // STEP 7
        return islandsNum;
    }
    
    public static void BFS(char[][] matrix, int startI, int startJ, int m, int n) {
        // STEP 1
        Queue<int[]> queue = new LinkedList<>();
        int[] directions = {-1, 0, 1, 0, -1};
        int[] curNode = null;
        // STEP 2
        queue.offer(new int[]{startI, startJ});
        // STEP 3
        matrix[startI][startJ] = '0';
        // STEP 4
        while (!queue.isEmpty()) {
            // STEP 5
            curNode = queue.poll();
            // STEP 6
            int curI = curNode[0];
            int curJ = curNode[1];
            // STEP 7
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 8
                int nextI = curI + directions[idxDirection];
                int nextJ = curJ + directions[idxDirection + 1];
                // STEP 9
                if (isValid(matrix, nextI, nextJ, m, n)) {
                    // STEP 10
                    queue.offer(new int[]{nextI, nextJ});
                    // STEP 11
                    matrix[nextI][nextJ] = '0';
                }
            }
        }
    }
    
    public static boolean isValid(char[][] matrix, int i, int j, int m, int n) {
        // STEP 1
        if (i >= 0 && i < m &&
            j >= 0 && j < n &&
            matrix[i][j] == '1') {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        // char[][] matrix = {
            // {'1', '1', '1', '1', '0'},
            // {'1', '1', '0', '1', '0'},
            // {'1', '1', '0', '0', '0'},
            // {'0', '0', '0', '0', '0'}
        // };
        
        char[][] matrix = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("matrix: " + Arrays.deepToString(matrix));
        
        int result = getIslandsNum(matrix);
        System.out.println("islands num: " + result);
    }
}
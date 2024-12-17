import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
/*
    NOTE:
        Compared to V1, V2 will wrap the BFS into a sperate function and combine all conditions that's valid together
        the directions should not be eight directions in V2, because the islands is defined nodes connected by horizontally and vertically
    VARS:
        islandsNum(int): the number of islands for final return
        m(int): the row number of matrix
        n(int): the col number of matrix
        visited(int[][]): a cache matrix to record whether current position has been visited
        queue(Queue<int[]>): queue for BFS
        curNode(int[]): represent each node when popping node from queue
        directions(int[]): each direction of four directions under curNode in BFS
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize visited to new int[m][n]
        STEP 2
        Iterate each row of matrix with index i
            STEP 3
            Iterate each col of matrix with index j
                STEP 4
                If matrix[i][j] == '1' and visited[i][j] != 1, (meaning current position in initial matrix is 1, and current position in visited cache matrix is not visited)
                    STEP 5
                    Do BFS from current position with index (i, j)
                        BFS(matrix, visited, i, j, m, n);
                    STEP 6
                    Increate islandsNum by one
                        islandsNum++;
        STEP 7
        Return islandsNum;
        
        - FUNC BFS(char[][] matrix, int[][] visited, int startI, int startJ, int m, int n)
        STEP 1
        Initialize queue to Queue<int[]>
        Initialize directions to {-1, 0, 1, 0, -1}
        Initialize curNode to null
        STEP 2
        Add current (startI, startJ) to queue
            queue.offer(new int[]{startI, startJ});
        STEP 3
        Set value of current position (startI, startJ) in visited to 1
            visited[startI][startJ] = 1
        STEP 4
        Loop while queue is not empty
            STEP 5
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
            STEP 6
            Get curI, curJ from curNode
                int curI = curNode[0];
                int curJ = curNode[1];
            STEP 7
            Iterate each direction of current node with index idxDirection from 0 to 3
                STEP 8
                Calculate next position with nextI, nextJ
                    int nextI = curI + directions[idxDirection]
                    int nextJ = curJ + directions[idxDirection + 1]
                STEP 9
                If isValid(matrix, visited, nextI, nextJ, m, n), (meaning next position with coordinate (nextI, nextJ) is valid by calling method to check)
                    STEP 10
                    Add next position to queue
                        queue.offer(new int[]{nextI, nextJ});
                    STEP 11
                    Set value of next position in visited to 1 to indicate that the node is already visited
                        visited[nextI][nextJ] = 1
        
        - FUNC isValid(char[][] matrix, int[][] visited, int i, int j, int m, int n)
        STEP 1
        If i >= 0 && i < m &&
           j >= 0 && j < n &&
           matrix[i][j] == '1' &&
           visited[i][j] != 1, (meaning i, j is within valid range, current position is '1', current position is not yet visited)
           STEP 2
           Return true
        STEP 3
        Return false;
    TIME:
        O(m * n), m is row number of matrix, n is col number of matrix
    SPACE:
        O(m * n), m is row number of matrix, n is col number of matrix
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
                    BFS(matrix, visited, i, j, m, n);
                    // STEP 6
                    islandsNum++;
                }
            }
        }
        // STEP 7
        return islandsNum;
    }
    
    public static void BFS(char[][] matrix, int[][] visited, int startI, int startJ, int m, int n) {
        // STEP 1
        Queue<int[]> queue = new LinkedList<>();
        int[] directions = {-1, 0, 1, 0, -1};
        int[] curNode = null;
        // STEP 2
        queue.offer(new int[]{startI, startJ});
        // STEP 3
        visited[startI][startJ] = 1;
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
                if (isValid(matrix, visited, nextI, nextJ, m, n)) {
                    // STEP 10
                    queue.offer(new int[]{nextI, nextJ});
                    // STEP 11
                    visited[nextI][nextJ] = 1;
                }
            }
        }
    }
    
    public static boolean isValid(char[][] matrix, int[][] visited, int i, int j, int m, int n) {
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
        char[][] matrix = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        
        // char[][] matrix = {
            // {'1', '1', '0', '0', '0'},
            // {'1', '1', '0', '0', '0'},
            // {'0', '0', '1', '0', '0'},
            // {'0', '0', '0', '1', '1'}
        // };
        System.out.println("matrix: " + Arrays.deepToString(matrix));
        
        int result = getIslandsNum(matrix);
        System.out.println("islands num: " + result);
    }
}
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        islandsNum(int): final result of number of islands
        queue(Queue<int[]>): a queue for BFS
        m(int): the row number of matrix
        n(int): the col number of matrix
        visited(int[][]): a cache to store all positions has been visited
        rowDirections(int[]): a row index of four directions in click-wise direction from top point
        colDirections(int[]): a col index of four directions in click-wise direction from top point
        curNode(Node): current iterated node
        nextNode(Node): the next position in one of four directions of curNode
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize queue to LinkedList<>()
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize visited to int[][]
        Initialize rowDirections to {-1, 0, 1, 0};
        Initialize colDirections to {0, 1, 0, -1};
        Initialize curNode to null with int[] type
        Initialize nextNode to null with int[] type
        STEP 2
        Iterate each row of matrix with index i
            STEP 3
            Iterate each col of matrix with index j
                STEP 4
                If matrix[i][j] == 1 and visited[i][j] != 1, (meaning current number is 1 and it is not visited yet)
                    STEP 5
                    Add current position with new int[]{i, j} to queue to start BFS
                        queue.offer(new int[]{i, j});
                    STEP 6
                    Set value of position (i, j) in visited to 1
                        visited[i][j] = 1
                    
                    Now begin to do BFS from startNode in queue
                    
                    STEP 7
                    Loop while queue is not empty
                        STEP 8
                        Pop front node from queue and assign it to curNode
                            curNode = queue.poll();
                        STEP 9
                        Iterate each direction of four directions with idxDirection
                            STEP 10
                            Calculate (nextI, nextJ) from curNode using rowDirections, colDirections and idxDirection
                                int nextI = curNode[0] + rowDirections[idxDirection];
                                int nextJ = curNode[1] + colDirections[idxDirection];
                            STEP 11
                            If isInValidRegion(matrix, nextI, nextJ, m, n, visited), (meaning next position is within valid region),
                                STEP 12
                                Add (nextI, nextJ) to queue,
                                    queue.offer(new int[]{nextI, nextJ})
                                STEP 13
                                Set the value of next position in visited to 1
                                    visited[nextI][nextJ] = 1
                    
                    Now the BFS already done and queue is empty again and all connectable nodes to startNode are all visited
                    
                    STEP 14
                    Increase islandsNum by one
                        islandsNum++;
        STEP 15
        Return islandsNum
    TIME:
        O(m * n), m is row number of matrix, n is col number of matrix
    SPACE:
        O(m * n + m * n) ~ O(m * n), an extra visited matrix used with same size of matrix(m * n), and second (m * n) represent the maximum number of node in matrix to be pushed into stack in worst case
*/

public class NumberOfIslandsV4 {
    
    public static int getIslandsNum(char[][] matrix) {
        // STEP 1
        int islandsNum = 0;
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};
        int[] curNode =  null;
        int[] nextNode = null;
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == '1' && visited[i][j] != 1) {
                    // STEP 5
                    queue.offer(new int[]{i, j});
                    // STEP 6
                    visited[i][j] = 1;
                    
                    // BFS Start
                    // STEP 7
                    while (!queue.isEmpty()) {
                        // STEP 8
                        curNode = queue.poll();
                        // STEP 9
                        for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                            // STEP 10
                            int nextI = curNode[0] + rowDirections[idxDirection];
                            int nextJ = curNode[1] + colDirections[idxDirection];
                            // STEP 11
                            if (isInValidRegion(matrix, nextI, nextJ, m, n, visited)) {
                                // STEP 12
                                queue.offer(new int[]{nextI, nextJ});
                                // STEP 13
                                visited[nextI][nextJ] = 1;
                            }
                        }
                    }
                    // BFS END
                    
                    // STEP 14
                    islandsNum++;
                }
            }
        }
        // STEP 15
        return islandsNum;
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
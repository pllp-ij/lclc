import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        islandsNum(int): final result of number of islands
        queue(Queue<Node>): a queue for BFS
        m(int): the row number of matrix
        n(int): the col number of matrix
        visited(int[][]): a cache to store all positions has been visited
        rowDirections(int[]): a row index of four directions in click-wise direction from top point
        colDirections(int[]): a col index of four directions in click-wise direction from top point
        curNode(Node): current iterated node
        nextNode(Node): the next position in one of four directions of curNode
    DESCRIPTION:
        STEP 1
        Initialize Node class
        Initialize islandsNum to 0
        Initialize queue to LinkedList<>()
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize visited to int[][]
        Initialize rowDirections to {-1, 0, 1, 0};
        Initialize colDirections to {0, 1, 0, -1};
        Initialize curNode to null
        Initialize nextNode to null
        STEP 2
        Iterate each row of matrix with index i
            STEP 3
            Iterate each col of matrix with index j
                STEP 4
                If matrix[i][j] == 1, (meaning current number is 1)
                    STPE 5
                    Create new Node from current (i, j) position index
                        Node startNode = new Node(i, j);
                    STEP 6
                    If visited[i][j] != 1, (meaning current position is not visited before)
                        STEP 7
                        Add current position with new Node(i, j) to queue to start BFS
                            queue.offer(startNode)
                        STEP 8
                        Set value of position (i, j) in visited to 1
                            visited[i][j] = 1
                        
                        Now begin to do BFS from startNode in queue
                        
                        STEP 9
                        Loop while queue is not empty
                            STEP 10
                            Pop front node from queue and assign it to curNode
                                curNode = queue.poll();
                            STEP 11
                            Get curI, curJ from curNode
                                int curI = curNode.i;
                                int curJ = curNode.j;
                            STEP 12
                            Iterate each direction of four directions with idxDirection
                                STEP 13
                                Calculate next position with nextI, nextJ
                                    int nextI = curI + rowDirections[idxDirection];
                                    int nextJ = curJ + colDirections[idxDirection];
                                STEP 14
                                If isWithinRange(nextI, nextJ, m, n), (meaning next position is within valid range of matrix),
                                    STEP 15
                                    Create new Node(nextI, nextJ) from nextI and nextJ
                                        nextNode = new Node(nextI, nextJ);
                                    STEP 16
                                    If visited[nextI][nextJ] != 1, (meaning next position has not been visited before)
                                        STEP 17
                                        If isNotWater(matrix, nextI, nextJ), (meaning next position is not water)
                                            STEP 18
                                            Add nextNode to queue,
                                                queue.offer(nextNode)
                                            STEP 19
                                            Set the value of next position in visited to 1
                                                visited[nextI][nextJ] = 1
                        
                        Now the BFS already done and queue is empty again and all connectable nodes to startNode are all visited
                        
                        STEP 20
                        Increase islandsNum by one
                            islandsNum++;
        STEP 21
        Return islandsNum
    TIME:
        O(m * n), m is row number of matrix, n is col number of matrix
    SPACE:
        O(m * n), m is row number of matrix, n is col number of matrix
*/

class Node {
    public int i;
    public int j;
    
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class NumberOfIslandsV1 {
    
    public static int getIslandsNum(char[][] matrix) {
        // STEP 1
        int islandsNum = 0;
        Queue<Node> queue = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] visited = new int[m][n];
        int[] rowDirections = {-1, 0, 1, 0};
        int[] colDirections = {0, 1, 0, -1};
        Node startNode = null;
        Node curNode =  null;
        Node nextNode = null;
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == '1') {
                    // STEP 5
                    startNode = new Node(i, j);
                    // STEP 6
                    if (visited[i][j] != 1) {
                        // STEP 7
                        queue.offer(startNode);
                        // STEP 8
                        visited[i][j] = 1;
                        
                        // BFS Start
                        // STEP 9
                        while (!queue.isEmpty()) {
                            // STEP 10
                            curNode = queue.poll();
                            // STEP 11
                            int curI = curNode.i;
                            int curJ = curNode.j;
                            // STEP 12
                            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                                // STEP 13
                                int nextI = curI + rowDirections[idxDirection];
                                int nextJ = curJ + colDirections[idxDirection];
                                // STEP 14
                                if (isWithinRange(nextI, nextJ, m, n)) {
                                    // STEP 15
                                    nextNode = new Node(nextI, nextJ);
                                    // STEP 16
                                    if (visited[nextI][nextJ] != 1)  {
                                        // STEP 17
                                        if (isNotWater(matrix, nextI, nextJ)) {
                                            // STEP 18
                                            queue.offer(nextNode);
                                            // STEP 19
                                            visited[nextI][nextJ] = 1;
                                        }
                                    }
                                }
                            }
                        }
                        // BFS END
                        
                        // STEP 20
                        islandsNum++;
                    }
                }
            }
        }
        // STEP 21
        return islandsNum;
    }
    
    public static boolean isWithinRange(int i, int j, int m, int n) {
        if (i < 0 || i >= m ||
            j < 0 || j >= n) {
                return false;
        }
        return true;
    }
    
    public static boolean isNotWater(char[][] matrix, int i, int j) {
        return matrix[i][j] != '0';
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
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

/*
    NOTE:
        Compared to V1, V2 will iterate nodes in queue by level group using queue.size(), which process the nodes in same level in STEP 7
    VARS:
        queue(Queue<Node>): a queue to store each node with Node type when doing BFS
        m(int): the row number of matrix
        n(int): the col number of matrix
        directions(int[]): the index for four directions of current iterated node, top, right, down, left
        curDistance(int): the distance of current iterated node to nearest gate
        curLevelNode(Node): the nodes iterated in current level
    DESCRIPTION:
        STEP 1
        Initialize Node class with i, j, distance property
        Initialize queue to Queue<Node>
        Initialize m to row number of matrix
            int m = matrix.length;
        Initialize n to col number of matrix
            int n = matrix[0].length;
        Initialize directions to {-1, 0, 1, 0, -1};
        Initialize curDistance to 0
        Initialize curLevelNode to null
        STEP 2
        Iterate each row of matrix with index in
            STEP 3
            Iterate each col of matrix with index j
                STEP 4
                If matrix[i][j] == 0, (meaning current node is gate)
                    STEP 5
                    Add new Node(i, j) to queue
                        queue.offer(new Node(i, j));
        
        Now queue contains all gate nodes
        
        STEP 6
        Loop while queue is not empty,
            STEP 7
            Store size of queue for current level iterated, which will be fixed for for loop condition below
                int curLevelSize = queue.size();
            STEP 8
            Iterate each node in current iterated level with index i < curLevelSize
                STEP 9
                Pop front element from queue and assign it to curLevelNode
                    curLevelNode = queue.poll();
                STEP 10
                Get curI, curJ from curLevelNode
                    int curI = curLevelNode.i;
                    int curJ = curLevelNode.j;
                STEP 11
                Iterate each direction from four directions with idxDirection from 0 to 3
                    STEP 12
                    Calculate next position with nextI, nextJ
                        int nextI = curI + directions[idxDirection];
                        int nextJ = curJ + colDirections[idxDirection + 1];
                    STEP 13
                    If isWithinRange(nextI, nextJ, m, n), (meaning next position is within the range of matrix)
                        STEP 14
                        If isRoomNotUpdated(matrix, nextI, nextJ), (meaning next position is empty room and has not been updated)
                            STEP 15
                            Assign the value of next position to curDistance
                                matrix[nextI][nextJ] = curDistance + 1;
                            STEP 16
                            Add new Node(nextI, nextJ) to queue
                                queue.offer(new Node(nextI, nextJ))
            STEP 17
            Increase curDistance by one to scan next level in the following
                curDistance++;
    TIME:
        O(m * n), m is the row number, n is the col number in matrix, in worst case, each element will be scanned once
    SPACE:
        O(m * n), an extra queue with size of (m * n) will be used in worst case
*/

class Node {
    public int i;
    public int j;
    
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class WallsAndGatesV2 {
    
    public static void fillEmptyRoom(int[][] matrix) {
        // STEP 1
        Queue<Node> queue = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[] directions = {-1, 0, 1, 0, -1};
        int curDistance = 0;
        Node curLevelNode = null;
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == 0) {
                    // STEP 5
                    queue.offer(new Node(i, j));
                }
            }
        }
        // STEP 6
        while (!queue.isEmpty()) {
            // STEP 7
            int curLevelSize = queue.size();
            // STEP 8
            for (int i = 0; i < curLevelSize; i++) {
                // STEP 9
                curLevelNode = queue.poll();
                // STEP 10
                int curI = curLevelNode.i;
                int curJ = curLevelNode.j;
                // STEP 11
                for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                    // STEP 12
                    int nextI = curI + directions[idxDirection];
                    int nextJ = curJ + directions[idxDirection + 1];
                    // STEP 13
                    if (isWithinRange(nextI, nextJ, m, n)) {
                        // STEP 14
                        if (isRoomNotUpdated(matrix, nextI, nextJ)) {
                            // STEP 15
                            matrix[nextI][nextJ] = curDistance + 1;
                            // STEP 16
                            queue.offer(new Node(nextI, nextJ));
                        }
                    }
                }
            }
            // STEP 17
            curDistance++;
        }
    }
    
    public static boolean isWithinRange(int i, int j, int m, int n) {
        if (i < 0 || i >= m ||
            j < 0 || j >= n) {
            return false;
        }
        return true;
    }
    
    public static boolean isRoomNotUpdated(int[][] matrix, int i, int j) {
        return matrix[i][j] == Integer.MAX_VALUE;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
            {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
            {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}
        };
        System.out.println("matrix: \n" + Arrays.deepToString(matrix));
        fillEmptyRoom(matrix);
        System.out.println("after fill empty room, matrix: " + Arrays.deepToString(matrix));
        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);
    }
}
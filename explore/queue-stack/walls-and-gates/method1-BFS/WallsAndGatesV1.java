import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        queue(Queue<Node>): a queue for BFS
        m(int): the number of rows in matrix
        n(int): the number of cols in matrix
        rowDirections(int[]): the index of row for four directions, top, right, down, left
        colDirections(int[]): the index of col for four directions, top, right, down, left
        curNode(Node): a pointer for iterating each node in queue
    DESCRIPTION:
        STEP 1
        Initialize Node class
        Initialize queue to Queue<Node>
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize rowDirections to {-1, 0, 1, 0};
        Initialize colDirections to {0, 1, 0, -1};
        Initialize curNode to null
        STEP 2
        Iterate each row in matrix with index i
            STEP 3
            Iterate each row in matrix with index j
                STEP 4
                If matrix[i][j] == 0, (meaning current position is gate)
                    STEP 5
                    Push new Node(i, j, matrix[i][j]) to queue to add all gate nodes into queue, the coordinate (i, j, matrix[i][j]) indicate the row index, col index and distance from nearest gate respectively
                        queue.offer(new Node(i, j, matrix[i][j]));
        
        Now queue contains all gate nodes
        
        STEP 6
        Loop while queue is not empty,
            STEP 7
            Pop front element from queue and assign it to curNode
                curNode = queue.poll();
            STEP 8
            Get i, j, distance of curNode and assign them to curI, curJ, curDistance respectively
                int curI = curNode.i;
                int curJ = curNode.j;
                int curDistance = curNode.distance;
            STEP 9
            Iterate each direction of four directions with index idxDirection
                STEP 10
                Calculate nextI, nextJ for next move under current direction
                    int nextI = curI + rowDirections[idxDirection];
                    int nextJ = curJ + colDirections[idxDirection];
                STEP 11
                If isWithinRange(nextI, nextJ, m, n), (check next position is within boundary of matrix by calling method)
                    STEP 12
                    If isRoomNotUpdated(matrix, nextI, nextJ), (check next position is room not yet updated)
                        STEP 13
                        Update the value at next position with (curDistance + 1)
                            matrix[nextI][nextJ] = curDistance + 1;
                        STEP 14
                        Add next position to queue with new Node(nextI, nextJ, matrix[nextI][nextJ])
                            queue.offer(new Node(nextI, nextJ, matrix[nextI][nextJ]))
    TIME:
        O(m * n), m is the row number, n is the col number in matrix, in worst case, each element will be scanned once
    SPACE:
        O(m * n), an extra queue with size of (m * n) will be used in worst case
*/

class Node {
    public int i;
    public int j;
    public int distance;
    
    public Node(int i, int j, int distance) {
        this.i = i;
        this.j = j;
        this.distance = distance;
    }
}

public class WallsAndGatesV1 {
    
    public static void fillEmptyRoom(int[][] matrix) {
        // STEP 1
        Queue<Node> queue = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        Node curNode = null;
        int[] rowDirections = {1, 0, -1, 0};
        int[] colDirections = {0, 1, 0, -1};
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == 0) {
                    // STEP 5
                    queue.offer(new Node(i, j, matrix[i][j]));
                }
            }
        }
        // STEP 6
        while (!queue.isEmpty()) {
            // STEP 7
            curNode = queue.poll();
            // STEP 8
            int curI = curNode.i;
            int curJ = curNode.j;
            int curDistance = curNode.distance;
            // STEP 9
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 10
                int nextI = curI + rowDirections[idxDirection];
                int nextJ = curJ + colDirections[idxDirection];
                // STEP 11
                if (isWithinRange(nextI, nextJ, m, n)) {
                    // STEP 12
                    if (isRoomNotUpdated(matrix, nextI, nextJ)) {
                        // STEP 13
                        matrix[nextI][nextJ] = curDistance + 1;
                        // STEP 14
                        queue.offer(new Node(nextI, nextJ, matrix[nextI][nextJ]));
                    }
                }
            }
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
    }
}
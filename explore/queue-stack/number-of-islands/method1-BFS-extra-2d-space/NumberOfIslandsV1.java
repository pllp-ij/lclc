import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        islandsNum(int): the total number islands, also for final return result
        m(int): the row number of matrix
        n(int): the col number of matrix
        visited(int[][]): a cache matrix to indicate if current position is already visited
        queue(Queue<Node>): a queue used when do BFS
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize visited with same size of matrix
            int[][] visited = new int[m][n]
        STEP 2
        Iterate each row of matrix with i
            STEP 3
            Iterate each col of matrix with j
                STEP 4
                If matrix[i][j] == '1' and visited[i][j] != 1, (meaning current position(i, j) is valid and it is not visited yet)
                    STEP 5
                    Do BFS from current position (i, j)
                        BFS(matrix, i, j, m, n, visited)
                    STEP 6
                    Increase islandsNum by one
                        islandsNum++;
        STEP 7
        Return islandsNum
        
        -FUNC void BFS(char[][] matrix, int i, int j, int m, int n, int[][] visited)
        STEP 1
        Initialize Node class with properties i and j
        Initialize queue to Queue<Node>
        Initialize directions to {-1, 0, 1, 0, -1}
        Initialize curNode to null
        Initialize nextI to 0
        Initialize nextJ to 0
        STEP 2
        Create new node from current position (i, j) and push it into queue
            queue.offer(new Node(i, j));
        STEP 3
        Loop while queue is not empty
            STEP 4
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
            STEP 5
            If isInInvalidRegion(matrix, curNode.i, curNode.j, m, n, visited), (meaning current position (curNode.i, curNode.j) is invalid)
                STEP 6
                Skip current iteration and jump to next iteration
                    continue;
            
            Now when code not enter into above if statement and reach to this line, it means current position is valid
            
            STEP 7
            Set current position (curNode.i, curNode.j) in visited to 1 to indicate current position is already visited
                visited[curNode.i][curNode.j] = 1;
            STEP 8
            Iterate each of four directions with idxDirection
                STEP 9
                Get (nextI, nextJ) from (curNode.i, curNode.j) using directions and idxDirection
                    nextI = curNode.i + directions[idxDirection];
                    nextJ = curNode.j + directions[idxDirection + 1];
                STEP 10
                Create new node from (nextI, nextJ) and push it into queue
                    queue.push(new Node(nextI, nextJ));
        
        -FUNC boolean isInInvalidRegion(char[][] matrix, int i, int j, int m, int n, int[][] visited)
        STEP 1
        If i < 0 || i >= m ||
           j < 0 || j >= n ||
           matrix[i][j] == '0' ||
           visited[i][j] == 1, (meaning current position (i, j) is invalid)
            STEP 2
            return true;
        STEP 3
        Return false;
    TIME:
        O(m * n), m is the row number of matrix, n is the col number of matrix
    SPACE:
        O(m * n + m * n), first (m * n) is for visited cache matrix, second (m * n) is for max number of nodes stored in queue in worst cases
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
                    BFS(matrix, i, j, m, n, visited);
                    // STEP 6
                    islandsNum++;
                }
            }
        }
        // STEP 7
        return islandsNum;
    }
    
    public static void BFS(char[][] matrix, int i, int j, int m, int n, int[][] visited) {
        // STEP 1
        Queue<Node> queue = new LinkedList<>();
        int[] directions = {-1, 0, 1, 0, -1};
        Node curNode = null;
        int nextI = 0;
        int nextJ = 0;
        // STEP 2
        queue.offer(new Node(i, j));
        // STEP 3
        while (!queue.isEmpty()) {
            // STEP 4
            curNode = queue.poll();
            // STEP 5
            if (isInInvalidRegion(matrix, curNode.i, curNode.j, m, n, visited)) {
                // STEP 6
                continue;
            }
            // STEP 7
            visited[curNode.i][curNode.j] = 1;
            // STEP 8
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 9
                nextI = curNode.i + directions[idxDirection];
                nextJ = curNode.j + directions[idxDirection + 1];
                // STEP 10
                queue.offer(new Node(nextI, nextJ));
            }
        }
    }
    
    public static boolean isInInvalidRegion(char[][] matrix, int i, int j, int m, int n, int[][] visited) {
        // STEP 1
        if (i < 0 || i >= m ||
            j < 0 || j >= n ||
            matrix[i][j] == '0' ||
            visited[i][j] == 1) {
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
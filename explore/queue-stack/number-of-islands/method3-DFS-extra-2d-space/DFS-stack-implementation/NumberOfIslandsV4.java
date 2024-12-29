import java.util.Arrays;
import java.util.Stack;

/*
    VARS:
        islandsNum(int): the number of islands, also for final return
        m(int): the row number of current matrix
        n(int): the col number of current matrix
        visited(int[][]): a cache matrix to indicate whether current position is already visited
        stack(Stack<Node>): a stack for iterating each node when do DFS
    DESCRIPTION:
        STEP 1
        Initialize islandsNum to 0
        Initialize m to matrix.length
        Initialize n to matrix[0].length
        Initialize visited to new int[m][n]
        STEP 2
        Iterate each row of matrix with i
            STEP 3
            Iterate each col of matrix with j
                STEP 4
                If matrix[i][j] == '1' and visited[i][j] != 1, (meaning current position (i, j) is in an island and it is not visited yet)
                    STEP 5
                    Do DFS from current position (i, j) using stack implementation
                        DFS(matrix, i, j, m, n, visited);
                    STEP 6
                    Increase islandsNum by one after finishing above DFS, meaning each node in connected region are already visited
                        islandsNum++;
        STEP 7
        Return islandsNum
        
        -FUNC void DFS(char[][] matrix, int i, int j, int m, int n, int[][] visited)
        STEP 1
        Create class Node with property i and j
        STEP 2
        Initialize stack as Stack<Node>
            Stack<Node> stakc = new Stack<>();
        Initialize directions as {-1, 0, 1, 0, -1}
        Initialize curNode to null
        Initialize nextI to 0
        Initialize nextJ to 0
        STEP 3 - OPTIONAL for STEP 3, but STEP 4 and STEP 5 are still needed
        If isInValidRegion(matrix, i, j, m, n, visited), (is the starting node is valid, this is optional, because the first node should always be true)            
            STEP 4
            Add current position node Node(i, j) into stack
                stack.push(new Node(i, j));
            STEP 5
            Set current position (i, j) in visited to 1
                visited[i][j] = 1;
                
        Above STEP 3, STEP 4 and STEP 5 always check all next state nodes in next level when it is now on current level, in this case, the "STEP 3 -> STEP 4 -> STEP 5" is the operation for current dummy head, so does it in logic within while loop for stack,
        so "STEP 3 -> STEP 4 -> STEP 5" is matching to "STEP 10 -> STEP 11 -> STEP 12"
                
        STEP 6
        Loop while !stack.isEmpty(), (when stack is not empty)
            STEP 7
            Pop top node from stack and assign it to curNode
                curNode = stack.pop();
            STEP 8
            Iterate each direction of four directions with idxDirection
                STEP 9
                Generate Node(nextI, nextJ) from (curNode.i, curNode.j) using directions and idxDirection
                    nextI = curNode.i + directions[idxDirection];
                    nextJ = curNode.j + directions[idxDirection + 1];
                STEP 10
                If isInValidRegion(matrix, nextI, nextJ, m, n, visited), (meaning next position (nextI, nextJ) is valid)
                    STEP 11
                    Create new Node(nextI, nextJ) and push it into stack
                        stack.push(new Node(nextI, nextJ));
                    STEP 12
                    Set position (nextI, nextJ) in visited to 1
                        visited[nextI][nextJ] = 1;

        -FUNC boolean isInInvalidRegion(char[][] matrix, int i, int j, int m, int n, int[][] visited)
        STEP 1
        If i < 0 || i >= m ||
           j < 0 || j >= n ||
           matrix[i][j] == '0' ||
           visited[i][j] == 1, (meaing current position(i, j) is not valid)
            STEP 2
            Return true;
        STEP 3
        Return false;
    TIME:
        O(m * n), m is the row number of matrix, n is the col number of matrix
    SPACE:
        O(m * n + m * n) ~ O(m * n), an extra visited matrix used with same size of matrix(m * n), and second (m * n) represent the maximum number of node in matrix to be pushed into stack in worst case
*/

class Node {
    public int i;
    public int j;
    
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class NumberOfIslandsV4 {
    
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
        Stack<Node> stack = new Stack<>();
        int[] directions = {-1, 0, 1, 0, -1};
        Node curNode = null;
        int nextI = 0;
        int nextJ = 0;
        // STEP 3, OPTIONAL because for first node, if statement will always be true, so STEP 4 and STEP 5 can be placed outside the if statement
        if (isInValidRegion(matrix, i, j, m, n, visited)) {
            // STEP 4
            stack.push(new Node(i, j));
            // STEP 5
            visited[i][j] = 1;
        }
        // STEP 6
        while (!stack.isEmpty()) {
            // STEP 7
            curNode = stack.pop();
            // STEP 8
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 9
                nextI = curNode.i + directions[idxDirection];
                nextJ = curNode.j + directions[idxDirection + 1];
                // STEP 10
                if (isInValidRegion(matrix, nextI, nextJ, m, n, visited)) {
                    // STEP 11
                    stack.push(new Node(nextI, nextJ));
                    // STEP 12
                    visited[nextI][nextJ] = 1;
                }
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
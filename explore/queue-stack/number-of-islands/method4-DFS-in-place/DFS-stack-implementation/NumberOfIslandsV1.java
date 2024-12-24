import java.util.Arrays;
import java.util.Stack;

/*
    NOTE: The corresponding DFS matching relationship are,
        DFS-stack-implementation/NumberOfIslandsV1 -> DFS-recursion-implementation/NumberOfIslandsV1
        DFS-stack-implementation/NumberOfIslandsV2 -> DFS-recursion-implementation/NumberOfIslandsV2
    VARS:
        islandsNum(int): the number of islands, also for final return
        m(int): the row number of current matrix
        n(int): the col number of current matrix
        stack(Stack<Node>): a stack for iterating each node when do DFS
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
                If matrix[i][j] == '1', (meaning current position (i, j) is in an island)
                    STEP 5
                    Do DFS from current position (i, j) using stack implementation
                        DFS(matrix, i, j, m, n);
                    STEP 6
                    Increase islandsNum by one after finishing above DFS, meaning each node in connected region are already visited
                        islandsNum++;
        STEP 7
        Return islandsNum
        
        -FUNC void DFS(char[][] matrix, int i, int j, int m, int n)
        STEP 1
        Create class Node with property i and j
        STEP 2
        Initialize stack as Stack<Node>
            Stack<Node> stakc = new Stack<>();
        Initialize directions as {-1, 0, 1, 0, -1}
        Initialize curNode to null
        Initialize nextI to 0
        Initialize nextJ to 0
        STEP 3
        Add current position node Node(i, j) into stack
            stack.push(new Node(i, j));
        STEP 4
        Loop while !stack.isEmpty(), (when stack is not empty)
            STEP 5
            Pop top node from stack and assign it to curNode
                curNode = stack.pop();
            STEP 6
            If isInInvalidRegion(matrix, curNode.i, curNode.j, m, n), (meaning if current position(i, j) is not valid, corresponding to the recursion implemetation V1 solution, in which, a check on current node is done in STEP 1 and STEP 2)
                STEP 7
                Skip current iteration and jump to next iteration
                    continue;
            STEP 8
            Since the code not enter into the if statement above and reach to this line of code, means the position (curNode.i, curNode.j) is valid, so set the matrix[curNode.i][curNode.j] to '0' to indicate the point is now invalid
                matrix[curNode.i][curNode.j] = '0';
            STEP 9
            Iterate each direction of four directions with idxDirection
                STEP 10
                Generate Node(nextI, nextJ) from (curNode.i, curNode.j) using directions and idxDirection
                    nextI = curNode.i + directions[idxDirection];
                    nextJ = curNode.j + directions[idxDirection + 1];
                STEP 11
                Create new Node(nextI, nextJ) and push it into stack
                    stack.push(new Node(nextI, nextJ));

        -FUNC boolean isInInvalidRegion(char[][] matrix, int i, int j, int m, int n)
        STEP 1
        If i < 0 || i >= m ||
           j < 0 || j >= n ||
           matrix[i][j] == '0', (meaing current position(i, j) is not valid)
            STEP 2
            Return true;
        STEP 3
        Return false;
    TIME:
        O(m * n), m is the row number of matrix, n is the col number of matrix
    SPACE:
        O(m * n), for maximum number of node in matrix to be pushed into stack in worst case
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
        // STEP 2
        for (int i = 0; i < m; i++) {
            // STEP 3
            for (int j = 0; j < n; j++) {
                // STEP 4
                if (matrix[i][j] == '1') {
                    // STEP 5
                    DFS(matrix, i, j, m, n);
                    // STPE 6
                    islandsNum++;
                }
            }
        }
        // STEP 7
        return islandsNum;
    }
    
    public static void DFS(char[][] matrix, int i, int j, int m, int n) {
        // STEP 1
        Stack<Node> stack = new Stack<>();
        int[] directions = {-1, 0, 1, 0, -1};
        Node curNode = null;
        int nextI = 0;
        int nextJ = 0;
        // STEP 2
        stack.push(new Node(i, j));
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curNode = stack.pop();
            // STEP 5
            if (isInInvalidRegion(matrix, curNode.i, curNode.j, m, n)) {
                // STEP 6
                continue;
            }
            // STEP 7
            matrix[curNode.i][curNode.j] = '0';
            // STEP 8
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 9
                nextI = curNode.i + directions[idxDirection];
                nextJ = curNode.j + directions[idxDirection + 1];
                // STEP 10
                stack.push(new Node(nextI, nextJ));
            }
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
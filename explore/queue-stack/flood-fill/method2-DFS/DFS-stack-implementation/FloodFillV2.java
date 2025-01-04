import java.util.Arrays;
import java.util.Stack;;

/*
    VARS:
        stack(Stack<Node>): a stack used when do DFS
        m(int): row number of image
        n(int): col number of image
        oldColor(int): the initial color within connected region in image
        newColor(int): the color used to fill connected region
        directions(int[]): for next possible directions
        curNode(Node): to receive the popped node from stack
    DESCRIPTION:
        STEP 1
        Initialize m to image.length
        Initialize n to image[0].length
        Initialize oldColor to image[sr][sc]
        Initialize newColor to color
        STEP 2
        Do DFS without return value
            DFS(image, m, n, sr, sc, oldColor, newColor);
            
        -FUNC void DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor)
        STEP 1
        Initialize Node class with i(int), j(int) properties
        Initialize stack to Stack<>();
        Initialize directions to {-1, 0, 1, 0, -1};
        Initialize curNode to null
        STEP 2
        Push new Node(i, j) into stack
        STEP 3
        Loop while !stack.isEmpty(), (when stack is not empty)
            STEP 4
            Pop front node from stack and assign it to curNode,
                curNode = stack.pop();
            STEP 7
            Set image[curNode.i][curNode.j] to newColor
                image[curNode.i][curNode.j] = newColor;
            STEP 8
            Iterate each direction with idxDirection from 0 to 3
                STEP 9
                Calculate (nextI, nextJ) from curNode using directions and idxDirection
                    int nextI = curNode.i + directions[idxDirection];
                    int nextJ = curNode.j + directions[idxDirection + 1];
                STEP 10
                Create next new Node(nextI, nextJ) and assign it to nextNode
                    nextNode = new Node(nextI, nextJ);
                STEP 11
                If isValid(image, m, n, nextNode, oldColor, newColor)                
                    STEP 12
                    Push new Node(nextI, nextJ) into stack
                        stack.push(nextI, nextJ);
                    
        -FUNC boolean isValid(int[][] image, int m, int n, Node nextNode, int oldColor, int newColor)
        STEP 1
        Get nextI, nextJ from nextNode
            int nextI = nextNode.i;
            int nextJ = nextNode.j;
        STEP 2
        If nextI < 0 && nextI >= m &&
           nextJ < 0 && nextJ >= n &&
           image[nextI][nextJ] != newColor &&
           image[nextI][nextJ] == oldColor, (first two conditions to check if position is within valid range, and third condition is useless when combined with fourth condition, the reason keep it is for the unify format with V1
            STEP 2
            Return true
        STEP 3
        Return false;
    TIME:
        O(m * n), each node in image will be visited once
    SPACE:
        O(m * n), in worst case, there are at most (m * n) nodes should be pushed into stack
*/

class Node {
    public int i;
    public int j;
    
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class FloodFillV2 {
    
    public static void floodFill(int[][] image, int sr, int sc, int color) {
        // STEP 1
        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];
        int newColor = color;
        // STEP 2
        DFS(image, m, n, sr, sc, oldColor, newColor);
    }
    
    public static void DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor) {
        // STEP 1
        Stack<Node> stack = new Stack<>();
        int[] directions = {-1, 0, 1, 0, -1};
        Node curNode = null;
        Node nextNode = null;
        // STEP 2
        stack.push(new Node(i, j));
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curNode = stack.pop();
            // STEP 5
            image[curNode.i][curNode.j] = newColor;
            // STEP 6
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 7
                int nextI = curNode.i + directions[idxDirection];
                int nextJ = curNode.j + directions[idxDirection + 1];
                // STEP 8
                nextNode = new Node(nextI, nextJ);
                // STEP 9
                if (isValid(image, m, n, nextNode, oldColor, newColor)) {
                    // STEP 10
                    stack.push(nextNode);
                }
            }
        }
    }
    
    public static boolean isValid(int[][] image, int m, int n, Node nextNode, int oldColor, int newColor) {
        // STEP 1
        int nextI = nextNode.i;
        int nextJ = nextNode.j;
        // STEP 2
        if (nextI >= 0 && nextI < m &&
            nextJ >= 0 && nextJ < n &&
            image[nextI][nextJ] != newColor &&
            image[nextI][nextJ] == oldColor) {
            // STEP 3
            return true;
        }
        // STEP 4
        return false;
    }

    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int[] node = {1, 1, 2};
        
        // int[][] image = {
            // {0, 0, 0},
            // {0, 0, 0}
        // };
        // int[] node = {0, 0, 0};
        
        System.out.println("initial image: \n" + Arrays.deepToString(image));
        floodFill(image, node[0], node[1], node[2]);
        System.out.println("after fill from position (" + node[0] + ", " + node[1] + ") with color=" + node[2] + ", result is: \n" + Arrays.deepToString(image));
    }
}
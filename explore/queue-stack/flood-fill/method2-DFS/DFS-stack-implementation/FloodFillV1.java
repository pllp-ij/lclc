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
            STEP 5
            If isNotValid(image, m, n, curNode, oldColor, newColor)
                STEP 6
                Skip current iteration
                    continue;
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
                Push new Node(nextI, nextJ) into stack
                    stack.push(nextI, nextJ);
                    
        -FUNC boolean isNotValid(int[][] image, int m, int n, Node curNode, int oldColor, int newColor)
        STEP 1
        Get i, j from curNode
            int i = curNode.i;
            int j = curNode.j;
        STEP 2
        If i < 0 || i >= m ||
           j < 0 || j >= n ||
           image[i][j] == newColor ||
           image[i][j] != oldColor, (first two conditions is to check the boundary validation, third condition is for the cases the starting point pixel color is equal to newColor, the fourth color is combined with third condition that if starting point is not initially equal to newColor, then it must equal to oldColor)
            STEP 3
            Return true;
        STEP 4
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

public class FloodFillV1 {
    
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
        // STEP 2
        stack.push(new Node(i, j));
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curNode = stack.pop();
            // STEP 5
            if (isNotValid(image, m, n, curNode, oldColor, newColor)) {
                // STPE 6
                continue;
            }
            // STEP 7
            image[curNode.i][curNode.j] = newColor;
            // STEP 8
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 9
                int nextI = curNode.i + directions[idxDirection];
                int nextJ = curNode.j + directions[idxDirection + 1];
                // STEP 10
                stack.push(new Node(nextI, nextJ));
            }
        }
    }
    
    public static boolean isNotValid(int[][] image, int m, int n, Node curNode, int oldColor, int newColor) {
        // STEP 1
        int i = curNode.i;
        int j = curNode.j;
        // STEP 2
        if (i < 0 || i >= m ||
            j < 0 || j >= n ||
            image[i][j] == newColor ||
            image[i][j] != oldColor) {
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
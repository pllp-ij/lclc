import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        queue(Queue<Integer>): a queue used whne do BFS
        m(int): the row number of image
        n(int): the col number of image
        oldColor(int): the initial color of all connected nodes
        newColor(int): the color should be used to fill all connected nodes
        directions(int[]): for listing all possible next step
    DESCRIPTION:
        STEP 1
        Initialize m to image.length
        Initialize n to image[0].length
        Initialize oldColor to image[sr][sc]
        Initialize newColor to color
        STEP 2
        Do BFS without return value
            BFS(image, m, n, sr, sc, oldColor, newColor);
            
        -FUNC BFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor)
        STEP 1
        Initialize class Node with i(int), j(int)properties
        Initialize queue to LinkedList<>()
        Initialize directions to {-1, 0, 1, 0, -1}
        Initialize curNode to null with Node type
        Initialize nextNode to null with Node type
        STEP 2
        Push new Node(i, j) into queue
            queue.offer(new Node(i, j));
        STEP 3
        Loop while queue is not empty
            STPE 4
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
            STEP 5
            Reset (curNode.i, curNode.j) in image to newColor
                image[curNode.i][curNode.j] = newColor;
            STEP 6
            Iterate all next possible directions with idxDirection from 0 to 3
                STEP 7
                Get (nextI, nextJ) from (curNode.i, curNode.j) and using directions, idxDirection
                    int nextI = curNode.i + directions[idxDirection];
                    int nextJ = curNode.j + directions[idxDirection + 1];
                STEP 8
                Create next new Node(nextI, nextJ) and assign it to nextNode
                    nextNode = new Node(nextI, nextJ);
                STEP 9
                If isValid(image, m, n, nextNode, oldColor, newColor), (meaning if next state is valid)
                    STEP 10
                    Push new Node(nextI, nextJ) into queue
                        queue.offer(nextNode);
            
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
        O(m * n), in worst case, there are at most (m * n) nodes should be enqueued into queue
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
        BFS(image, m, n, sr, sc, oldColor, newColor);
    }
    
    public static void BFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor) {
        // STEP 1
        Queue<Node> queue = new LinkedList<>();
        int[] directions = {-1, 0, 1, 0, -1};
        Node curNode = null;
        Node nextNode = null;
        // STEP 2
        queue.offer(new Node(i, j));
        // STEP 3
        while (!queue.isEmpty()) {
            // STEP 4
            curNode = queue.poll();
            // STEP 5
            image[curNode.i][curNode.j] = newColor;
            // STEP 6
            for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
                // STEP 7
                int nextI = curNode.i + directions[idxDirection];
                int nextJ=  curNode.j + directions[idxDirection + 1];
                // STEP 8
                nextNode = new Node(nextI, nextJ);
                // STEP 9
                if (isValid(image, m, n, nextNode, oldColor, newColor)) {
                    // STEP 10
                    queue.offer(nextNode);
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
            // STEP 2
            return true;
        }
        // STEP 3
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
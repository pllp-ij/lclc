import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        squares(int[]): store all perfect squares from 1 to the number than not more than
        queue(Queue<Integer>): a queue for storing all nodes when doing BFS
        depth(int): the depth current iterated, which indicate the number of perfect squares needed, also the final return
    DESCRIPTION
        STEP 1
        Create squares from n by calling method, after which, squares contains all perfect squares that not more than n
            int[] squares = generateSquares(n)
        Initialize queue to Queue<Integer>
        Initialize depth to 1
        Initialize curNode to -1
        Initialize nextNode to -1
        STEP 2
        Add n to queue
            queue.offer(n)
        STEP 3
        Loop while queue is not empty
            STEP 4
            Calculate size of current level and assign it to levelSize
                int levelSize = queue.size();
            STEP 5
            Iterate each node in current level with idx
                STEP 6
                Pop front node from queue and assign it to curNode
                    curNode = queue.poll();
                STEP 7
                If curNode is contained in squares, (meaning current node is perfect square itself)
                    STEP 8
                    Return depth
                STEP 9
                Iterate each perfect square in squares from 1 to n with idxPerfectSquare, and squares[idxPerfectSquare] <= curNode
                    STEP 10
                    Generate nextNode from curNode using squares and idxPerfectSquare and assign it to nextNode
                        nextNode = generateNextNode(curNode, squares, idxPerfectSquare);
                    STEP 11
                    Add nextNode to queue
                        queue.offer(nextNode)
            STEP 12
            Increase depth by one
                depth++;
        STEP 13
        Return -1
        
        -FUNC int[] generateSquares(int n)
        STEP 1
        Initialize squares to new int[(int) Math.sqrt(n) + 1]
            int[] squares = new int[(int) Math.sqrt(n) + 1]
        STEP 2
        Iterate each position of squares from 1 to n with idx
            STEP 3
            Assign (idx * idx) to squares[idx]
                squares[idx] = (idx * idx);
        STEP 4
        Return squares
        
        -FUNC boolean isContained(int[] squares, int curNode)
        STEP 1
        Iterate each perfect square in squares from 1 to (squares.length - 1) with idx
            STEP 2
            If squares[idx] == curNode, (meaning curNode is contained in squares)
                STEP 3
                Return true;
        STEP 4
        Return false;
        
        -FUNC int generateNextNode(int curNode, int[] squares, int idxPerfectSquare)
        STEP 1
        return curNode - squares[idxPerfectSquare];
    TIME:
        O(n^(((h+1)/2) - 1)/(n^(1/2) - 1))
    SPACE:
        O(n^(h/2))
*/

class PerfectSquaresV1 {
    
    public static int getPerfectSquaresNum(int n) {
        // STEP 1
        int[] squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        int depth = 1;
        int curNode = -1;
        int nextNode = -1;
        // STEP 2
        queue.offer(n);
        // STEP 3
        while (!queue.isEmpty()) {
            // STEP 4
            int levelSize = queue.size();
            // STEP 5
            for (int idx = 0; idx < levelSize; idx++) {
                // STEP 6
                curNode = queue.poll();
                // STEP 7
                if (isContained(squares, curNode)) {
                    // STEP 8
                    return depth;
                }
                // STEP 9
                for (int idxPerfectSquare = 1; idxPerfectSquare < squares.length && squares[idxPerfectSquare] <= curNode; idxPerfectSquare++) {
                    // STEP 10
                    nextNode = generateNextNode(curNode, squares, idxPerfectSquare);
                    // STEP 11
                    queue.offer(nextNode);
                }
            }
            // STEP 12
            depth++;
        }
        // STEP 13
        return -1;
    }
    
    public static boolean isContained(int[] squares, int curNode) {
        // STEP 1
        for (int idx = 1; idx < squares.length; idx++) {
            // STEP 2
            if (squares[idx] == curNode) {
                // STEP 3
                return true;
            }
        }
        // STEP 4
        return false;
    }
    
    public static int generateNextNode(int curNode, int[] squares, int idxPerfectSquare) {
        return curNode - squares[idxPerfectSquare];
    }
    
    public static int[] generateSquares(int n) {
        // STEP 1
        int[] squares = new int[(int) Math.sqrt(n) + 1];
        // STEP 2
        for (int idx = 1; idx < squares.length; idx++) {
            // STEP 3
            squares[idx] = (idx * idx);
        }
        // STEP 4
        return squares;
    }
    
    public static void main(String[] args) {
        // int n = 12;
        int n = 13;
        
        int result = getPerfectSquaresNum(n);
        System.out.println(n + " can be decomposed into least perfect squares number: " + result);
    }
}
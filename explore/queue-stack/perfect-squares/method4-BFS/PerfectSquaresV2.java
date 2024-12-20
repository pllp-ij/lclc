import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

/*
    Compared to V1, V2 use a hashset for squares to check if a int is contained in it in O(1)
    VARS:
        queue(Queue<Integer>): a queue for storing all nodes when do BFS
        squares(Set<Integer>): contain all perfect square that not more than n
        depth(int): denote current level when found the value contains in square when do BFS, also the final return result
    DESCRIPTION
        STEP 1
        Initialize queue to Queue<Integer>
        Create squares by calling method using nodes
            Set<Integer> squares = generateSquares(n);
        Initialize depth to 1
        Initialize curNode to -1
        Initialize nextNode to -1
        STEP 2
        Add n to queue
            queue.offer(n)
        STEP 3
        Loop while queue is not empty
            STEP 4
            Calculate the size of current level and assign it to levelSize
                int levelSize = queue.size();
            STEP 5
            Iterate each node in current level with idx from 0 to (levelSize - 1)
                STEP 6
                Pop front node from queue and assign it to curNode
                    curNode = queue.poll();
                STEP 7
                If squares.contains(curNode), (meaning curNode is a perfect square)
                    STEP 8
                    Return depth
                STEP 9
                Iterate each perfect square number in squares with squareNum
                    STEP 10
                    If squareNum <= curNode,
                        STEP 11
                        Generate nextNode by substracting squareNum from curNode
                            nextNode = curNode - squareNum;
                        STEP 12
                        Add nextNode to queue
                            queue.offer(nextNode)
            STEP 13
            Increase depth by one
                depth++;
        STEP 14
        Return -1
        
        -FUNC Set<Integer> generateSquares(int n)
        STEP 1
        Initialize squares as Set<Integer>
            Set<Integer> squares = new HashSet<>();
        STEP 2
        Iterate idx from 1 to when (idx * idx) <= n, (so in order to find the largest number that not more than n)
            STEP 3
            Add (idx * idx) to squares
                squares.add(idx * idx);
        STEP 4
        Return squares
    TIME:
        O((n^((h+1)/2) - 1)/(n^(1/2) - 1))
    SPACE:
        O(n^(h/2))
*/

class PerfectSquaresV2 {
    
    public static int getPerfectSquaresNum(int n) {
        // STEP 1
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> squares = generateSquares(n);
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
                if (squares.contains(curNode)) {
                    // STEP 8
                    return depth;
                }
                // STEP 9
                for (Integer square: squares) {
                    // STEP 10
                    if (square <= curNode) {
                        // STEP 11
                        nextNode = curNode - square;
                        // STEP 12
                        queue.offer(nextNode);
                    }
                }
            }
            // STEP 13
            depth++;
        }
        // STEP 14
        return -1;
    }
    
    public static Set<Integer> generateSquares(int n) {
        // STEP 1
        Set<Integer> squares = new HashSet<>();
        // STEP 2
        for (int idx = 1; idx * idx <= n; idx++) {
            // STEP 3
            squares.add(idx * idx);
        }
        // STEP 4
        return squares;
    }
    
    public static void main(String[] args) {
        int n = 12;
        // int n = 13;
        
        int result = getPerfectSquaresNum(n);
        System.out.println(n + " can be decomposed into least perfect squares number: " + result);
    }
}
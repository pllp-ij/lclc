import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
    VARS:
        queue(Queue<String>): a queue for storing all combinations when BFS
        visited(Set<String>): a hash set to denote all visited nodes
        turnsNum(int): the total steps moving from start to target without hitting deadends
        curCombination(String): the current combination popped from queue
        nextCombination(String): the next combination generated from curCombination
    DESCRIPTION:
        STEP 1
        Initialize visited from deadends
            Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        STEP 2
        If visited.contains(start), (meaning start combination is a deadend, so it's impossible to begin)
            STEP 3
            Return -1
        STEP 4
        If target == start, (meaning start combination is target, directly return 0)
            STEP 5
            Return 0
        STEP 6
        Do BFS and assign the needed steps to turnsNum
            int turnsNum = BFS(start, target, visited);
        STEP 7
        return turnsNum;
        
        -FUNC int BFS(String start, String target, Set<String> visited)
        STEP 1
        Initialize queue to Queue<String>
        Initialize turnsNum to 0
        Initialize curCombination to null with String type
        Initialize nextCombination to null with String type
        STEP 2
        Add start combination to queue
            queue.offer(start);
        STEP 3
        Add start to visited
            visited.add(start);
        STEP 4
        Loop while queue is not empty
            STEP 5
            Calculate total nodes number in current level
            int levelSize = queue.size();
            STEP 6
            Iterate each node in current level with idx
                STEP 7
                Pop front node from queue and assign it to curCombination
                    curCombination = queue.poll();
                STEP 8
                If curCombination.equals(target), (meaning target is found)
                    STEP 9
                    Return turnsNum
                STEP 10
                Iterate each wheel with idxWheel from 0 to 3
                    STEP 11
                    Iterate direction with idxDirection for two values: from -1 to 1
                        STEP 12
                        Get nextCombination from curCombination using idxWheel and idxDirection
                            nextCombination = getNextCombination(curCombination, idxWheel, idxDirection);
                        STEP 13
                        If !visited.contains(nextCombination), (meaning next combination is not visited)
                            STEP 14
                            Add nextCombination to queue
                                queue.offer(nextCombination);
                            STEP 15
                            Add nextCombination to visited to mark it as visited
                                visited.add(nextCombination)
            STEP 16
            Increase turnsNum by one after iterating all combinations in current level
                turnsNum++;
        
        Now if queue is empty and nothing returned when iterating it, return -1
        
        STEP 17
        Return -1;
        
        -FUNC String getNextCombination(String curCombination, int idxWheel, int idxDirection) 
        STEP 1
        Create copy string from curCombination and assign it to nextCombination
            StringBuilder nextCombination = new StringBuilder(curCombination);
        STEP 2
        Update character at idxWheel with value equal to idxDirection in nextCombination and assign it to updatedChar
            char updatedChar = (char) ((nextCombination.charAt(idxWheel)  - '0' + idxDirection + 10) % 10 + '0');
        STEP 3
        Change character at idxWheel to updatedChar for nextCombination
            nextCombination.setCharAt(idxWheel, updatedChar);
        STEP 4
        Return nextCombination.toString();
    TIME:
        O(dw + n^w * 2w), dw for copy deadends to visited, n^w for all combinations in wrost case, 2w for under each combination there are 2w next combinations
    SPACE:
        O(dw + (n^w + 2w) + (n^w + 2w)), dw for storing initial deadends in visited, first (n^w + 2w) for storing all combinations in visited in wrost case, second (n^w + 2w) for enqueue all combinations in queue
*/

class OpenTheLockV1 {
    
    public static int openLockWithLeastSteps(String[] deadends, String start, String target) {
        // STEP 1
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        // STEP 2
        if (visited.contains(start)) {
            // STEP 3
            return -1;
        }
        // STEP 4
        if (target == start) {
            // STEP 5
            return 0;
        }
        // STEP 6
        int turnsNum = BFS(start, target, visited);
        // STEP 7
        return turnsNum;
    }
    
    public static int BFS(String start, String target, Set<String> visited) {
        // STEP 1
        Queue<String> queue = new LinkedList<>();
        int turnsNum = 0;
        String curCombination = null;
        String nextCombination = null;
        // STEP 2
        queue.offer(start);
        // STEP 3
        visited.add(start);
        // STEP 4
        while (!queue.isEmpty()) {
            // STEP 5
            int levelSize = queue.size();
            // STEP 6
            for (int idx = 0; idx < levelSize; idx++) {
                // STEP 7
                curCombination = queue.poll();
                // STEP 8
                if (curCombination.equals(target)) {
                    // STEP 9
                    return turnsNum;
                }
                // STEP 10
                for (int idxWheel = 0; idxWheel < 4; idxWheel++) {
                    // STEP 11
                    for (int idxDirection = -1; idxDirection <= 1; idxDirection += 2) {
                        // STEP 12
                        nextCombination = getNextCombination(curCombination, idxWheel, idxDirection);
                        // STEP 13
                        if (!visited.contains(nextCombination)) {
                            // STEP 14
                            queue.offer(nextCombination);
                            // STEP 15
                            visited.add(nextCombination);
                        }
                    }
                }
            }
            // STEP 16
            turnsNum++;
        }
        // STEP 17
        return -1;
    }
    
    public static String getNextCombination(String curCombination, int idxWheel, int idxDirection) {
        // STEP 1
        StringBuilder nextCombination = new StringBuilder(curCombination);
        // STEP 2
        char updatedChar = (char) ((nextCombination.charAt(idxWheel) - '0' + idxDirection + 10) % 10 + '0');
        // STEP 3
        nextCombination.setCharAt(idxWheel, updatedChar);
        // STEP 4
        return nextCombination.toString();
    }
    
    public static void main(String[] args) {
        String start = "0000";
        
        // String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        // String target = "0202";
        
        String[] deadends = {"8888"};
        String target = "0009";
        
        // String[] deadends = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        // String target = "8888";
              
        int steps = openLockWithLeastSteps(deadends, start, target);
        if (steps != -1) {
            System.out.println("at least " + steps + " turns needed to move from " + start + " to " + target + ", with deadends as: " + Arrays.toString(deadends));
        } else {
            System.out.println("no path exists");
        }
        
    }
}
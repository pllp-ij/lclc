import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    NOTE:
        Compared to V1, V2 use 1d array to replace HashSet
    VARS:
        visitedArray(int[]): an array used as hash set to store all combinations have been visited
        queue(Queue<String>): a queue for storing combinations level by level
        turnsNum(int): the number of steps used until current combination from start
        curCombination(String): for storing the combination popped from queue
    DESCRIPTION:
        STEP 1
        Initialize visitedArray as new int[10000], from 0000 to 9999
        STEP 2
        Add all deadend in deadends to visitedArray by calling method
            addDeadends(visitedArray, deadends);
        STEP 3
        If visitedArray[0] == 1, (meaning start is deadend at the beginning, so there is no way to create a path from start to target)
            STEP 4
            Return -1
        STEP 5
        If start.equals(target), (meaning target is the start, so it need 0 step to move from start to target)
            STEP 6
            Return 0;
        STEP 7
        Do BFS and assign depth to turnsNum
            int turnsNum = BFS(start, target, visitedArray);
        STEP 8
        Return turnsNum;
        
        -FUNC int BFS(String start, String target, int[] visitedArray)
        STEP 1
        Initialize queue to Queue<String>
        Initialize turnsNum to 0
        Initialize curCombination to null with String type
        Initialize nextCombination to null with String type
        STEP 2
        Add start to queue
            queue.offer(start);
        STEP 3
        Convert start to int then push corresponding index in visitedArray to 1
            visitedArray[stoi(start)] = 1;
        STEP 4
        Loop while queue is not empty
            STEP 5
            Calculate the size of current level and assign it to levelSize
                int levelSize = queue.size();
            STEP 6
            Iterate each combinatino in current level with idx
                STEP 7
                Pop front node from queue and assign it to curCombination
                    curCombination = queue.poll();
                STEP 8
                If curCombination.equals(target), (meaning target is found)
                    STEP 9
                    Return turnsNum;
                STEP 10
                Iterate each wheel with idxWheel from 0 to 3
                    STEP 11
                    Iterate each direction with idxDirection for two values: -1 and 1 to indicate the forward and backward movement
                        STEP 12
                        Get next combination from curCombination using idxWheel and idxDirection and assign it to nextCombination
                            nextCombination = getNextCombination(curCombination, idxWheel, idxDirection)
                        STEP 13
                        If visitedArray[stoi(nextCombination)] != 1, (meaning next combination is not visited yet)
                            STEP 14
                            Convert it to int then enqueue it
                                queue.offer(nextCombination);
                            STEP 15
                            Convert nextCombination to int then set corresponding index in visitedArray to 1
                                visitedArray[stoi(nextCombination)] = 1;
                            
            STEP 16
            After iterating all combinations in current level, increase turnsNum by one for next level
                turnsNum++;
        STEP 17
        If nothing returned and finally queue is empty, means there is no path from start to target, directly return -1
            Return -1
        
        -FUNC void addDeadends(int[] visitedArray, String[] deadends)
        STEP 1
        Iterate each string in deadends with deadend,
            STEP 2
            Convert deadend to int then set corresponding index in visitedArray to 1
                visitedArray[stoi(deadend)] = 1;
        
        -FUNC int stoi(String s)
        STEP 1
        Return Integer.parseInt(s);
        
        -FUNC String getNextCombination(String curCombination, int idxWheel, int idxDirection)
        STEP 1
        Create a copy of curCombination as StringBuilder type
            StringBuilder nextCombination = new StringBuilder(curCombination)
        STEP 2
        Update character at idxWheel by idxDirection and assign it to updatedChar
            char updatedChar = (char) ((nextCombination.charAt(idxWheel) - '0' + idxDirection + 10) % 10 + '0');
        STEP 3
        Update character at idxWheel in nextCombination
            nextCombination.setCharAt(idxWheel, updatedChar);
        STEP 4
        Return nextCombination.toString()
        
    TIME:
        O(n^w + dw + n^w * 2w), first n^w is for initialize visitedArray, dw is for iteratng deadends and set each in visitedArray, n^w * 2w is for all combinations in queue
    SPACE:
        O(n^w + n^w * 2w), n^w for 1d array used as hash set, n^w * 2w for all combinations stored in queue at most in wrost case
*/

class OpenTheLockV2 {
    
    public static int openLockWithLeastSteps(String[] deadends, String start, String target) {
        // STEP 1
        int[] visitedArray = new int[10000];
        // STEP 2
        addDeadends(visitedArray, deadends);
        // STEP 3
        if (visitedArray[0] == 1) {
            // STEP 4
            return -1;
        }
        // STEP 5
        if (target.equals(start)) {
            // STEP 6
            return 0;
        }
        // STEP 7
        int turnsNum = BFS(start, target, visitedArray);
        // STEP 8
        return turnsNum;
    }
    
    public static int BFS(String start, String target, int[] visitedArray) {
        // STPE 1
        Queue<String> queue = new LinkedList<>();
        int turnsNum = 0;
        String curCombination = null;
        String nextCombination = null;
        // STEP 2
        queue.offer(start);
        // STEP 3
        visitedArray[stoi(start)] = 1;
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
                        if (visitedArray[stoi(nextCombination)] != 1) {
                            // STEP 14
                            queue.offer(nextCombination);
                            // STEP 15
                            visitedArray[stoi(nextCombination)] = 1;
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
    
    public static void addDeadends(int[] visitedArray, String[] deadends) {
        // STEP 1
        for (String deadend: deadends) {
            // STEP 2
            visitedArray[stoi(deadend)] = 1;
        }
    }
    
    public static int stoi(String s) {
        return Integer.parseInt(s);
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
        System.out.println("at least " + steps + " turns needed to move from " + start + " to " + target);
    }
}
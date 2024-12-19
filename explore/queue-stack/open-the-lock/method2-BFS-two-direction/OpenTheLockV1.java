import java.util.Arrays;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/*
    VARS:
        queueFromStart(Queue<String>): a queue used for BFS from start to target
        queueFromTarget(Queue<String>): a queue used for BFS from target to start
        queueSelected(Queue<String>): the queue with shorter size between queueFromStart and queueFromTarget to optimize the search performance
        hashFromStart(Map<String, Integer>): a map with combination as key and steps as value from start
        hashFromTarget(Map<String, Integer>): a map with combination as key and steps as value from target
        hashSelected(Map<String, Integer>): the hash map correspond to the shorter size queue
        hashNotSelected(Map<String, Integer>): the hash map not selected yet, for checking if curCombination exist in it
        turnsNum(int): the steps used from start or target to current combination
        deadendsSet(Set<String>): convert the deadends to Set<Integer> to make O(1) query
        curCombination(String): the current combination popped from queue front
        nextCombination(String): the next combination generated from curCombination
    DESCRIPTION:
        STEP 1
        Initialize deadendsSet to HashSet from deadends
            Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
        STEP 2
        If deadendsSet.contains(start), (meaning the start is a deadend, so there is no way to move from start to target)
            STEP 3
            Return -1
        STEP 4
        If target.equals(start), (meaning the target is the start, so there should be 0 steps to move from start to target)
            STEP 5
            Return 0;
        STEP 6
        Do bidirectional BFS and assign steps to turnsNum
            int turnsNum = BFS(start, target, deadendsSet);
        STEP 7
        Return turnsNum
        
        -FUNC int BFS(String start, String target, Set<String> deadendsSet)
        STEP 1
        Initialize queueFromStart to Queue<String>
        Initialize queueFromTarget to Queue<String>
        Initialize queueSelected to null
        Initialize hashFromStart to HashMap<String, Integer>, with key as combination, value as steps from start to current combination
        Initialize hashFromTarget to HashMap<String, Integer>, with key as combination, value as steps from target to current combination
        Initialize hashSelected to null
        Initialize hashNotSelected to null
        Initialize turnsNum to 0
        Initialize curCombination to null with String type
        Initialize nextCombination to null with String type        
        STEP 2
        Add start to queueFromStart
            queueFromStart.offer(start);
        STEP 3
        Add start as key and 0 steps as value to hashFromStart 
            hashFromStart.put(start, 0);
        STEP 4
        Add target to queueFromTarget
            queueFromTarget.offer(target);
        STEP 5
        Add target as key and 0 steps as value to hashFromTarget
            hashFromTarget.put(target, 0);
        STEP 6
        Loop while both queueFromStart and queueFromTarget are not empty
            If queueFromStart.size() < queueFromTarget.size()
                STEP 7
                Assign queueFromStart to queueSelected
                    queueSelected = queueFromStart;
                STEP 8
                Assign hashFromStart to hashSelected
                    hashSelected = hashFromStart
                STEP 9
                Assign hashFromTarget to hashNotSelected
                    hashNotSelected = hashFromStart;
            Else, (meaning queueFromTarget has shorter size)
                STEP 10
                Assign queueFromTarget to queueSelected
                    queueSelected = queueFromTarget;
                STEP 11
                Assign hashFromTarget to hashSelected
                    hashSelected = hashFromTarget
                STEP 12
                Assign hashFromStart to hashNotSelected
                    hashNotSelected = hashFromStart;
            STEP 13
            Get the size of current level of current queue
                int levelSize = queueSelected.size();
            STEP 14
            Iterate each node in current level with idx
                STEP 15
                Pop front node from queue and assign it to curCombination
                    curCombination = queueSelected.poll();
                STEP 16
                Iterate each wheel with idxWheel from 0 to 3
                    STEP 17
                    Iterate two directions(forward and backward) with idxDirection in two values: -1 and 1
                        STEP 18
                        Generate next combination from curCombination using idxWheel and idxDirection and assign it to nextCombination
                            nextCombination = getNextCombination(curCombination, idxWheel, idxDirection);
                        STEP 19
                        If hashNotSelected.containsKey(nextCombination), (meaning the next combination is exist in other side hash map)
                            STEP 20
                            Return the steps from start to curCombination, then plus one(meaning from curCombination to nextCombination), then plus the steps from target to nextCombination
                                Return hashSelected.get(curCombination) + 1 + hashNotSelected.get(nextCombination);
                        STEP 21
                        If isValid(hashSelected, deadendsSet, nextCombination), (meaning next combination is valid)
                            STEP 22
                            Add nextCombination to current selected queue
                                queueSelected.offer(nextCombination);
                            STEP 23
                            Add nextCombination as key and (hashSelected.get(curCombination) + 1) as value to hashSelected
                                hashSelected.put(nextCombination, hashSelected.get(curCombination) + 1);
        STEP 24
        Return -1

        -FUNC String getNextCombination(String curCombination, int idxWheel, int idxDirection)                        
        STEP 1
        Create a copy of curCombination as StringBuilder type and assign it to nextCombination
            StringBuilder nextCombination = new StringBuilder(curCombination);
        STEP 2
        Get updated character in nextCombination at idxWheel using idxDirection
            char updatedChar = (char) ((nextCombination.charAt(idxWheel) - '0' + idxDirection + 10) % 10 + '0');
        STEP 3
        Update nextCombination in idxWheel with updatedChar
            nextCombination.setCharAt(idxWheel, updatedChar)
        STEP 4
        Return nextCombination.toString();
        
        -FUNC boolean isValid(Mao<String, Integer> hashSelected, Set<String> deadendsSet, String nextCombination)
        STEP 1
        Return true if current selected hashmap doesn't contain nextCombination and nextCombination is not a deadend
            return !hashSelected.containsKey(nextCombination) && !deadendsSet.contains(nextCombination)            
    TIME:
        O(dw + n^w * 2w), dw for copy deadends as HashSet, n^w * 2w for the combinations in two queues before two directional BFS meeting in the middle in normal case, but will doubled when there is no path which is the wrost case
    SPACE:
        O(dw + n^w * 2w * 2), dw for deadends hashset, n^w * 2w * w for all combinations stored in two queues in wrost cases where there is no path from start to target
*/

class OpenTheLockV1 {
    
    public static int openLockWithLeastSteps(String[] deadends, String start, String target) {
        // STEP 1
        Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
        // STEP 2
        if (deadendsSet.contains(start)) {
            // STEP 3
            return -1;
        }
        // STEP 4
        if (target.equals(start)) {
            // STEP 5
            return 0;
        }
        // STEP 6
        int turnsNum = BFS(start, target, deadendsSet);
        // STEP 7
        return turnsNum;
    }
    
    public static int BFS(String start, String target, Set<String> deadendsSet) {
        // STEP 1
        Queue<String> queueFromStart = new LinkedList<>();
        Queue<String> queueFromTarget = new LinkedList<>();
        Queue<String> queueSelected = null;
        Map<String, Integer> hashFromStart = new HashMap<>();
        Map<String, Integer> hashFromTarget = new HashMap<>();
        Map<String, Integer> hashSelected = null;
        Map<String, Integer> hashNotSelected = null;
        String curCombination = null;
        String nextCombination = null;
        // STEP 2
        queueFromStart.offer(start);
        // STEP 3
        hashFromStart.put(start, 0);
        // STEP 4
        queueFromTarget.offer(target);
        // STEP 5
        hashFromTarget.put(target, 0);
        // STEP 6
        while (!queueFromStart.isEmpty() && !queueFromTarget.isEmpty()) {
            if (queueFromStart.size() < queueFromTarget.size()) {
                // STEP 7
                queueSelected = queueFromStart;
                // STEP 8
                hashSelected = hashFromStart;
                // STEP 9
                hashNotSelected = hashFromTarget;
            } else {
                // STEP 10
                queueSelected = queueFromTarget;
                // STEP 11
                hashSelected = hashFromTarget;
                // STEP 12
                hashNotSelected = hashFromStart;
            }
            // STEP 13
            int levelSize = queueSelected.size();
            // STEP 14
            for (int idx = 0; idx < levelSize; idx++) {
                // STEP 15
                curCombination = queueSelected.poll();
                // STEP 16
                for (int idxWheel = 0; idxWheel < 4; idxWheel++) {
                    // STEP 17
                    for (int idxDirection = -1; idxDirection <= 1; idxDirection += 2) {
                        // STEP 18
                        nextCombination = getNextCombination(curCombination, idxWheel, idxDirection);
                        // STEP 19
                        if (hashNotSelected.containsKey(nextCombination)) {
                            // STEP 20
                            return hashSelected.get(curCombination) + 1 + hashNotSelected.get(nextCombination);
                        }
                        // STEP 21
                        if (isValid(hashNotSelected, deadendsSet, nextCombination)) {
                            // STEP 22
                            queueSelected.offer(nextCombination);
                            // STEP 23
                            hashSelected.put(nextCombination, hashSelected.get(curCombination) + 1);
                        }
                    }
                }
            }
        }
        // STEP 24
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
    
    public static boolean isValid(Map<String, Integer> hashSelected, Set<String> deadendsSet, String nextCombination) {
        // STEP 1
        return !hashSelected.containsKey(nextCombination) && !deadendsSet.contains(nextCombination);
    }
        
    public static void main(String[] args) {
        String start = "0000";
        
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        
        // String[] deadends = {"8888"};
        // String target = "0009";
        
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
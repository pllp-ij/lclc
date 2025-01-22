import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        queue(Queue<Integer>): a queue used when do BFS
        visited(Set<Integer>): a cache grid to indicate whether current index is already visited
        index(int): current iterated index of roomsList
    DESCRIPTION:
        STEP 1
        Initialize visited to new HashSet<>();
        Initialize index to 0
        STEP 2
        Do BFS from index of 0
            BFS(roomsList, index, visited);
        STEP 3
        Return true is the size of visited is equal to the size of roomsList after completing the whole BFS
            return visited.size() == roomsList.size();
            
        -FUNC void BFS(List<List<Integer>> roomsList, int index, Set<Integer> visited)
        STEP 1
        Initialize queue to new LinkedList<Integer>
        Initialize curIndex to index
        STEP 2
        Enqueue curIndex into queue
            queue.offer(curIndex);
        STEP 3
        Loop while queue is not empty,
            STEP 4
            Dequeue front node from queue and assign it to curIndex
                curIndex = queue.poll();
            STEP 5
            Set current index as visited by adding it into visited
                visited.add(curIndex);
            STEP 6
            Iterate each keys of roomsList.get(curIndex) with idxKeys from 0 to (roomsList.get(curIndex).size())
                STEP 7
                Calculate next node index
                    int nextIndex = roomsList.get(curIndex).get(idxKeys);
                STEP 8
                If isValid(nextIndex, visited), (meaning next index is valid)
                    STEP 9
                    Enqueue nextIndex into queue for next operation
                        queue.offer(nextIndex);
        
        -FUNC boolean isValid(int nextIndex, Set<Integer> visited)
        STEP 1
        If !visited.contains(nextIndex), (meaning nextIndex is not visited)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
    TIME:
        O(m * n), m is the number of nodes in roomsList, n is the number of corresponding edges
    SPACE:
        O(m), the size of queue used when do BFS in worst cases is the number of nodes
*/

class KeysAndRoomsV2 {
    
    public static boolean canVisitAllRooms(List<List<Integer>> roomsList) {
        // STEP 1
        Set<Integer> visited = new HashSet<>();
        int index = 0;
        // STEP 2
        BFS(roomsList, index, visited);
        // STEP 3
        return visited.size() == roomsList.size();
    }
    
    public static void BFS(List<List<Integer>> roomsList, int index, Set<Integer> visited) {
        // STEP 1
        Queue<Integer> queue = new LinkedList<>();
        int curIndex = index;
        // STEP 2
        queue.offer(curIndex);
        // STEP 3
        while (!queue.isEmpty()) {
            // STEP 4
            curIndex = queue.poll();
            // STEP 5
            visited.add(curIndex);
            // STEP 6
            for (int idxKeys = 0; idxKeys < roomsList.get(curIndex).size(); idxKeys++) {
                // STEP 7
                int nextIndex = roomsList.get(curIndex).get(idxKeys);
                // STEP 8
                if (isValid(nextIndex, visited)) {
                    // STEP 9
                    queue.offer(nextIndex);
                }
            }
        }
    }
    
    public static boolean isValid(int index, Set<Integer> visited) {
        // STEP 1
        if (!visited.contains(index)) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        String roomsStr = "[[1] [2] [3] []]";
        // String roomsStr = "[[1,3] [3,0,1] [2] [0]]";
        
        Rooms rooms = new Rooms(roomsStr);
        List<List<Integer>> roomsList = rooms.roomsList;
        
        System.out.println("initial roomsStr: " + roomsStr);
        System.out.println("created roomsList: " + rooms.printRoomsList());
        boolean result = canVisitAllRooms(roomsList);
        System.out.println("all rooms can be visited: " + result);
    }
}
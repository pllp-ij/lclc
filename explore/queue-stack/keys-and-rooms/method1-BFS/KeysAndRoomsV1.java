import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        queue(Queue<Integer>): a queue used when do BFS
        visited(Set<Integer>): a cache hash set to indicate whether current room is already visited
        index(int): the index when iterate each room of roomsList
    DESCRIPTION:
        STEP 1
        Initialize visited to HashSet<Integer>;
        Initialize index to 0
        STEP 2
        Do BFS from index 0
            BFS(roomsList, index, visited);
        STEP 3
        Return true if size of visited is equal to roomsList size
            return visited.size() == roomsList.size();
            
        -FUNC void BFS(List<List<Integer>> roomsList, int index, Set<Integer>)
        STEP 1
        Initialize queue to Queue<Integer>;
        Initialize curIndex to -1
        STEP 2
        Enqueue current index into queue
            queue.offer(index);
        STEP 3
        Loop while queue is not empty,
            STEP 4
            Dequeue front node from queue and assign it to curNode
                curIndex = queue.poll();
            STEP 5
            If isNotValid(roomsList, curIndex, visited), (meaning current index is not valid)
                STEP 6
                Skip current iteration
                    continue;
            STEP 7
            Set current index as visited
                visited.add(curIndex);
            STEP 8
            Iterate each keys of current index with idxKeys from 0 to (roomsList.get(curIndex).size() - 1)
                STEP 9
                Calculate nextIndex from curIndex
                    int nextIndex = roomsList.get(curIndex).get(idxKeys);
                STEP 10
                Enqueue new next node into queue
                    queue.offer(nextIndex);

        -FUNC boolean isNotValid(List<List<Integer>> roomsList, int curIndex, Set<Integer> visited)
        STEP 1
        If visited.contains(curIndex), (if current index is already visited, note that there is no need to return true when the size of list in curIndex is zero, because though it is an empty list, but it is still regarded as a object to be inspected)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
    TIME:
        O(m * n), m is the number of nodes in roomsList, n is the number of corresponding edges
    SPACE:
        O(m), the size of queue used when do BFS in worst cases is the number of nodes
*/

class KeysAndRoomsV1 {
    
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
            if (isNotValid(curIndex, visited)) {
                // STEP 6
                continue;
            }
            // STEP 7
            visited.add(curIndex);
            // STEP 8
            for (int idxKeys = 0; idxKeys < roomsList.get(curIndex).size(); idxKeys++) {
                // STEP 9
                int nextIndex = roomsList.get(curIndex).get(idxKeys);
                // STEP 10
                queue.offer(roomsList.get(curIndex).get(idxKeys));
            }
        }
    }
    
    public static boolean isNotValid(int curIndex, Set<Integer> visited) {
        // STEP 1
        if (visited.contains(curIndex)) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        // String roomsStr = "[[1] [2] [3] []]";
        String roomsStr = "[[1,3] [3,0,1] [2] [0]]";
        
        Rooms rooms = new Rooms(roomsStr);
        List<List<Integer>> roomsList = rooms.roomsList;
        
        System.out.println("initial roomsStr: " + roomsStr);
        System.out.println("created roomsList: " + rooms.printRoomsList());
        boolean result = canVisitAllRooms(roomsList);
        System.out.println("all rooms can be visited: " + result);
    }
}
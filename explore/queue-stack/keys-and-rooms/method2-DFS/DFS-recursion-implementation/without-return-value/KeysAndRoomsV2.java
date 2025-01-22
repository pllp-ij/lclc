import java.util.List;
import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        visited(Set<Integer>): a cache hash set to indicate whether a node is already visited
        index(int): current iterated index of roomsList
    DESCRIPTION:
        STEP 1
        Initialize visited to new HashSet<>();
        Initialize index to 0
        STEP 2
        Do DFS with recursion
            DFS(roomsList, index, visited);
        STEP 3
        Return true if the size of visited is equal to the size of roomsList
            return visited.size() == roomsList.size();
            
        -FUNC void DFS(List<List<Integer>> roomsList, int curIndex, Set<Integer> visited)
        STEP 1
        Set current curIndex as visited
            visited.add(curIndex);
        STEP 2
        Iterate each next index with idxKeys from 0 to (roomsList.get(curIndex).size() - 1)
            STEP 3
            Calculate next index
                int nextIndex = roomsList.get(curIndex).get(idxKeys);
            STEP 4
            If isValid(nextIndex, visited), (meaning next index is not valid)            
                STEP 5
                Do DFS recursively for next iteration
                    DFS(roomsList, nextIndex, visited);
        
        -FUNC boolean isValid(int index, Set<Integer> visited)
        STEP 1
        If !visited.contains(index), (meaning index is not in visited)
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
        DFS(roomsList, index, visited);
        // STEP 3
        return visited.size() == roomsList.size();
    }
    
    public static void DFS(List<List<Integer>> roomsList, int curIndex, Set<Integer> visited) {
        // STEP 1
        visited.add(curIndex);
        // STEP 2
        for (int idxKeys = 0; idxKeys < roomsList.get(curIndex).size(); idxKeys++) {
            // STEP 3
            int nextIndex = roomsList.get(curIndex).get(idxKeys);
            // STEP 4
            if (isValid(nextIndex, visited)) {
                // STEP 4
                DFS(roomsList, nextIndex, visited);
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
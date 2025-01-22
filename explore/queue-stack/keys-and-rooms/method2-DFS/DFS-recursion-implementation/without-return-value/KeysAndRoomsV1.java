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
        If isNotValid(curIndex, visited), (meaning the node in current level is not valid)
            STEP 2
            Directly return
                return;
        STEP 3
        Set current curIndex as visited
            visited.add(curIndex);
        STEP 4
        Iterate each next index with idxKeys from 0 to (roomsList.get(curIndex).size() - 1)
            STEP 5
            Calculate next index
                int nextIndex = roomsList.get(curIndex).get(idxKeys);
            STEP 6
            Do DFS recursively for next iteration
                DFS(roomsList, nextIndex, visited);
        
        -FUNC isNotValid(int index, Set<Integer> visited)
        STEP 1
        If visited.contains(index), (meaning current index is not valid)
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
        DFS(roomsList, index, visited);
        // STEP 3
        return visited.size() == roomsList.size();
    }
    
    public static void DFS(List<List<Integer>> roomsList, int curIndex, Set<Integer> visited) {
        // STEP 1
        if (isNotValid(curIndex, visited)) {
            // STEP 2
            return;
        }
        // STEP 3
        visited.add(curIndex);
        // STEP 4
        for (int idxKeys = 0; idxKeys < roomsList.get(curIndex).size(); idxKeys++) {
            // STEP 5
            int nextIndex = roomsList.get(curIndex).get(idxKeys);
            // STEP 6
            DFS(roomsList, nextIndex, visited);
        }
    }
    
    public static boolean isNotValid(int index, Set<Integer> visited) {
        // STEP 1
        if (visited.contains(index)) {
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
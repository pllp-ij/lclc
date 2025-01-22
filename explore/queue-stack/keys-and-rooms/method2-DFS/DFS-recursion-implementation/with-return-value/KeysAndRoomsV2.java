import java.util.List;
import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        visited(Set<Integer>): a cache to indicate whether a node is already visited
        index(int): the iterated index from the start index of 0
        somethingResult(int): the result of some tasks after traverse all nodes by DFS
    DESCRIPTION:
        STEP 1
        Initialize visited to new HashSet<>();
        Initialize index to 0
        STEP 2
        Do DFS with recursion and assign the result back to meaningless var, there can be something done with somethingResult
            int somethingResult = DFS(roomsList, index, visited);
        STEP 3
        Return true if size of visited is equal to the size of roomsList
            return visited.size() == roomsList.size();
            
        -FUNC int DFS(List<List<Integer>> roomsList, int curIndex, Set<Integer> visited)
        STEP 1
        Set curIndex as visited
            visited.add(curIndex);
        STEP 2
        Initialize somethingResult to 0 to represent the final state of current node after recursively process all its adjacent nodes
            int somethingResult = 0;
        STEP 3
        Iterate each next index with idxKeys from 0 to (roomsList.get(curIndex).size() - 1)
            STEP 4
            Calculate next index from current index
                int nextIndex = roomsList.get(curIndex).get(idxKeys);
            STEP 5
            If isValid(nextIndex, visited), (meaning next index is valid)
                STEP 6
                Do DFS recursively for next index and assign the result to nextResult
                    int nextResult = DFS(roomsList, nextIndex, visited);
                STEP 7
                Update somethingResult by constructing the relationships between somethingResult and nextResult, here use the min result
                    somethingResult = Math.min(somethingResult, nextResult);
        STEP 8
        Return a mapping function of somethingResult for current node as final result, here return it plus one, which target at the shortest distance from current node to deadend
            return somethingResult + 1;
        
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
        int somethingResult = DFS(roomsList, index, visited);
        // STEP 3
        return visited.size() == roomsList.size();
    }
    
    public static int DFS(List<List<Integer>> roomsList, int curIndex, Set<Integer> visited) {
        // STEP 1
        visited.add(curIndex);
        // STEP 2
        int somethingResult = 0;
        // STEP 3
        for (int idxKeys = 0; idxKeys < roomsList.get(curIndex).size(); idxKeys++) {
            // STEP 4
            int nextIndex = roomsList.get(curIndex).get(idxKeys);
            // STEP 5
            if (isValid(nextIndex, visited)) {
                // STEP 6
                int nextResult = DFS(roomsList, nextIndex, visited);
                // STEP 7
                somethingResult = Math.min(somethingResult, nextResult);
            }
        }
        // STEP 8
        return somethingResult + 1;
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
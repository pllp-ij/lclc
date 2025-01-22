import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

/*
    VARS:
        stack(Stack<Integer>): a stack used when do DFS
        visited(Set<Integer>): a hash set used to indicate whether a node is already visited
        index(int): the index of start node
    DESCRIPTION:
        STEP 1
        Initialize visited to HashSet<>();
        Initialize index to 0
        STEP 2
        Do DFS from index
            DFS(roomsList, index, visited);
        STEP 3
        Return true if size of visited is equal to the size of roomsList
            return visited.size() == roomsList.size();
            
        -FUNC void DFS(List<List<Integer>> roomsList, int index, Set<Integer> visited)
        STEP 1
        Initialize stack to new Stack<>();
        Initialize curIndex to index
        STEP 2
        Push curIndex into stack
            stack.push(curIndex);
        STEP 3
        Loop while stack is not empty,
            STEP 4
            Pop front node from stack and assign it to curIndex
                curIndex = stack.pop();
            STEP 5
            Set curIndex as visited by adding it into visited
                visited.add(curIndex);
            STEP 6
            Iterate each key with idxKeys from 0 to (roomsList.get(curIndex).size() - 1)
                STEP 7
                Get next index
                    int nextIndex = roomsList.get(curIndex).get(idxKeys);
                STEP 8
                If isValid(nextIndex, visited), (meaning next index is valid)
                    STEP 9
                    Push new next index into stack
                        stack.push(nextIndex);
                    
        -FUNC boolean isValid(int index, Set<Integer> visited)
        STEP 1
        If !visited.contains(index), (meaning next index is not visited)
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
        // STEP 3s
        return visited.size() == roomsList.size();
    }
    
    public static void DFS(List<List<Integer>> roomsList, int index, Set<Integer> visited) {
        // STEP 1
        Stack<Integer> stack = new Stack<>();
        int curIndex = index;
        // STEP 2
        stack.push(curIndex);
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curIndex = stack.pop();
            // STEP 5
            visited.add(curIndex);
            // STEP 6
            for (int idxKeys = 0; idxKeys < roomsList.get(curIndex).size(); idxKeys++) {
                // STEP 7
                int nextIndex = roomsList.get(curIndex).get(idxKeys);
                // STEP 8
                if (isValid(nextIndex, visited)) {
                    // STEP 9
                    stack.push(roomsList.get(curIndex).get(idxKeys));
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
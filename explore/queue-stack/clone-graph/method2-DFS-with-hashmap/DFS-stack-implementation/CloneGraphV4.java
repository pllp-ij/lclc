import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

/*
    VARS:
        map(Map<GraphNode, GraphNode>): a map used to store relationship from initial node to cloned node
        visited(Set<GraphNode>): the hash set to store all visited nodes when do DFS
        stack(Stack<GraphNode>): a stack used when do DFS
    DESCRIPTION:
        STEP 1
        If graph == null || graph.node == null, (meaning graph is empty or its first node is empty)
            STEP 2
            Return graph
        STEP 3
        Initialize map to HashMap<>();
        Initialize graphCloned for final return
            Graph graphCloned = new Graph();
        Initialize visited to HashSet<>();
        STEP 4
        Do DFS with graph.node using map and visited
            DFS(graph.node, map, visited);
        STEP 5
        After above DFS finished, assign the first node initial graph to the first node of graphCloned
            graphCloned.node = map.get(graph.node);
        STEP 6
        Return graphCloned
        
        -FUNC void DFS(GraphNode node, Map<GraphNode, GraphNode> map, Set<GraphNode> visited)
        STEP 1
        Initialize stack to Stack<GraphNode>
        Initialize curNode to node
        Initialize curNodeCloned to null
        Initialize curNodeNeighborCloned to null
        
        Now firstly clone first node from STEP to STEP
        
        If map.containsKey(curNode), (meaning first node is already visited, which is actually never happen)
            STPE 2
            Get cloned first node from map and assign it to curNodeCloned
                curNodeCloned = map.get(curNode);
        Else, (meaning first node is not visited)
            STEP 3
            Create a new node from curNode and assign it to curNodeCloned
                curNodeCloned = new GraphNode(curNode.val);
            STEP 4
            Add pair (curNode, curNodeCloned) into map
                map.put(curNode, curNodeCloned);
        STEP 5
        If isValid(curNode, visited), (meaning first node is valid, actually it will be always true)
            STEP 6
            Set curNode as visited
                visited.add(curNode);
            STEP 7
            Push curNode into stack
                stack.push(curNode);
        STEP 8
        Loop while stack is not empty, (like in a non-dummy head linked list, iterate each node from head to last non-NULL node)
            STEP 9
            Pop front node from stack and assign it to curNode
                curNode = stack.pop();
            STEP 10
            Get cloned node of curNode from map, (because each iteration in default is already cloning curNode and it exist in map)
                curNodeCloned = map.get(curNode);
                
            Now clone all neighboring nodes of curNode
                
            STEP 11
            Iterate each neighboring nodes of curNode with curNodeNeighbor
                If map.containsKey(curNodeNeighbor), (meaning current neighboring node is alerady cloned)
                    STEP 12
                    Get cloned node value and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = map.get(curNodeNeighbor)
                Else, (meaning current neighboring node is not cloned)
                    STEP 13
                    Create a new node from curNodeNeighbor and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    STEP 14
                    Add pair (curNodeNeighbor, curNodeNeighborCloned) into map
                        map.put(curNodeNeighbor, curNodeNeighborCloned);
                STEP 15
                Add curNodeNeighborCloned to neighbors property of curNodeCloned
                    curNodeCloned.neighbors.add(curNodeNeighborCloned);
                    
            Now explore all next steps
            
            STEP 16
            Iterate each neighboring nodes of curNode with curNodeNeighbor,
                STEP 17
                If isValid(curNodeNeighbor, visited), (meaning curNodeNeighbor is valid)
                    STEP 18
                    Push curNodeNeighbor into stack for next iteration
                        stack.push(curNodeNeighbor);
                    STEP 19
                    Set curNodeNeighbor as visited
                        visited.add(curNodeNeighbor);

        -FUNC boolean isValid(GraphNode node, Set<GraphNode> visited)
        STEP 1
        If !visited.contains(node), (meaning current node is not visited)
            STEP 2
            Return true;
        STEP 3
        Return false;
    TIME:
        O(m * n), m is vertices number in graph, and n is the edges number in graph
    SPACE:
        O(m * n + m), (m * n) is for the max number of nodes pushed into queue when do BFS in worst case, m is the vertices number and n is the edges number
*/

class CloneGraphV4 {
    
    public static Graph cloneGraph(Graph graph) {
        // STEP 1
        if (graph == null || graph.node == null) {
            // STEP 2
            return graph;
        }
        // STEP 3
        Map<GraphNode, GraphNode> map = new HashMap<>();
        Graph graphCloned = new Graph();
        Set<GraphNode> visited = new HashSet<>();
        // STEP 4
        DFS(graph.node, map, visited);
        // STEP 5
        graphCloned.node = map.get(graph.node);
        // sTEp 6
        return graphCloned;
    }
    
    public static void DFS(GraphNode node, Map<GraphNode, GraphNode> map, Set<GraphNode> visited) {
        // STEP 1
        Stack<GraphNode> stack = new Stack<>();
        GraphNode curNode = node;
        GraphNode curNodeCloned = null;
        GraphNode curNodeNeighborCloned = null;
        /* clone first node start */
        if (map.containsKey(curNode)) {
            // STEP 2
            curNodeCloned = map.get(curNode);
        } else {
            // STEP 3
            curNodeCloned = new GraphNode(curNode.val);
            // STEP 4
            map.put(curNode, curNodeCloned);
        }
        // STEP 5
        if (isValid(curNode, visited)) {
            // STEP 6
            stack.push(curNode);
            // STEP 7
            visited.add(curNode);
        }
        // STEP 8
        while (!stack.isEmpty()) {
            // STEP 9
            curNode = stack.pop();
            // STEP 10
            curNodeCloned = map.get(curNode);
            // STEP 11
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                if (map.containsKey(curNodeNeighbor)) {
                    // STEP 12
                    curNodeNeighborCloned = map.get(curNodeNeighbor);
                } else {
                    // STEP 13
                    curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    // STEP 14
                    map.put(curNodeNeighbor, curNodeNeighborCloned);
                }
                // STEP 15
                curNodeCloned.neighbors.add(curNodeNeighborCloned);
            }
            // STEP 16
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                // STEP 17
                if (isValid(curNodeNeighbor, visited)) {
                    // STEP 18
                    stack.push(curNodeNeighbor);
                    // STEP 19
                    visited.add(curNodeNeighbor);
                }
            }
        }
    }
    
    public static boolean isValid(GraphNode node, Set<GraphNode> visited) {
        // STEP 1
        if (!visited.contains(node)) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        int[][] relationships = {
            {2, 4},
            {1, 3},
            {2, 4},
            {1, 3}
        };
        
        // int[][] relationships = {
            // {}
        // };
        
        // int[][] relationships = null;
        
        Graph graph = new Graph();
        graph.createGraph(relationships);
        System.out.println("initial graph: " + graph.printGraphDFSRecursiveV2());
        Graph clonedGraph = cloneGraph(graph);
        System.out.println("cloned graph: " + clonedGraph.printGraphDFSRecursiveV2());
    }
}
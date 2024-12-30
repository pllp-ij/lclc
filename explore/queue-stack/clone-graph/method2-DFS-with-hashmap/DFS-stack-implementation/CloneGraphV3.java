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
        STEP 2
        Add curNode to stack
            stack.push(curNode);
        STEP 3
        Loop while stack is not empty,
            STEP 4
            Pop front node from stack and assign it to curNode
                curNode = stack.pop();
            
            Now clone curNode from STEP 5 to STEP 8
            
            If map.containsKey(curNode), (meaning current node is already cloned)
                STEP 5
                Get cloned node and assign it to curNodeCloned
                    curNodeCloned = map.get(curNode);
            Else, (meaning current node is not cloned)
                STEP 6
                Create a new node from curNode and assign it to curNodeCloned
                    curNodeCloned = new GraphNode(curNode.val);
                STEP 7
                Add pair (curNode, curNodeCloned) into map
                    map.put(curNode, curNodeCloned);
                
            Now clone all neighboring nodes of curNode from STEP to STEP
            
            STEP 8
            Iterate each neighboring node of curNode with curNodeNeighbor
                If map.containsKey(curNodeNeighbor), (meaning current neighboring node is already cloned)
                    STEP 9
                    Get the cloned neighboring node of curNodeNeighbor and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = map.get(curNodeNeighbor);
                Else, (meaning current neighboring node is already cloned)
                    STEP 10
                    Create a new node from curNodeNeighbor and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    STEP 11
                    Add pair (curNodeNeighbor, curNodeNeighborCloned) to map
                        map.put(curNodeNeighbor, curNodeNeighborCloned);
                STEP 12
                Add curNodeNeighborCloned to neighbors property of curNodeCloned
                    curNodeCloned.neighbors.add(curNodeNeighborCloned);
            STEP 13
            Set curNode as visited
                visited.add(curNode);
            
            Now explore all next steps
            
            STEP 14
            Iterate each neighboring node of curNode with curNodeNeighbor,
                STEP 15
                If isValid(curNodeNeighbor, visited), (meaning current neighboring node is valid)
                    STEP 16
                    Add curNodeNeighbor to stack
                        stack.push(curNodeNeighbor);
                
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

class CloneGraphV3 {
    
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
        // STEP 2
        stack.push(curNode);
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curNode = stack.pop();
            /* clone curNode start */
            if (map.containsKey(curNode)) {
                // STEP 5
                curNodeCloned = map.get(curNode);
            } else {
                // STEP 6
                curNodeCloned = new GraphNode(curNode.val);
                // STEP 7
                map.put(curNode, curNodeCloned);
            }
            /* clone curNode end */
            
            /* clone all neighboring nodes start */
            // STEP 8
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                if (map.containsKey(curNodeNeighbor)) {
                    // STEP 9
                    curNodeNeighborCloned = map.get(curNodeNeighbor);
                } else {
                    // STEP 10
                    curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    // STEP 11
                    map.put(curNodeNeighbor, curNodeNeighborCloned);
                }
                // STEP 12
                curNodeCloned.neighbors.add(curNodeNeighborCloned);
            }
            /* clone all neighboring nodes end */
            
            // STEP 13
            visited.add(curNode);
            
            /* enplore all next steps */
            // STEP 14
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                // STEP 15
                if (isValid(curNodeNeighbor, visited)) {
                    // STEP 16
                    stack.push(curNodeNeighbor);
                    // STEP 17
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
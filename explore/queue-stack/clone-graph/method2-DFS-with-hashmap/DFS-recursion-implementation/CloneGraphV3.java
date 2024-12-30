import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        map(Map<GraphNode, GraphNode>): a map used to store relationship from initial node to cloned node
        visited(Set<GraphNode>): the hash set to store all visited nodes when do DFS
    DESCRIPTION:
        STEP 1
        If graph == null or graph.node == null, (meaning graph is empty or its first node is emtpty)
            STEP 2
            Return graph
        STEP 3
        Initialize map to HashMap<>();
        Initialize graphCloned
            Graph graphCloned = new Graph();
        Initialize visited to HashSet<>();
        STEP 4
        Do DFS from graph.node
            DFS(graph.node, map, visited);
        STEP 5
        After finishing above DFS, assign the first node of graphCloned to the first cloned node of initial graph 
            graphCloned.node = mao.get(graph.node);
        STEP 6
        Return graphCloned
        
        -FUNC void DFS(GraphNode node, Map<GraphNode, GraphNode> map, Set<GraphNode> visited)
        STEP 1
        Initialize curNode to node
        Initialize curNodeCloned to null
        Initialize curNodeNeighbor to null
        
        Now start cloning curNode from STEP 2 to STEP 5
        
        If map.containsKey(curNode), (meaning current node is already cloned)
            STEP 2
            Assign curNodeCloned to map.get(curNode)
                curNodeCloned = map.get(curNode);
        Else, (meaning current node is not cloned yet, so create a new one and assign it to curNodeCloned)
            STEP 3
            Create a new node from curNode and assign it to curNodeCloned
                curNodeCloned = new GraphNode(curNode.val);
            STEP 4
            Put the pair (curNode, curNodeCloned) into map
                map.put(curNode, curNodeCloned);
            
        Now clone all neighbor node of curNode from STEP 6 to STEP 10
            
        STEP 5
        Iterate each neighbor node of current node with curNodeNeighbor
            If map.containsKey(curNodeNeighbor), (meaning the neighbor node of curNode has been cloned)
                STEP 6
                Get cloned node of curNode's neighbor node and assign it to curNodeNeighborCloned
                    curNodeNeighborCloned = map.get(curNodeNeighbor);
            Else, (meaning current neighbor node is not yet cloned)
                STEP 7
                Create a new node from curNodeNeighbor and assign it to curNodeNeighborCloned
                    curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                STEP 8
                Add pair (curNodeNeighbor, curNodeNeighborCloned) into map
                    map.put(curNodeNeighbor, curNodeNeighborCloned)
            STEP 9
            Add curNodeNeighborCloned into curNodeCloned's neighbors property
                curNodeCloned.neighbors.add(curNodeNeighborCloned);
        STEP 10
        Set curNode as visited
            visited.add(curNode);
            
        Now do DFS recursion for each neighboring node
        
        STEP 11
        Iterate each neighbor node of current node with curNodeNeighbor
            STEP 12
            If isValid(curNodeNeighbor, visited), (meaning current neighboring node is valid)
                STEP 13
                Do DFS recursively
                    DFS(curNodeNeighbor, map, visited);
                STEP 14
                Set curNodeNeighbor as visited in curNode's iteration, (though it will result in repeated operation, but not a wrong logic)
                    visited.add(curNodeNeighbor);

        -FUNC boolean isNotValid(GraphNode node, Set<GraphNode> visited)
        STEP 1
        If visited.contains(node), (meaning current node is not valid)
            STEP 2
            Return true;
        STEP 3
        Return false;
    TIME:
        O(m * n), m is vertices number in graph, and n is the edges number in graph
    SPACE:
        O(M + M + M * N), first M is for visited, second M is for map, (M * N) is for max number of calling stack
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
        // STEP 6
        return graphCloned;
    }
    
    public static void DFS(GraphNode node, Map<GraphNode, GraphNode> map, Set<GraphNode> visited) {
        // STEP 1
        GraphNode curNode = node;
        GraphNode curNodeCloned = null;
        GraphNode curNodeNeighborCloned = null;
        
        /* clone curNode start */
        if (map.containsKey(curNode)) {
            // STEP 2
            curNodeCloned = map.get(curNode);
        } else {
            // STEP 3
            curNodeCloned = new GraphNode(curNode.val);
            // STEP 4
            map.put(curNode, curNodeCloned);
        }
        /* clone curNode end */
        
        /* clone neighbors of curNode start */
        // STEP 5
        for (GraphNode curNodeNeighbor: curNode.neighbors) {
            if (map.containsKey(curNodeNeighbor)) {
                // STEP 6
                curNodeNeighborCloned = map.get(curNodeNeighbor);
            } else {
                // STEP 7
                curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                // STEP 8
                map.put(curNodeNeighbor, curNodeNeighborCloned);
            }
            // STEP 9
            curNodeCloned.neighbors.add(curNodeNeighborCloned);
        }
        /* clone neighbors of curNode end */
        
        // STEP 10
        visited.add(curNode);
        
        /* recursively DFS start */
        // STEP 11
        for (GraphNode curNodeNeighbor: curNode.neighbors) {
            // STEP 12
            if (isValid(curNodeNeighbor, visited)) {
                // STEP 13
                DFS(curNodeNeighbor, map, visited);
                // STEP 14
                visited.add(curNodeNeighbor);
            }
        }
        /* recursively DFS end */
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
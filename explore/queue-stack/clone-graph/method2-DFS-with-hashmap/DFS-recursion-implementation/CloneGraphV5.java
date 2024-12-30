import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        map(Map<GraphNode, GraphNode>): a map used to store relationship from initial node to cloned node
    DESCRIPTION:
        STEP 1
        If graph == null or graph.node == null, (meaning current graph is empty or the first node of graph is empty)
            STEP 2
            Directly return graph
        STEP 3
        Initialize map to HashMap<>();
        Initialize graphCloned with Graph type for final return
        STEP 4
        Do DFS with return value of GraphNode type and assign it to graphCloned.node
            graphCloned.node = DFS(graph.node, map)
        STEP 5
        Return graphCloned
        
        -FUNC GraphNode DFS(GraphNode curNode, Map<GraphNode, GraphNode> map)
        STEP 1
        If map.containsKey(curNode), (meaning current node is already cloned)
            STEP 2
            Return the cloned node of current node
                return map.get(curNode);
                
        Now clone current node
                
        STEP 3
        Create a new node from curNode.val and assign it to curNodeCloned
            GraphNode curNodeCloned = new GraphNode(curNode.val);
        STEP 4
        Add pair (curNode, curNodeCloned) into map
            map.put(curNode, curNodeCloned);
        STEP 5
        Iterate each neighboring node of curNode with curNodeNeighbor
            STEP 6
            Do DFS recursively and assign it to curNodeNeighborCloned
                GraphNode curNodeNeighborCloned = DFS(curNodeNeighbor, map);
            STEP 7
            Add curNodeNeighborCloned to curNodeCloned's neighbors property
                curNodeCloned.neighbors.add(curNodeNeighborCloned);
    TIME:
        O(m * n), m is vertices number in graph, and n is the edges number in graph
    SPACE:
        O(M + M * N), first M is for map, (M * N) is for max number of calling stack
*/

class CloneGraphV5 {
    
    public static Graph cloneGraph(Graph graph) {
        // STEP 1
        if (graph == null || graph.node == null) {
            // STEP 2
            return graph;
        }
        // STEP 3
        Map<GraphNode, GraphNode> map = new HashMap<>();
        Graph graphCloned = new Graph();
        // STEP 4
        graphCloned.node = DFS(graph.node, map);
        // STEP 5
        return graphCloned;
    }
    
    public static GraphNode DFS(GraphNode curNode, Map<GraphNode, GraphNode> map) {
        // STPE 1
        if (map.containsKey(curNode)) {
            // STEP 2
            return map.get(curNode);
        }
        // STEP 3
        GraphNode curNodeCloned = new GraphNode(curNode.val);
        // STEP 4
        map.put(curNode, curNodeCloned);
        // STEP 5
        for (GraphNode curNodeNeighbor: curNode.neighbors) {
            // STEP 6
            GraphNode curNodeNeighborCloned = DFS(curNodeNeighbor, map);
            // STEP 7
            curNodeCloned.neighbors.add(curNodeNeighborCloned);
        }
        return curNodeCloned;
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
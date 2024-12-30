import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        map(Map<GraphNode, GraphNode>): a hash map to store all visited node pair from initial graph to cloned graph
        visited(Set<GraphNode>): a hash set to store all the nodes when do BFS
        queue(Queue(GraphNode)): a queue needed when do BFS
    DESCRIPTION:
        STEP 1
        If graph == null || graph.node == null, (process cases when graph is null or graph.node is null)
            STEP 2
            Directly return null
                return graph;
        STEP 3
        Initialize map to HashMap<>()
        Initialize graphCloned to Graph type for final return
            Graph graphCloned = new Graph();
        Initialize visited to HashSet<GraphNode>
            Set<GraphNode> visited = new HashSet<>();
        STEP 4
        Do BFS with map
            BFS(graph.node, map, visited);
        STEP 5
        Assign cloned graph's first node to graphCloned's first node
            graphCloned.node = map.get(graph.node);
        STEP 6
        Return cloned graph
            return graphCloned
        
        -FUNC void BFS(GraphNode node, Map<GraphNode, GraphNode> map, Set<GraphNode> visited)
        STEP 1
        Initialize queue to Queue<GraphNode>
        Initialize curNode to null
        Initialize curNodeCloned to null
        Initialize curNodeNeighborCloned to null
        STEP 2
        Push node to queue
        STEP 3
        Loop while queue is not empty,
            STEP 4
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
                
            Now clone curNode from STEP 5 to STEP 7
                
            If map.containsKey(curNode), (meaning current node is already cloned in previous operation)                
                STEP 5
                Directly get the cloned node from map and assign it to curNodeCloned
                    curNodeCloned = map.get(curNode)
            Else, (meaning current node has not been created, so create it from start)
                STEP 6
                Create newNode from curNode value
                    curNodeCloned = new GraphNode(curNode.val);
                STEP 7
                Put pair relationship (curNode, curNodeCloned) into map
                    map.put(curNode, curNodeCloned);
                    
            Now clone all neighboring nodes of curNode from STEP 8 to STEP 12
                    
            STEP 8
            Iterate each neighbor of curNode with curNodeNeighbor
                If map.containsKey(curNodeNeighbor), (meaning current neighbor node of curNode is already cloned, so directly use it)
                    STEP 9
                    Directly use the cloned neighbor of curNode
                        curNodeNeighborCloned = map.get(curNodeNeighbor)
                Else, (meaning curNode's neighbor has not been cloned)
                    STEP 10
                    Create new node from curNodeNeighbor and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    STEP 11
                    Push (curNodeNeighbor, curNodeNeighborCloned) pair into map
                        map.push(curNodeNeighbor, curNodeNeighborCloned);
                STEP 12
                Add curNodeNeighborCloned into the neighbors property of curNodeCloned
                    curNodeCloned.neighbors.add(curNodeNeighborCloned);
            STEP 13
            Set curNode as visited
                visited.add(curNode);
                
            Now explore all next steps
                
            STEP 14
            Iterate each neighbor of curNode with curNodeNeighbor
                STEP 15
                If isValid(curNodeNeighbor), (meaning the next neighbor node is valid)
                    STEP 16
                    Push curNodeNeighbor into queue
                        queue.offer(curNodeNeighbor);
                    STEP 17
                    Set curNodeNeighbor as visited, (because though it is now in current node, but the neighbor node of current node has already finished clone operation)
                        visited.add(curNodeNeighbor);
        
        -FUNC boolean isValid(GraphNode node, Set<GraphNode> visited)
        STEP 1
        If visited.contains(node), (meaning currrent node is already visited)
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
        BFS(graph.node, map, visited);
        // STEP 5
        graphCloned.node = map.get(graph.node);
        // STEP 6
        return graphCloned;
    }
    
    public static void BFS(GraphNode node, Map<GraphNode, GraphNode> map, Set<GraphNode> visited) {
        // STEP 1
        Queue<GraphNode> queue = new LinkedList<>();
        GraphNode curNode = null;
        GraphNode curNodeCloned = null;
        GraphNode curNodeNeighborCloned = null;
        // STEP 2
        queue.offer(node);
        // STEP 3
        while (!queue.isEmpty()) {
            // STEP 4
            curNode = queue.poll();
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
                /* clone current neighbor start */
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
            
            /* explore all next steps */
            // STEP 14
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                // STEP 15
                if (isValid(curNodeNeighbor, visited)) {
                    // STEP 16
                    queue.offer(curNodeNeighbor);
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
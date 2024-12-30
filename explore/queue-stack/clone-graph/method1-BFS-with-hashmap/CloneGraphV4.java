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
        If map.containsKey(node), (meaning the first node is already cloned, actually it always false)
            STEP 2
            Get cloned node and assign it to curNodeCloned
                curNodeCloned = map.get(node);
        Else, (meaning the first node has not been cloned)
            STEP 3
            Create a new node from node and assign it to curNodeCloned
                curNodeCloned = new GraphNode(node.val);
            STEP 4
            Add pair (node, curNodeCloned) into map
                map.put(node, curNodeCloned);
        STEP 5
        If isValid(node, visited), (meaning current is valid)
            STEP 6
            Add node into queue
                queue.offer(node);
            STEP 7
            Add node into visited
                visited.add(node);
        STEP 8
        Loop while queue is not empty, (this is a solution based on dummy head node, so each node pushed into queue is already cloned and corresponding pair is already put into map, so one can directly get the cloned node from curNode)
            STEP 9
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
            STEP 10
            Get curNodeCloned from curNode
                curNodeCloned = map.get(curNode);
                
            Now clone all neighboring nodes
                
            STEP 11
            Iterate each neighbor of curNode with curNodeNeighbor
                If map.containsKey(curNodeNeighbor), (meaning current neighbor node of curNode is already cloned, so directly use it)
                    STEP 12
                    Directly use the cloned neighbor of curNode
                        curNodeNeighborCloned = map.get(curNodeNeighbor)
                Else, (meaning curNode's neighbor has not been cloned)
                    STEP 13
                    Create new node from curNodeNeighbor and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    STEP 14
                    Push (curNodeNeighbor, curNodeNeighborCloned) pair into map
                        map.push(curNodeNeighbor, curNodeNeighborCloned);
                STEP 15
                Add curNodeNeighborCloned into the neighbors property of curNodeCloned
                    curNodeCloned.neighbors.add(curNodeNeighborCloned);
            
            Now explore all next steps
            
            STEP 16
            Iterate each neighbor of curNode with curNodeNeighbor
                STEP 17
                If isValid(curNodeNeighbor), (meaning the next neighbor node is valid)
                    STEP 18
                    Push curNodeNeighbor into queue
                        queue.offer(curNodeNeighbor);
                    STEP 19
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
        /* clone current node start */
        if (map.containsKey(node)) {
            // STEP 2
            curNodeCloned = map.get(node);
        } else {
            // STEP 3
            curNodeCloned = new GraphNode(node.val);
            // STEP 4
            map.put(node, curNodeCloned);
        }
        /* clone current node start */
        // STEP 5
        if (isValid(node, visited)) {
            // STEP 6
            queue.offer(node);
            // STEP 7
            visited.add(node);
        }
        // STEP 8
        while (!queue.isEmpty()) {
            // STEP 9
            curNode = queue.poll();
            // STEP 10
            curNodeCloned = map.get(curNode);
            
            /* clone all neighboring nodes start */
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
            /* clone all neighboring nodes end */
            
            /* explore all next steps */
            // STEP 16
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                // STEP 17
                if (isValid(curNodeNeighbor, visited)) {
                    // STEP 18
                    queue.offer(curNodeNeighbor);
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
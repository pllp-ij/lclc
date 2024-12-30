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
        If graph == null || graph.node == null, (process cases when graph is empty or graph.node is null)
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
            STEP 5
            If isNotValid(curNode, visited), (meaning if current node is not valid)
                STEP 6
                Skip current iteration to jump to next iteration
                    continue;
                    
            Now clone curNode from STEP 7 to STEP 9
                    
            If map.containsKey(curNode), (meaning current node is already cloned in previous operation)                
                STEP 7
                Directly get the cloned node from map and assign it to curNodeCloned
                    curNodeCloned = map.get(curNode)
            Else, (meaning current node has not been created, so create it from start)
                STEP 8
                Create newNode from curNode value
                    curNodeCloned = new GraphNode(curNode.val);
                STEP 9
                Put pair relationship (curNode, curNodeCloned) into map
                    map.put(curNode, curNodeCloned);
                
            Now clone all neighboring nodes of curNode from STEP 11 to STEP 15
                
            STEP 10
            Iterate each neighbor of curNode with curNodeNeighbor
                If map.containsKey(curNodeNeighbor), (meaning current neighbor node of curNode is already cloned, so directly use it)
                    STEP 11
                    Directly use the cloned neighbor of curNode
                        curNodeNeighborCloned = map.get(curNodeNeighbor)
                Else, (meaning curNode's neighbor has not been cloned)
                    STEP 12
                    Create new node from curNodeNeighbor and assign it to curNodeNeighborCloned
                        curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    STEP 13
                    Push (curNodeNeighbor, curNodeNeighborCloned) pair into map
                        map.push(curNodeNeighbor, curNodeNeighborCloned);
                STEP 14
                Add curNodeNeighborCloned into the neighbors property of curNodeCloned
                    curNodeCloned.neighbors.add(curNodeNeighborCloned);
            STEP 15
            Set curNode as visited
                visited.add(curNode);

            Now explore all next steps
            
            STEP 16
            Iterate each neighbor of curNode with curNodeNeighbor
                STEP 17
                Push curNodeNeighbor into queue
                    queue.offer(curNodeNeighbor);
        
        -FUNC boolean isNotValid(GraphNode node, Set<GraphNode> visited)
        STEP 1
        If node == null ||
           visited.contains(node), (first condition is for cases in which graph is empty, second condition is for all nodes have been visited)
            STEP 2
            return true;
        STEP 3
        return false;
    TIME:
        O(m * n), m is vertices number in graph, and n is the edges number in graph
    SPACE:
        O(m * n + m), (m * n) is for the max number of nodes pushed into queue when do BFS in worst case, m is the vertices number and n is the edges number
*/

class CloneGraphV1 {
    
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
            // STEP 5
            if (isNotValid(curNode, visited)) {
                // STEP 6
                continue;
            }
            
            /* clone curNode start */
            if (map.containsKey(curNode)) {
                // STEP 7
                curNodeCloned = map.get(curNode);
            } else {
                // STEP 8
                curNodeCloned = new GraphNode(curNode.val);
                // STEP 9
                map.put(curNode, curNodeCloned);
            }
            /* clone curNode end */
            
            /* clone all neighboring nodes of curNode start */
            // STEP 10
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                if (map.containsKey(curNodeNeighbor)) {
                    // STEP 11
                    curNodeNeighborCloned = map.get(curNodeNeighbor);
                } else {
                    // STEP 12
                    curNodeNeighborCloned = new GraphNode(curNodeNeighbor.val);
                    // STEP 13
                    map.put(curNodeNeighbor, curNodeNeighborCloned);
                }
                // STEP 14
                curNodeCloned.neighbors.add(curNodeNeighborCloned);
            }
            /* clone all neighboring nodes of curNode end */
            
            // STEP 15
            visited.add(curNode);
            
            /* all next steps start */
            // STEP 16
            for (GraphNode curNodeNeighbor: curNode.neighbors) {
                // STEP 17
                queue.offer(curNodeNeighbor);
            }
            /* all next steps end */
        }
    }
    
    public static boolean isNotValid(GraphNode node, Set<GraphNode> visited) {
        // STEP 1
        if (visited.contains(node)) {
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
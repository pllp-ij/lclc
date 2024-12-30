import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
    
    public GraphNode() {
        this.val = 0;
        this.neighbors = new LinkedList<>();
    }
    
    public GraphNode(int val) {
        this.val = val;
        this.neighbors = new LinkedList<>();
    }
    
    public GraphNode(int val, List<GraphNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

public class Graph {
    public GraphNode node;
    
    public Graph() {
        node = null;
    }
    
    public void createGraph(int[][] relationships) {
        // check if relationship is null, meaning the graph is empty
        if (relationships == null) {
            return;
        }
        // create hash map to store already created node
        Map<Integer, GraphNode> map = new HashMap<>();
        // for final return result of first node
        GraphNode firstNode = null;
        // initialize curNode to null for reusable purpose
        GraphNode curNode = null;
        // initialize neighborNode to null for reusable purpose
        GraphNode neighborNode = null;
        // iterate each node in relationships with i
        for (int idxCurNode = 0; idxCurNode < relationships.length; idxCurNode++) {
            if (map.containsKey(idxCurNode + 1)) {
                // if map does not contain  current node, create one, otherwise use its value by key
                curNode = map.get(idxCurNode + 1);
            } else {
                // create curNode from current index plus 1 to represent the value of that
                curNode = new GraphNode(idxCurNode + 1);
                map.put(idxCurNode + 1, curNode);
            }
            // assign first node to firstNode for final return
            if (idxCurNode == 0) {
                firstNode = curNode;
            }
            // iterate each neighboring node of curNode
            for (int idxNeightbor = 0; idxNeightbor < relationships[idxCurNode].length; idxNeightbor++) {
                // get neighborNode val first
                int neighborVal = relationships[idxCurNode][idxNeightbor];
                // if hashmap contains current neighbor node, get it from hashmap, otherwise, create a new one
                if (map.containsKey(neighborVal)) {
                    neighborNode = map.get(neighborVal);
                } else {
                    neighborNode = new GraphNode(neighborVal);
                    map.put(neighborVal, neighborNode);
                }
                curNode.neighbors.add(neighborNode);
            }
        }
        // return first node in graph
        node = firstNode;
    }
    
    /*
        Print using DFS with recursive method V1
    */
    public String printGraphDFSRecursiveV1() {
        // for final return result
        List<List<Integer>> result = new LinkedList<>();
        // hash set to store all visited node
        Set<GraphNode> visited = new HashSet<>();
        // do DFS recursive V1
        DFSRecursiveV1(node, result, visited);
        
        return convertResultToArrayStr(result);
    }
    public void DFSRecursiveV1(GraphNode curNode, List<List<Integer>> result, Set<GraphNode> visited) {
        // check if current curNode is not valid, directly return
        if (isNotValid(curNode, visited)) {
            return;
        }
        // create a list of neighboring nodes for current curNode
        List<Integer> neighborsList = new LinkedList<>();
        for (GraphNode neighbor: curNode.neighbors) {
            // add current neighbor's value to neighborsList
            neighborsList.add(neighbor.val);
        }
        // add neighborsList to result
        result.add(neighborsList);
        // set current curNode as visited
        visited.add(curNode);
        // iterate each neighbor curNode of current curNode
        for (GraphNode neighbor: curNode.neighbors) {
            DFSRecursiveV1(neighbor, result, visited);
        }
    }
    public boolean isNotValid(GraphNode curNode, Set<GraphNode> visited) {
        // if current node is not valids
        if (curNode == null || visited.contains(curNode)) {
            return true;
        }
        return false;
    }
    
    
    /*
        Print using DFS with recursive method V2
    */
    public String printGraphDFSRecursiveV2() {
        // first process the node is empty
        if (node == null) {
            return "NULL";
        }
        // initialize result
        List<List<Integer>> result = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();
        // do DFS recursive V2
        DFSRecursiveV2(node, result, visited);
        
        return convertResultToArrayStr(result);
    }
    public void DFSRecursiveV2(GraphNode curNode, List<List<Integer>> result, Set<GraphNode> visited) {
        // create neighbor list from neighbors of curNode
        List<Integer> neighborList = new LinkedList<>();
        for (GraphNode neighbor: curNode.neighbors) {
            neighborList.add(neighbor.val);
        }
        // add neightborList into result
        result.add(neighborList);
        // set curNode as visited
        visited.add(curNode);
        // iterate each neighbor of curNode
        for (GraphNode neighbor: curNode.neighbors) {
            // if next neighbor is valid
            if (isValid(neighbor, visited)) {
                DFSRecursiveV2(neighbor, result, visited);
            }
        }
    }
    public boolean isValid(GraphNode curNode, Set<GraphNode> visited) {
        if (visited.contains(curNode)) {
            return false;
        }
        return true;
    }
    
    
    public String convertResultToArrayStr(List<List<Integer>> result) {
        // print graph
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < result.size(); i++) {
            str.append("[");
            for (int j = 0; j < result.get(i).size(); j++) {
                str.append(result.get(i).get(j));
                if (j < result.get(i).size() - 1) {
                    str.append(", ");
                }
            }
            str.append("]");
            if (i < result.size() - 1) {
                str.append(", ");
            }
        }
        str.append("]\n");
        return str.toString();
    }
}
import java.util.Queue;
import java.util.LinkedList;

class BinaryTreeNode {
    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    
    public BinaryTreeNode() {
        val = 0;
        left = null;
        right = null;
    }
    
    public BinaryTreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
    
    public BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree {
    public BinaryTreeNode root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }
    
    public void createBinaryTree(int[] nums) {
        // initialize
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        int idxNums = 0;  // iterate each number in nums
        BinaryTreeNode curNode = null;
        // create root node from nums[idxNums] for final return
        root = new BinaryTreeNode(nums[idxNums]);
        // push first root node into queue
        queue.offer(root);
        // loop while queue is not empty
        while (!queue.isEmpty() && idxNums < nums.length) {
            // pop front node from queue and assign it to curNode
            curNode = queue.poll();
            // increase idxNums by one to point to curNode's left child
            idxNums++;
            // if idxNums within valid range of nums's length and left child is not null, link the left point of curNode to new created left child and push left child into queue
            if (idxNums < nums.length && nums[idxNums] != Integer.MAX_VALUE) {
                curNode.left = new BinaryTreeNode(nums[idxNums]);
                queue.offer(curNode.left);
            }
            // increase idxNums by one to point to curNode's right child
            idxNums++;
            // if idxNums within valid range of nums's length and right child is not null, link the right point of curNode to created right child and push right child into queue
            if (idxNums < nums.length && nums[idxNums] != Integer.MAX_VALUE) {
                curNode.right = new BinaryTreeNode(nums[idxNums]);
                queue.offer(curNode.right);
            }
        }
    }
    
    public String printPreorderRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        printPreorderRecursiveBody(root, str);
        str.append("]");
        return str.toString();
    }
    public void printPreorderRecursiveBody(BinaryTreeNode root, StringBuilder str) {
        if (root == null) {
            return;
        }
        str.append(root.val + " ");
        printPreorderRecursiveBody(root.left, str);
        printPreorderRecursiveBody(root.right, str);
    }
    
    public String printInorderRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        printInorderRecursiveBody(root, str);
        str.append("]");
        return str.toString();
    }
    public void printInorderRecursiveBody(BinaryTreeNode root, StringBuilder str) {
        if (root == null) {
            return;
        }
        printInorderRecursiveBody(root.left, str);
        str.append(root.val + " ");
        printInorderRecursiveBody(root.right, str);
    }
    
    public String printPostorderRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        printPostorderRecursiveBody(root, str);
        str.append("]");
        return str.toString();
    }
    public void printPostorderRecursiveBody(BinaryTreeNode root, StringBuilder str) {
        if (root == null) {
            return;
        }
        printPostorderRecursiveBody(root.left, str);
        printPostorderRecursiveBody(root.right, str);
        str.append(root.val + " ");
    }
    
}
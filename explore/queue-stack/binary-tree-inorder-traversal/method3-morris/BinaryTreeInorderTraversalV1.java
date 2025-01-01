import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
    NOTE:
        The first downward scan from curNode to the left-bottom most node of its left child subtree is to setup the dummy link for returning back to curNode,
        while the second upward scan is to visit all "left" and "root" nodes in inorder and delete the dummy link to restore the initial state of tree
    VARS:
        result(List<Integer>): list of integers, also for final return
        curNode(BinaryTreeNode): a pointer for iterating nodes in tree
        prevNode(BinaryTreeNode): a pointer pointing a node before curNode
    DESCRIPTION:
        STEP 1
        Initialize result to ArrayList<>();
        Initialize curNode to tree.root to process all nodes from root
        Initialize prevNode to null
        STEP 2
        Loop while curNode is not null
            If curNode.left == null, (meaning curNode has no left child, so directly put curNode's value into result)
                STEP 3
                Put curNode's value into result
                    result.add(curNode.val);
                STEP 4
                Move curNode to its right child
                    curNode = curNode.right;
            Else, (meaning curNode has left child)
                STEP 5
                Move prevNode to curNode.left
                    prevNode = curNode.left;
                STEP 6
                Loop while prevNode.right != null and prevNode.right != curNode, (meaning find the right most node of curNode's left subtree for the first time)
                    STEP 7
                    Move prevNode to its right child
                        prevNode = prevNode.right;
                
                Now prevNode is pointing at the right-bottom most node of curNode's left child subtree, which is also the previous node of curNode in inorder traversal, and there are two conditions after existing the while loop above
                    1. prevNode.right == null, (meaning it is the first time visit the right-bottom most node of curNode's left child subtree, similar to the push operation of stack)
                    2. prevNode.right == curNode, (meaning it is the second time visit the right-bottom most node of curNode's left child subtree, similar to the pop operation of stack)
                
                STEP 8
                If prevNode.right == null, (meaning it is the first time visit the right-bottom most node of curNode's left child subtree, similar to the push operation of stack)                
                    STEP 9
                    Link the right field of prevNode to curNode to create a dummy link for returning back to the curNode after visited all nodes in left child subtree
                        prevNode.right = curNode;
                    STEP 10
                    Move curNode to its left child as a new root
                        curNode = curNode.left;
                
                STEP 11
                If prevNode.right == curNode, (meaning it is the second time visit the right-bottom most node of curNode's left child subtree, in this time, all nodes in left child subtree of curNode are already processed, similar to the pop operation of the right-bottom most node of left child subtree in stack)
                    STEP 12
                    Add curNode's value into result
                        result.add(curNode.val);
                    STEP 13
                    Delete the dummy link from prevNode to curNode
                        prevNode.right = null;
                    STEP 14
                    Move curNode to its right child node
                        curNode = curNode.right;
                    
    TIME:
        O(N), N is for all the nodes in tree
    SPACE:
        O(1), no extra space is used
*/

class BinaryTreeInorderTraversalV1 {
    
    public static List<Integer> getInorder(BinaryTree tree) {
        // STEP 1
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode curNode = tree.root;
        BinaryTreeNode prevNode = null;
        // STEP 2
        while (curNode != null) {
            if (curNode.left == null) {
                // STEP 3
                result.add(curNode.val);
                // STEP 4
                curNode = curNode.right;
            } else {
                // STEP 5
                prevNode = curNode.left;
                // STPE 6
                while (prevNode.right != null && prevNode.right != curNode) {
                    // STEP 7
                    prevNode = prevNode.right;
                }
                // STEP 8
                if (prevNode.right == null) {
                    // STEP 9
                    prevNode.right = curNode;
                    // STEP 10
                    curNode = curNode.left;
                }
                // STEP 11
                if (prevNode.right == curNode) {
                    // STEP 12
                    result.add(curNode.val);
                    // STEP 13
                    prevNode.right = null;
                    // STEP 14
                    curNode = curNode.right;
                }
            }
        }
        // STEP 15
        return result;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, Integer.MAX_VALUE, 2, 3};
        int[] nums = {1, 2, 3, 4, 5, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 7, 9};
        
        System.out.println("nums: " + Arrays.toString(nums));
        BinaryTree tree = new BinaryTree();
        tree.createBinaryTree(nums);
        System.out.println("tree created from nums, preorder is: " + tree.printPreorderRecursive());
        System.out.println("tree created from nums, inorder is: " + tree.printInorderRecursive());
        System.out.println("tree created from nums, postorder is: " + tree.printPostorderRecursive());
        
        List<Integer> result = getInorder(tree);
        System.out.println("tree inorder: " + result.toString());
    }
}
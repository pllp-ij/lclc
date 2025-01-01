import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/*
    VARS:
        result(List<Integer>): list of integers, also for final return
    DESCRIPTION:
        STEP 1
        Initialize result to ArrayList<>();
        STEP 2
        Do recursion using tree.root and result
            getInorderRecursive(tree.root, result);
        STEP 3
        Return result
        
        -FUNC void getInorderRecursive(BinaryTreeNode curNode, List<Integer> result)
        STEP 1
        If curNode == null, (meaning curNode is not valid)
            STEP 2
            Directly return;
        STEP 3
        Recursive calling method for left child of curNode
            getInorderRecursive(curNode.left, result);
        STEP 4
        Add curNode's value into result for inorder
            result.add(curNode.val);
        STEP 5
        Recursive calling method for right child of curNode
            getInorderRecursive(curNode.right, result);
    TIME:
        O(N), N is for all the nodes in tree
    SPACE:
        O(N), N calling stack is used when do inorder
*/

class BinaryTreeInorderTraversalV1 {
    
    public static List<Integer> getInorder(BinaryTree tree) {
        // STEP 1
        List<Integer> result = new ArrayList<>();
        // STEP 2
        getInorderRecursive(tree.root, result);
        // STEP 3
        return result;
    }
    
    public static void getInorderRecursive(BinaryTreeNode curNode, List<Integer> result) {
        // STEP 1
        if (curNode == null) {
            // STEP 2
            return;
        }
        // STEP 3
        getInorderRecursive(curNode.left, result);
        // STEP 4
        result.add(curNode.val);
        // STEP 5
        getInorderRecursive(curNode.right, result);
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
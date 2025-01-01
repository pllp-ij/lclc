import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
    VARS:
        result(List<Integer>): list of integers, also for final return
        stack(Stack<BinaryTreeNode>): a stack is used when do inorder recursion
        curNode(BinaryTreeNode): a pointer for iterating nodes in tree
    DESCRIPTION:
        STEP 1
        Initialize result to ArrayList<>();
        Initialize stack to Stack<>();
        Initialize curNode to tree.root start from the root node
        STEP 2
        Loop while curNode != null, (push all nodes from root and all left child nodes along the path)
            STEP 3
            Push curNode into stack
                stack.push(curNode)
            STEP 4
            Move curNode to its left child
                curNode = curNode.left;
        
        Now all nodes from root to left-most node are pushed into stack, so when pop node from stack, the more left-bottom node will be processed early, which in accordance with the inorder logic(left child, root, right child)
        
        STEP 5
        Loop while stack is not empty, (meaning visit nodes from left to root)
            STEP 6
            Pop front node from stack and assign it to curNode
                curNode = stack.pop();
            STEP 7
            Add curNode.val into result
                result.add(curNode.val);
            STEP 8
            If curNode.right is not empty, (meaning curNode has right subtree)
                STEP 9
                Move curNode to curNode.right
                    curNode = curNode.right;
                STEP 10
                Loop while curNode != null, (push second line from curNode to left-bottom node)
                    STEP 11
                    Push curNode into stack
                        stack.push(curNode)
                    STEP 12
                    Move curNode to its left child
                        curNode = curNode.left;
        STEP 13
        Return result
    TIME:
        O(N), N is for all the nodes in tree
    SPACE:
        O(N), max number of nodes pushed into stack is N in worst case
*/

class BinaryTreeInorderTraversalV1 {
    
    public static List<Integer> getInorder(BinaryTree tree) {
        // STEP 1
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curNode = tree.root;
        // STEP 2
        while (curNode != null) {
            // STEP 3
            stack.push(curNode);
            // STEP 4
            curNode = curNode.left;
        }
        // STEP 5
        while (!stack.isEmpty()) {
            // STEP 6
            curNode = stack.pop();
            // STEP 7
            result.add(curNode.val);
            // STEP 8
            if (curNode.right != null) {
                // STEP 9
                curNode = curNode.right;
                // STEP 10
                while (curNode != null) {
                    // STEP 11
                    stack.push(curNode);
                    // STEP 12
                    curNode = curNode.left;
                }
            }
        }
        // STEP 13
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
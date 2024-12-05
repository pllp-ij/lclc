import java.util.Arrays;
import com.anno.trie.TrieTree;
import com.anno.trie.TrieNode;

/**
    VARS:
        result(StringBuilder): final return result
    DESCRIPTION:
        STEP 1
        Define trie tree class
        STEP 2
        Construct trie tree from the strs array and get the final longest common prefix string
        STEP 3
        Return final result
    TIME:
        
    SPACE:
*/

class LongestCommonPrefixV1 {
    
    public static String getLongestCommonPrefix(String[] strs) {
        // STEP 1
        TrieTree tree = new TrieTree();
        // STEP 2
        String result = tree.longestCommonPrefix(strs, strs[0]);
        // STEP 3
        return result;
    }
    
    public static void main(String[] args) {
        // String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"dog", "racecar", "car"};
        // String[] strs = {"sad", "sateqtr", "saaa"};
        String result = getLongestCommonPrefix(strs);
        System.out.println("longest common prefix: " + result);
    }
}
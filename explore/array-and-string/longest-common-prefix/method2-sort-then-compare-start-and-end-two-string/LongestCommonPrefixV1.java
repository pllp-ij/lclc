import java.util.Arrays;

/**
    VARS:
        result(StringBuilder): final return result
    DESCRIPTION:
        STEP 1
        Initialize commonPrefixLenOfTwo to 0
        STEP 2
        Sort the strs array
        STEP 3
        Iterate each character in strs[0] with index i
            STEP 4
            Get char at index i in strs[0]
                char curCharInFirst = strs[0].charAt(i);
            STEP 5
            Get char at index i in strs[strs.length - 1]
                char curCharInLast = strs[strs.length - 1].charAt(i);
            STEP 6
            If curCharInFirst != curCharInLast, (meaning at the same index of i in first or last string, two character are equal)
                STEP 7
                Break the loop
            STEP 8
            Add current same character into final result
                result.append(strs[0].charAt(i));
        STEP 9
        Return result.toString()
    TIME:
        O(sorted(strs)), depend on the time of sorting the strs array, commonly, sorted(strs) ~ O(n * log(n) * n) ~ O(n^2 * log(n))
    SPACE:
        O(sorted(strs)), depend on the space used in sorting the strs array
*/

class LongestCommonPrefixV1 {
    
    public static String getLongestCommonPrefix(String[] strs) {
        // STEP 1
        StringBuilder result = new StringBuilder();
        // STEP 2
        Arrays.sort(strs);
        // STEP 3
        for (int i = 0; i < strs[0].length(); i++) {
            // STEP 4
            char curCharInFirst = strs[0].charAt(i);
            // STEP 5
            char curCharInLast = strs[strs.length - 1].charAt(i);
            // STEP 6
            if (curCharInFirst != curCharInLast) {
                // STEP 7
                break;
            }
            // STEP 8
            result.append(curCharInFirst);
        }
        // STEP 9
        return result.toString();
    }
    
    public static void main(String[] args) {
        // String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"dog", "racecar", "car"};
        // String[] strs = {"sad", "sateqtr", "saaa"};
        String result = getLongestCommonPrefix(strs);
        System.out.println("longest common prefix: " + result);
    }
}
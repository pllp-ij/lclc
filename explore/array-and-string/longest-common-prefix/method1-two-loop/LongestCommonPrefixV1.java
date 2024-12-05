/**
    VARS:
        result(StringBuilder): final return result
    DESCRIPTION:
        STEP 1
        Initialize result to StringBuilder
        STEP 2
        Iterate each character in strs[0] with index i
            STEP 3
            Iterate each string in remaining strs from 1 to strs.length - 1 with index j
                STEP 4
                If i >= str[j].length() or strs[0].charAt(i) != strs[j].charAt(i), (meaning current index i is outside the right edge of string strs[j] or two characters at index i are not equal)
                    STEP 5
                    Return result.toString()
            STEP 6
            Add current character with index i in strs[0]
                result.append(strs[0].charAt(i))
        STEP 7
        Return result
    TIME:
        O(n * m), n is the number of strings in strs, m is the length of the shortest string in strs
    SPACE:
        O(1)
*/

class LongestCommonPrefixV1 {
    
    public static String getLongestCommonPrefix(String[] strs) {
        // STEP 1
        StringBuilder result = new StringBuilder();
        // STEP 2
        for (int i = 0; i < strs[0].length(); i++) {
            // STEP 3
            for (int j = 1; j < strs.length; j++) {
                // STEP 4
                if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    // STEP 5
                    return result.toString();
                }
            }
            // STEP 6
            result.append(strs[0].charAt(i));
        }
        // STEP 7
        return result.toString();
    }
    
    public static void main(String[] args) {
        // String[] strs = {"flower", "flow", "flight"};
        // String[] strs = {"dog", "racecar", "car"};
        String[] strs = {"sad", "sateqtr", "saaa"};
        String result = getLongestCommonPrefix(strs);
        System.out.println("longest common prefix: " + result);
    }
}
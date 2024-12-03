/**
    VARS:
        rightBoundary(int): the right most boundary, the length from which to the end of string a is at least the length of string b
    DESCRIPTION:
        STEP 1
        Initialize rightBoundary to haystack.length() - needle.length()
        STEO 2
        Iterate each char with index i in haystack string from 0 to rightBoundary
            STEP 3
            Get substring from index i to i + needle.length() - 1 by calling method
                String curSubStr = getSubStr(haystack, i, i + needle.length())
            If curSubStr equal to needle by calling compare method,
                STEP 4
                Return i
        STEP 5
        Return -1
    TIME:
        O((haystack.length() - needle.length() + 1) * needle.length()), the (haystack.length() - needle.length() + 1) means the all indexes in string a enumerated from which to compare with string b without exceeding the right edge of string a
    SPACE:
        O(1)

*/

class ImplementStrStrV1 {
    
    public static int getFirstIndex(String haystack, String needle) {
        // STEP 1
        int rightBoundary = haystack.length() - needle.length();
        // STEP 2
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            // STEP 3
            String curSubStr = getSubStr(haystack, i, i + needle.length());
            if (curSubStr.equals(needle)) {
                // STEP 4
                return i;
            }
        }
        // STEP 5
        return -1;
    }
    
    public static String getSubStr(String haystack, int start, int endPlusOne) {
        StringBuilder res = new StringBuilder();
        while (start < endPlusOne) {
            res.append(haystack.charAt(start));
            start++;
        }
        return res.toString();
    }
    
    public static void main(String[] args) {
        // String[] haystackNeedle = {"sadbutsad", "sad"};
        // String[] haystackNeedle = {"adbutsad", "sad"};
        // String[] haystackNeedle = {"adbutsa", "sad"};
        // String[] haystackNeedle = {"sa", "sad"};
        // String[] haystackNeedle = {"", "sad"};
        // String[] haystackNeedle = {"sad", ""};
        String[] haystackNeedle = {"nfxsssdtqmf", "ssd"};
        int firstIdx = getFirstIndex(haystackNeedle[0], haystackNeedle[1]);
        System.out.println("\"" + haystackNeedle[1] + "\" in " + "\"" + haystackNeedle[0] + "\" first index is: " + firstIdx);
    }
}
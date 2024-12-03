
/**
    VARS:
        idxHayStack(int): iterate index for haystack
        idxNeedle(int): iterate index for needle
    DESCRIPTION:
        STEP 1
        Initialize idxHayStack to 0
        Initialize idxNeedle to 0
        STEP 2
        Loop while true, (repeat the process)
            STEP 3
            Loop while idxHayStack < haystack.length() and idxNeedle < needle.length() and haystack.charAt(idxHayStack) != needle.charAt(0)
                STEP 4
                Increase idxHayStack by one
                    idxHayStack++;
            
            Now there are two conditions:
                1 idxHayStack >= haystack.length(), (meaning the haystack is already used up)
                2 idxHayStack < haystack.length() and idxNeedle >= needle.length(), (because the idxNeedle won't moved in above while loop, so this means the initial length of needle string is 0)
                3 idxHayStack < haystack.length() and idxNeedle < needle.length() and haystack[idxHayStack] == needle[0], (meeaning both strings are not used up, and now finding the first place two elements equal)
                
            STEP 5
            If idxHayStack >= haystack.length()
                STEP 6
                Return -1
            
            Now idxHayStack < haystack.length() in the following
            
            STEP 7
            If idxNeedle >= needle.length(), (meaning string needle is initially empty, so return 0 index in haystack directly)
                STEP 8
                Return 0
            
            Now haystack.charAt(idxHayStack) == needle.charAt(0)
            
            STEP 9
            Loop while idxHayStack < haystack.length() and idxNeedle < idxNeedle.length() and haystack.charAt(idxHayStack) == needle.charAt(idxNeedle)
                STEP 10
                Increase idxHayStack and idxNeedle by one at the same time
                    idxHayStack++;
                    idxNeedle++;
            
            Now there are three conditions:
                1 idxHayStack >= haystack.length(), (meaning haystack is used up)
                2 idxHayStack < haystack.length() and idxNeedle >= idxNeedle.length(), (meaning haystack is not used up buy needle is used up)
                3 idxHayStack < haystack.length() and idxNeedle < idxNeedle.length() and haystack.charAt(idxHayStack) != needle.charAt(idxNeedle), (meaning both not used up but met with two elements not equal)
                
            STEP 11
            If idxHayStack >= haystack.length(),
                If idxNeedle < needle.length(), (meaning needle is not used up)
                    STEP 12
                    Return -1
                Else, (meaning idxNeedle >= needle.length(), the needle is also used up at the same time)
                    STEP 13
                    Return idxHayStack - needle.length();
            
            Now idxHayStack < haystack.length() in the following
            
            STEP 14
            If idxNeedle >= needle.length(), (meaning haystack is not used up but needle is used up)
                STEP 15
                Return idxHayStack - needle.length()
            
            Now idxHayStack < haystack.length() and idxNeedle < needle.length() in the following
            
            STEP 16
            If haystack.charAt(idxHayStack) != needle.charAt(idxNeedle), (meaning both are not used up, but the two elements are not equal)
                STEP 17
                Reset idxHayStack back the amount of idxNeedle that idxNeedle advanced
                    idxHayStack = idxHayStack - idxNeedle + 1;
                STEP 18
                Reset idxNeedle to 0 again
                    idxNeedle = 0;
    TIME:
        O(a.length + a.length / b.length * b.length) ~ O(max(a.length, b.length)), the all elements in string a and plus the maximum occurance of b in a
    SPACE:
        O(1)
    TO BE OPTIMIZED:
        Replace two inner while loops with if statements
*/

class ImplementStrStrV1 {
    
    public static int getFirstIndex(String haystack, String needle) {
        // STEP 1
        int idxHayStack = 0;
        int idxNeedle = 0;
        // STEP 2
        while (true) {
            // STEP 3
            while (idxHayStack < haystack.length() && idxNeedle < needle.length() && haystack.charAt(idxHayStack) != needle.charAt(idxNeedle)) {
                // STEP 4
                idxHayStack++;
            }
            // STEP 5
            if (idxHayStack >= haystack.length()) {
                // STEP 6
                return -1;
            }
            // STEP 7
            if (idxNeedle >= needle.length()) {
                // STEP 8
                return 0;
            }
            // STEP 9
            while (idxHayStack < haystack.length() && idxNeedle < needle.length() && haystack.charAt(idxHayStack) == needle.charAt(idxNeedle)) {
                // STEP 10
                idxHayStack++;
                idxNeedle++;
            }
            // STEP 11
            if (idxHayStack >= haystack.length()) {
                if (idxNeedle >= needle.length()) {
                    // STEP 12
                    return idxHayStack - needle.length();
                } else {
                    // STEP 13
                    return -1;
                }
            }
            // STEP 14
            if (idxNeedle >= needle.length()) {
                // STEP 15
                return idxHayStack - needle.length();
            }
            // STEP 16
            if (haystack.charAt(idxHayStack) != needle.charAt(idxNeedle)) {
                // STEP 18
                idxHayStack = idxHayStack - idxNeedle + 1;
                // STEP 17
                idxNeedle = 0;
            }
        }
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
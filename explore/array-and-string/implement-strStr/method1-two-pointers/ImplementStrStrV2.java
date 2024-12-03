/**
    VARS:
        idxHayStack(int): iterate index for haystack
        idxNeedle(int): iterate index for needle
    DESCRIPTION:
        STEP 1
        Initialize idxHayStack to 0
        Initialize rightBoundary to haystack.length() - needle.length()
        Initialize idxNeedle to 0
        STEP 2
        Loop while idxNeedle < needle.length() and idxHayStack < haystack.length(), 
            If haystack.charAt(idxHayStack) != needle.charAt(idxNeedle), (meaning the two characters are not equal)
                STEP 3
                Reset idxHaystack back to the amount advanced by idxNeedle to the start, and them add one to next position
                    idxHayStack = idxHayStack - idxNeedle + 1
                STEP 4
                Reset idxNeedle to 0 to refresh the iteration on needle
                    idxNeedle = 0
            Else, (meaning the two chars are equal)
                STEP 5
                Increase idxHayStack and idxNeedle by one at the same time
                    idxHayStack++;
                    idxNeedle++;
        Now there are two contions:
            1 idxNeedle >= needle.length(), (meaning the needle is used up, no matter the haystack is used up or not, all signify the end of searching)
            2 idxNeedle < needle.length() and idxHayStack >= haystack.length(), (meaning the haystack is already used up but needle not)
            
        STEP 6
        If idxNeedle >= needle.length(),
            STEP 7
            Return idxHayStack - needle.length()
        
        Now idxNeedle < needle.length() in the following
        
        STEP 8
        Return -1
    TIME:
        O(a.length + a.length / b.length * b.length) ~ O(max(a.length, b.length)), the all elements in string a and plus the maximum occurance of b in a
    SPACE:
        O(1)

*/

class ImplementStrStrV2 {
    
    public static int getFirstIndex(String haystack, String needle) {
        // STEP 1
        int idxHayStack = 0;
        int idxNeedle = 0;
        // STEP 2
        while (idxNeedle < needle.length() && idxHayStack < haystack.length()) {
            if (haystack.charAt(idxHayStack) != needle.charAt(idxNeedle)) {
                // STEP 3
                idxHayStack = idxHayStack - idxNeedle + 1;
                // STEP 4
                idxNeedle = 0;
            } else {
                // STEP 5
                idxHayStack++;
                idxNeedle++;
            }
        }
        // STEP 6
        if (idxNeedle >= needle.length()) {
            // STEP 7
            return idxHayStack - needle.length();
        }
        // STEP 8
        return -1;
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
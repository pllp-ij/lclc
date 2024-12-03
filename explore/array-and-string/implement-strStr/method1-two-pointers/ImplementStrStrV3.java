
/**
    NOTICE:
        This V3 is reconstruct from V1, but evidently, the final format in V3 is almost as same as V2, so the main differences between V1 and V2 are that:
            1 The extra conditions "idxHayStack < haystack.length() and idxNeedle < needle.length()" in inner two while loop is deleted in V2, and the two inner while loop are replaced with two if statements so that
            haystack at the idxHayStack or needle at the idxNeedle won't be out of range of haystack or needle by two inner while loop, because in V2 after the execution of each if statements, there will be a recheck on condition for outside while loop, if
            the two inner while loop must be kept, the outside condition "idxHayStack < haystack.length() and idxNeedle < needle.length()" must added into two inner while loop condition and after the two while loop but still within the outside while loop, there should be extra if statement to judge if idxHayStack or idxNeedle is moved outside the valid range
            2 In V1, there are two types of moments when haystack.charAt(idxHayStack) != needle.charAt(idxNeedle), the first is check the need.charAt(0) in haystack, and another is when need.charAt(0) is already found in haystack
            but some char in the following after need.charAt(0) mismatch, actually these two conditons can be merged into a single format written in V2: 
                idxHayStack = idxHayStack - idxNeedle + 1;
                idxNeedle = 0;
            3 In V3, the condition of "idxNeedle < needle.length()" is put before condition "idxHayStack < haystack.length()", this arangement will be easier for the following if statements to judge the validation of idxHayStack
            and idxNeedle after two inner while loops
            4 
    VARS:
        idxHayStack(int): iterate index for haystack
        idxNeedle(int): iterate index for needle
    DESCRIPTION:
        STEP 1
        Initialize idxHayStack to 0
        Initialize idxNeedle to 0
        STEP 2
        // initial wrong logic: Loop while idxNeedle < needle.length() and idxHayStack < haystack.length()
        Loop while idxNeedle <= needle.length() and idxHayStack <= haystack.length(), (repeat the process)
            STEP 3
            // initial wrong logic: Loop while haystack.charAt(idxHayStack) != needle.charAt(0)
            Loop while idxNeedle < needle.length() and idxHayStack < haystack.length() and haystack.charAt(idxHayStack) != needle.charAt(0)
                STEP 4
                Increase idxHayStack by one
                    idxHayStack++;
            STEP 5
            If idxNeedle >= needle.length(), (for cases in which needle is empty string, because in above while loop, the idxNeedle is actually not changed, so if idxNeedle >= needle.length() means the needle string initially is empty)
                STEP 6
                Return 0
            STEP 7
            // initial wrong logic: Loop while haystack.charAt(idxHayStack) == needle.charAt(idxNeedle)
            Loop while idxNeedle < needle.length() and idxHayStack < haystack.length() and haystack.charAt(idxHayStack) == needle.charAt(idxNeedle)
                STEP 8
                Increase idxHayStack and idxNeedle by one at the same time
                    idxHayStack++;
                    idxNeedle++;
            STEP 9
            If idxNeedle >= needle.length(), (meaning haystack is not used up but needle is used up)
                STEP 10
                Return idxHayStack - needle.length()
            
            Now idxNeedle < needle.length() in the following
            
            STEP 11
            If idxHayStack >= haystack.length(),
                STEP 12
                Return -1
        STEP 13
        Return -1 (this return clause will not be executed forever, just for syntax correct)
    TIME:
        O(a.length + a.length / b.length * b.length) ~ O(max(a.length, b.length)), the all elements in string a and plus the maximum occurance of b in a
    SPACE:
        O(1)

*/

class ImplementStrStrV3 {
    
    public static int getFirstIndex(String haystack, String needle) {
        // STEP 1
        int idxHayStack = 0;
        int idxNeedle = 0;
        // STEP 2
        // initial wrong logic: while (idxNeedle < needle.length() && idxHayStack < haystack.length()) {
        while (idxNeedle <= needle.length() && idxHayStack <= haystack.length()) {
            // STEP 3
            // initial wrong logic: while (haystack.charAt(idxHayStack) != needle.charAt(0)) {
            while (idxNeedle < needle.length() && idxHayStack < haystack.length() && haystack.charAt(idxHayStack) != needle.charAt(0)) {
                // STEP 4
                idxHayStack++;
            }
            // STEP 5
            if (idxNeedle >= needle.length()) {
                // STEP 6
                return 0;
            }
            // STEP 7
            // initial wrong logic: while (haystack.charAt(idxHayStack) == needle.charAt(idxNeedle)) {
            while (idxNeedle < needle.length() && idxHayStack < haystack.length() && haystack.charAt(idxHayStack) == needle.charAt(idxNeedle)) {
                // STEP 8
                idxHayStack++;
                idxNeedle++;
            }
            // STEP 9
            if (idxNeedle >= needle.length()) {
                // STEP 10
                return idxHayStack - needle.length();
            }
            // STEP 11
            if (idxHayStack >= haystack.length()) {
                // STEP 12
                return -1;
            }
            // STEP 13
            if (haystack.charAt(idxHayStack) != needle.charAt(idxNeedle)) {
                // STEP 14
                idxHayStack = idxHayStack - idxNeedle + 1;
                // STEP 15
                idxNeedle = 0;
            }
        }
        // STEP 16
        return -1;
    }
    
    public static void main(String[] args) {
        String[] haystackNeedle = {"sadbutsad", "sad"};
        // String[] haystackNeedle = {"adbutsad", "sad"};
        // String[] haystackNeedle = {"adbutsa", "sad"};
        // String[] haystackNeedle = {"sa", "sad"};
        // String[] haystackNeedle = {"", "sad"};
        // String[] haystackNeedle = {"sad", ""};
        // String[] haystackNeedle = {"nfxsssdtqmf", "ssd"};
        // String[] haystackNeedle = {"nfxnqtqmf", "ssd"};
        int firstIdx = getFirstIndex(haystackNeedle[0], haystackNeedle[1]);
        System.out.println("\"" + haystackNeedle[1] + "\" in " + "\"" + haystackNeedle[0] + "\" first index is: " + firstIdx);
    }
}
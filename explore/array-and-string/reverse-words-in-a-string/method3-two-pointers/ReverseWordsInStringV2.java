/*
    NOTE:
        Compared to V1, in V2, the while loops are nested within the outside one so the idxEnd may extend the right edge, so there should be extra check for that
    VARS:
        idxResult(int): the index of final result for adding elements
        idxStart(int): indicate the start of new word
        idxEnd(int): indicate the end of new word
        sNew(StringBuilder): a new mutable string of initial one
    DESCRIPTION:
        STEP 1
        Initialize idxResult to 0
        Initialize idxStart to 0
        Initialize idxEnd to 0
        Initialize mutable sNew to StringBuilder from initial string
        STEP 2
        Reverse sNew
            sNew.reverse();
        STEP 3
        Loop while idxStart < sNew.length()
            If sNew.charAt(idxStart) != '.', (meaning meet new word)
                If idxResult != 0, (meaning the newly met word is not the first one)
                    STEP 4
                    Add '.' to idxResult before adding current new word
                        sNew.setCharAt(idxResult, '.');
                    STEP 5
                    Advance idxResult by one step
                        idxResult++;
                STEP 6
                Set idxEnd to idxResult for iterating new word
                    idxEnd = idxStart;
                STEP 7
                Loop while idxEnd < sNew.length() and sNew.charAt(idxEnd) != '.', (meaning adding each character in new word)
                    STEP 8
                    Add sNew.charAt(idxEnd) to idxResult
                        sNew.setCharAt(idxResult, sNew.charAt(idxEnd));
                    STEP 9
                    Advance both idxResult and idxEnd by one step
                        idxResult++;
                        idxEnd++;
                
                Now there are two conditions:
                    1 idxEnd >= sNew.length(), (meaning last word is at the end of sNew, once more reverse operation should be done in the following)
                    2 idxEnd < sNew.length() and sNew.charAt(idxEnd) == '.', (meaning the end of new word, again once more reverse operation should be done in the following)
                Since both two conditions should do reverse operation, so first do the reverse operation and then check these two conditions
                
                STEP 10
                Calculate the start point of the reverse range
                    int reverseRangeStart = idxResult - (idxEnd - idxStart);
                STEP 11
                Calculate the end point of the reverse range
                    int reverseRangeEnd = idxResult - 1;
                STEP 12
                Reverse sNew range from reverseRangeStart to reverseRangeEnd
                    reverseRange(sNew, reverseRangeStart, reverseRangeEnd);
                    
                STEP 13
                If idxEnd >= sNew.length()
                    STEP 14
                    Break the loop
                
                Now idxEnd < sNew.length() and sNew.charAt(idxEnd) == '.'
                
                STEP 15
                Advance idxEnd by one step
                    idxEnd++;
                STEP 16
                Set idxStart to idxEnd
                    idxStart = idxEnd;
            Else, (meaning sNew.charAt(idxStart) == '.')
                STEP 17
                Advance idxStart by one step
                    idxStart++;
        STEP 18
        Return substring of sNew from 0 to idxResult - 1
            return sNew.substring(0, idxResult);
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class ReverseWordsInStringV2 {
    
    public static String getReverseWords(String s) {
        // STEP 1
        int idxResult = 0;
        int idxStart = 0;
        int idxEnd = 0;
        StringBuilder sNew = new StringBuilder(s);
        // STEP 2
        sNew.reverse();
        // STEP 3
        while (idxStart < sNew.length()) {
            if (sNew.charAt(idxStart) != '.') {
                if (idxResult != 0) {
                    // STEP 4
                    sNew.setCharAt(idxResult, '.');
                    // STEP 5
                    idxResult++;
                }
                // STEP 6
                idxEnd = idxStart;
                // STEP 7
                while (idxEnd < sNew.length() && sNew.charAt(idxEnd) != '.') {
                    // STEP 8
                    sNew.setCharAt(idxResult, sNew.charAt(idxEnd));
                    // STEP 9
                    idxResult++;
                    idxEnd++;
                }
                // STEP 10
                int reverseRangeStart = idxResult - (idxEnd - idxStart);
                // STEP 11
                int reverseRangeEnd = idxResult - 1;
                // STEP 12
                reverseRange(sNew, reverseRangeStart, reverseRangeEnd);
                // STEP 13
                if (idxEnd >= sNew.length()) {
                    // STEP 14
                    break;
                }
                // STEP 15
                idxEnd++;
                // STEP 16
                idxStart = idxEnd;
            } else {
                // STEP 17
                idxStart++;
            }
        }
        // STEP 18
        return sNew.substring(0, idxResult);
    }
    
    public static void reverseRange(StringBuilder s, int start, int end) {
        while (start < end) {
            char temp = s.charAt(start);
            s.setCharAt(start, s.charAt(end));
            s.setCharAt(end, temp);
            start++;
            end--;
        }
    }
    
    public static void main(String[] args) {
        String s = "..the.sky...is.light_blue...";
        System.out.println("Before: " + s);
        String result = getReverseWords(s);
        System.out.println("After: " + result);
    }
}
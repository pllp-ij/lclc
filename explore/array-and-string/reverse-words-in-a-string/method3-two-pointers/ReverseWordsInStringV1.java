/*
    VARS:
        idxResult(int): the index of final result for adding elements
        idxStart(int): indicate the start of new word
        idxEnd(int): indicate the end of new word
        sNew(StringBuilder): a new mutable string of initial one
        newWordAppear(boolean): indicate if current iteration is a new appearance of new word
    DESCRIPTION:
        STEP 1
        Initialize idxResult to 0;
        Initialize idxStart to 0;
        Initialize idxEnd to 0;
        Initialize newWordAppear to true
        Initialize sNew to a new StringBuilder from initial s
            StringBuilder sNew = new StringBuilder(s);
        STEP 2
        Reverse sNew from 0 to s.length - 1 by calling built-in method
            sNew.reverse()
        STEP 3
        Loop while idxStart < s.length()
            If sNew.charAt(idxStart) != '.', (meaning the beginning of new word)
                If newWordAppear is true and idxResult != 0, (meaning new word appear at current iteration and it is not the first insertion of word)
                    STEP 4
                    Insert single '.' character in idxResult
                        sNew.setCharAt(idxResult, '.');
                    STEP 5
                    Advance idxResult by one step
                        idxResult++;
                STEP 6
                If idxEnd < idxStart, (meaning the idxEnd is left behind in last iteration of word)
                    STEP 7
                    Set idxEnd to idxStart to start this new turn of iteration on new word
                        idxEnd = idxStart;
                STEP 8
                If idxEnd < sNew.length() and sNew.charAt(idxEnd) != '.', (meaning idxEnd still within valid range of new word)
                    STEP 9
                    Copy char at idxEnd to idxResult
                        sNew.setCharAt(idxResult, sNew.charAt(idxEnd));
                    STEP 10
                    Advance both idxEnd and idxResult by one step
                        idxEnd++;
                        idxResult++;
                    STEP 11
                    The copy operation means current iteration is not meeting a new word, so set newWordAppear to false
                        newWordAppear = false;

                Else, (the logic in the following must be exclusive to above logic, so there is a else statement)
                
                    Now there are two conditions
                        1 idxEnd >= sNew.length(), (meaning the final sub range of sNew is scanned completed, do once more reverse operation in the following then break the loop)
                        2 idxEnd < sNew.length() and sNew.charAt(idxEnd) == '.', (meaning the end of current word, do reverse operation in the following then continue to next iteration)
                        
                    STEP 12
                    Calculate the start point of the range to be reversed
                        int reverseRangeStart = idxResult - (idxEnd - idxStart);
                    STEP 13
                    Calculate the end point of the range to be reversed
                        int reverseRangeEnd = idxResult - 1
                    STEP 14
                    Reverse sNew from reverseRangeStart to reverseRangeEnd by calling method
                        reverseRange(sNew, reverseRangeStart, reverseRangeEnd);
                    STEP 15
                    If idxEnd >= sNew.length(),
                        STEP 16
                        Break the loop
                    
                    Now idxEnd < sNew.length() and sNew.charAt(idxEnd) == '.' in the following
                    
                    STEP 17
                    Advance idxEnd by one step
                        idxEnd++;
                    STEP 18
                    Set idxStart to idxEnd
                        idxStart = idxEnd
                    STEP 19
                    Since current iteration meet a '.', so the next iteration will be a new word, set newWordAppear to true
                        newWordAppear = true;
            Else, (meaning if sNew.charAt(idxStart) != '.')
                STEP 20
                Advance idxStart by one step
                    idxStart++;
        STEP 21
        Return substring of sNew from 0 to idxResult - 1
            return sNew.substring(0, idxResult)
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class ReverseWordsInStringV1 {
    
    public static String getReverseWords(String s) {
        // STEP 1
        int idxResult = 0;
        int idxStart = 0;
        int idxEnd = 0;
        boolean newWordAppear = true;
        StringBuilder sNew = new StringBuilder(s);
        // STEP 2
        sNew.reverse();
        // STEP 3
        while (idxStart < sNew.length()) {
            if (sNew.charAt(idxStart) != '.') {
                if (newWordAppear && idxResult != 0) {
                    // STEP 4
                    sNew.setCharAt(idxResult, '.');
                    // STEP 5
                    idxResult++;
                }
                // STEP 6
                if (idxEnd < idxStart) {
                    // STEP 7
                    idxEnd = idxStart;
                }
                // STEP 8
                if (idxEnd < sNew.length() && sNew.charAt(idxEnd) != '.') {
                    // STEP 9
                    sNew.setCharAt(idxResult, sNew.charAt(idxEnd));
                    // STEP 10
                    idxResult++;
                    idxEnd++;
                    // STEP 11
                    newWordAppear = false;
                } else {
                    // STEP 12
                    int reverseRangeStart = idxResult - (idxEnd - idxStart);
                    // STEP 13
                    int reverseRangeEnd = idxResult - 1;
                    // STEP 14
                    reverseRange(sNew, reverseRangeStart, reverseRangeEnd);
                    // STEP 15
                    if (idxEnd >= sNew.length()) {
                        // STEP 16
                        break;
                    }
                    // STEP 17
                    idxEnd++;
                    // STEP 18
                    idxStart = idxEnd;
                    // STEP 19
                    newWordAppear = true;
                }
            } else {
                // STEP 20
                idxStart++;
            }
        }
        // STEP 21
        return sNew.substring(0, idxResult);
    }
    
    public static void reverseRange(StringBuilder s, int start, int end) {
        char temp = 'a';
        while (start < end) {
            temp = s.charAt(start);
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
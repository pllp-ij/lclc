/*
    VARS:
        idxEnd(int): indicate the end of word
        idxStart(int): indicate the start of word
        sNew(StringBuilder): a mutale string of initial one
    DESCRIPTION:
        STEP 1
        Initialize idxEnd to 0
        Initialize idxStart to 0
        Initialize sNew to StringBuilder from initial string
        STEP 2
        Loop while idxStart < sNew.length()
            If sNew.charAt(idxStart) != ' ', (meaning current is still within word)
                If idxEnd < sNew.length() and sNew.charAt(idxEnd) != ' ', (meaning iteration on current word is not stopped)
                    STEP 3
                    Advance idxEnd by one step
                        idxEnd++;
                Else, (meaning the end of current word)
                    
                    Now there are two conditions:
                        1 idxEnd >= sNew.length(), (meaning the idxEnd is outside the right edge current word)
                        2 idxEnd < sNew.length() and sNew.charAt(idxEnd) == ' ', (meaning idxEnd is pointing at the ' ' character)
                    Since both above two conditions should do reverse operation in the following, so do the reverse operation first
                    
                    STEP 4
                    Reverse the range of sNew from idxStart to idxEnd - 1, which is the word range
                        reverseRange(sNew, idxStart, idxEnd - 1);
                    STEP 5
                    If idxEnd >= sNew.length()
                        STEP 6
                        Break the loop
                    
                    Now idxEnd < sNew.length() and sNew.charAt(idxEnd) == ' ' in the following
                    
                    STEP 7
                    Advance idxEnd by one step
                        idxEnd++;
                    STEP 8
                    Move idxStart to idxEnd
                        idxStart = idxEnd;
            Else, (meaning sNew.charAt(idxStart) == ' ', in this version of code, this part won't be executed, since each time idxEnd met with ' ', the idxStart will finally jump to next one that not equal to ' ')
                STEP 9
                idxStart++;
        STEP 10
        Return sNew as String type
            return sNew.toString();
    TIME:
        O(n)
    SPACE:
        O(1)
*/
class ReverseWordsInStringV1 {
    
    public static String getReverseWords(String s) {
        // STEP 1
        int idxEnd = 0;
        int idxStart = 0;
        StringBuilder sNew = new StringBuilder(s);
        // STEP 2
        while (idxStart < sNew.length()) {
            if (sNew.charAt(idxStart) != ' ') {
                if (idxEnd < sNew.length() && sNew.charAt(idxEnd) != ' ') {
                    // STEP 3
                    idxEnd++;
                } else {
                    // STEP 4
                    reverseRange(sNew, idxStart, idxEnd - 1);
                    // STEP 5
                    if (idxEnd >= sNew.length()) {
                        // STEP 6
                        break;
                    }
                    // STEP 7
                    idxEnd++;
                    // STEP 8
                    idxStart = idxEnd;
                }
            } else {
                // STEP 9
                idxStart++;
            }
        }
        // STEP 10
        return sNew.toString();
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
        String s = "Let's take LeetCode contest";
        System.out.println("Before: " + s);
        String result = getReverseWords(s);
        System.out.println("After: " + result);
    }
}
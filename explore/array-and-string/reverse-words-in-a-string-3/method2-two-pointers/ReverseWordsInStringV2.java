import java.util.ArrayList;
import java.util.List;

/*
    NOTE:
        Compared with V1, this V2 version is nest while loop within outside while loop instead of using if statement
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
            If sNew.charAt(idxStart) != ' ', (meaning the beginning of new word)
                STEP 3
                Set idxEnd to idxStart for iterating current word
                    idxEnd = idxStart
                STEP 4
                Loop while idxEnd < sNew.length() and sNew.charAt(idxEnd) != ' ', (meaning the iteration process is not stopped)
                    STEP 5
                    Advance idxEnd by one step
                        idxEnd++;
                    
                Now there are two conditions:
                        1 idxEnd >= sNew.length(), (meaning the idxEnd is outside the right edge current word)
                        2 idxEnd < sNew.length() and sNew.charAt(idxEnd) == ' ', (meaning idxEnd is pointing at the ' ' character)
                    Since both above two conditions should do reverse operation in the following, so do the reverse operation first)
                    
                STEP 6
                Reverse range of sNew from idxStart to idxEnd - 1
                    reverseRange(sNew, idxStart, idxEnd - 1);
                STEP 7
                If idxEnd >= sNew.length(),
                    STEP 8
                    Break the loop
                
                Now idxEnd < sNew.length() and sNew.charAt(idxEnd) == ' ' in the following
                
                STEP 9
                Advance idxEnd by one step
                    idxEnd++;
                STEP 10
                Set idxStart to idxEnd
                    idxStart = idxEnd;
            Else, (meaning sNew.charAt(idxStart) == ' ', in this version of code, this part won't be executed, since each time idxEnd met with ' ', the idxStart will finally jump to next one that not equal to ' ')
                STEP 11
                idxStart++;
        STEP 12
        Return sNew as String type
            return sNew.toString();
    TIME:
        O(n)
    SPACE:
        O(1)
*/
class ReverseWordsInStringV2 {
    
    public static String getReverseWords(String s) {
        // STEP 1
        int idxEnd = 0;
        int idxStart = 0;
        StringBuilder sNew = new StringBuilder(s);
        // STEP 2
        while (idxStart < sNew.length()) {
            if (sNew.charAt(idxStart) != ' ') {
                // STEP 3
                idxEnd = idxStart;
                // STEP 4
                while (idxEnd < sNew.length() && sNew.charAt(idxEnd) != ' ') {
                    // STEP 5
                    idxEnd++;
                }
                // STEP 6
                reverseRange(sNew, idxStart, idxEnd - 1);
                // STEP 7
                if (idxEnd >= sNew.length()) {
                    // STEP 8
                    break;
                }
                // STEP 9
                idxEnd++;
                // STEP 10
                idxStart = idxEnd;
            } else {
                // STEP 11
                idxStart++;
            }
        }
        // STEP 12
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
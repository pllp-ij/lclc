import java.util.ArrayList;
import java.util.List;

/*
    NOTE:
        Compared with V1 and V2, this V3 version use for loop instead of using while loop
    VARS:
        idxEnd(int): indicate the end of word
        idxStart(int): indicate the start of word
        sNew(StringBuilder): a mutale string of initial one
    DESCRIPTION:
        STEP 1
        Initialize idxStart to 0
        Initialize sNew to StringBuilder from initial string
        STEP 2
        Iterate idxEnd from 0 to sNew.length(), (the reason to include the sNew.length() is to unify the right point of reverse range)
            STEP 3
            If idxEnd == sNew.length - 1 or sNew.charAt(idxEnd) == ' ', (meaning the end of current iterated word but not the end of sNew)
                STEP 4
                Reverse sNew from idxStart to idxEnd - 1
                    reverseRange(sNew, idxStart, idxEnd - 1)
                STEP 5
                Move idxStart to idxEnd + 1 for next iteration
                    idxStart = idxEnd + 1
        STEP 6
        Return sNew as String type
            return sNew.toString();
    TIME:
        O(n)
    SPACE:
        O(1)
*/
class ReverseWordsInStringV3 {
    
    public static String getReverseWords(String s) {
        // STEP 1
        int idxStart = 0;
        StringBuilder sNew = new StringBuilder(s);
        // STEP 2
        for (int idxEnd = 0; idxEnd <= sNew.length(); idxEnd++) {
            // STEP 3
            if (idxEnd == sNew.length() || sNew.charAt(idxEnd) == ' ') {
                // STEP 4
                reverseRange(sNew, idxStart, idxEnd - 1);
                // STEP 5
                idxStart = idxEnd + 1;
            }
        }
        // STEP 6
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
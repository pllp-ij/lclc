import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/*
    VARS:
        result(List<String>): the string list
        splittedArr(String[]): the array store the splitted string
    DESCRIPTION:
        STEP 1
        Initialize result to ArrayList
        STEP 2
        Split the string by space sperator by calling method
            String[] splittedArr = s.split("\\.");
        STEP 3
        Iterate each string in splitted string array
            STEP 4
            If current string is not empty,
                STEP 5
                Add current string to result
        STEP 6
        Reverse result by calling method
            Collections.reverse(result);
        STEP 7
        Join/concate the reversed collections with space to generate final string and return it
            return String.join(".", result);
    TIME:
        O(n)
    SPACE:
        O(n)
*/

class ReverseWordsInStringV1 {
    
    public static String getReverseWords(String s) {
        // STEP 1
        List<String> result = new ArrayList<>();
        // STEP 2
        String[] splittedArr = s.split("\\.");
        // STEP 3
        for (String word: splittedArr) {
            // STEP 4
            if (!word.isEmpty()) {
                // STEP 5
                result.add(word);
            }
        }
        // STEP 6
        Collections.reverse(result);
        // STEP 7
        return String.join(".", result);
    }
    
    public static void main(String[] args) {
        String s = "..the.sky...is.light_blue...";
        System.out.println("Before: " + s);
        String result = getReverseWords(s);
        System.out.println("After: " + result);
    }
}
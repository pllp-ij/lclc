import java.util.ArrayList;
import java.util.List;

/*
    VARS:
        result(List<String>): store each reversed string as final result
        splittedArr(String[]): store the result of splitting of initial string
    DESCRIPTION:
        STEP 1
        Initialize result to ArrayList
        STEP 2
        Split initial string
            String splittedArr = s.splitted(" ")
        STEP 3
        Iterate each string in splittedArr
            STEP 4
            Reverse current string and add it to result
        STEP 5
        Join result with sperator " "
            String.join(" ", result);
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
        String[] splittedArr = s.split(" ");
        // STEP 3
        for (String str: splittedArr) {
            // STEP 4
            result.add(new StringBuilder(str).reverse().toString());
        }
        // STEP 5
        return String.join(" ", result);
    }
    
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println("Before: " + s);
        String result = getReverseWords(s);
        System.out.println("After: " + result);
    }
}
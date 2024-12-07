import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/*
    VARS:
        result(List<Integer>): the final return list
        curLineArr(int[]): the cache 1d array with the size of pascalNum
    DESCRIPTION:
        STEP 1
        Initialize result to ArrayList
        Initialize curLineArr with the size of (pascalNum + 1)
        STEP 2
        Add first one into curLineArr
            curLineArr[0] = 1;
        STEP 3
        Iterate each row with index i from 1 to pascalNum
            STEP 4
            Iterate each column with index j from idxRow to 0
                If j == 0, (meaning the first element, the top-left corner of current index is outside the left edge of above row)
                    STEP 5
                    curLineArr[j] = 0 + curLineArr[j];
                Else, (meaning the following elements from 1 to pascalNum)
                    STEP 6
                    curLineArr[j] = curLineArr[j - 1] + curLineArr[j];
        STEP 7
        Convert array to List<Integer>
            List<Integer> result = Arrays.asList(curLineArr);
        STEP 8
        Return result
    TIME:
        O(2 + 3 + .. + n) ~ O(n^2)
    SPACE:
        O(n), only 1d array is used
*/

class PascalTriangle2V1 {
    
    public static List<Integer> getRow(int pascalNum) {
        // STEP 1
        List<Integer> result = new ArrayList<>();
        int[] curLineArr = new int[pascalNum + 1];
        // STEP 2
        curLineArr[0] = 1;
        // STEP 3
        for (int i = 1; i <= pascalNum; i++) {
            // STEP 4
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    // STEP 5
                    curLineArr[j] = 0 + curLineArr[j];
                } else {
                    // STEP 6
                    curLineArr[j] = curLineArr[j - 1] + curLineArr[j];
                }
            }
        }
        // STEP 7
        for (int i = 0; i <= pascalNum; i++) {
            result.add(curLineArr[i]);
        }
        // STEP 8
        return result;
    }
    
    public static void main(String[] args) {
        int pascalNum = 4;
        List<Integer> result = getRow(pascalNum);
        System.out.println(pascalNum + " pascal row: " + result.toString());
    }
}
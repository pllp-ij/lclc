import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
    VARS:
        curLineArr(int[]): an array to store each line of pascal triangle
        result(List<List<Integer>>): final return result
    DESCRIPTION:
        STEP 1
        Initialize curLineArr with size of (pascalNum + 1)
        STEP 2
        Add 1 to the first position of curLineArr
            curLineArr[0] = 1;
        STEP 3
        Iterate row index i from 1 to pascalNum
            STEP 4
            Extract all non-zero elements in last above line store in curLineArr at this moment to ArrayList
                List<Integer> lastLineList = convertNonZerosToList(curLineArr);
            STEP 5
            Add lastLineList to final result
                result.add(lastLineList);
            STEP 6
            Iterate col index j from i to 0, (if iterate from 0 to i, the recent updated result will influence following calculation)
                If j == 0, (meaning current position is the first element in current line, and the top left corner is outside the left edge of last above line)
                    STEP 7
                    Update curLineArr[j] with the sum of 0 and curLineArr[j]
                        curLineArr[j] = 0 + curLineArr[j];
                Else if j == i, (meaning current position is the last element in current line, and the top above it is outside the right edge of last above line)
                    STEP 8
                    Update curLineArr[j] with the sum of curLineArr[j - 1] and 0
                        curLineArr[j] = curLineArr[j - 1] + 0;
                Else, (meaning the top-left and top position of current position both within the valid range)
                    STEP 9
                    Update curLineArr[j] with the sum of curLineArr[j - 1] and curLineArr[j]
                        curLineArr[j] = curLineArr[j - 1] + curLineArr[j]
        STEP 10
        Return result
    TIME:
        O(n(n + 1) / 2) ~ O(n^2), the sum of 1 + 2 + 3 + .. + n
    SPACE:
        O(n)
*/


class PascalTriangleV1 {
   
    public static List<List<Integer>> getPascalTriangle(int pascalNum) {
        // STEP 1
        int[] curLineArr = new int[pascalNum + 1];
        List<List<Integer>> result = new ArrayList<>();
        // STEP 2
        curLineArr[0] = 1;
        // STEP 3
        for (int i = 1; i < pascalNum + 1; i++) {
            // STEP 4
            List<Integer> lastLineList = convertNonZerosToList(curLineArr);
            // STEP 5
            result.add(lastLineList);
            // STEP 6
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    // STEP 7
                    curLineArr[j] = 0 + curLineArr[j];
                } else if (j == i) {
                    // STEP 8
                    curLineArr[j] = curLineArr[j - 1] + 0;
                } else {
                    // STEP 9
                    curLineArr[j] = curLineArr[j - 1] + curLineArr[j];
                }
            }
        }
        // STEP 10
        return result;
    }
    
    public static List<Integer> convertNonZerosToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                list.add(arr[i]);
            }
        }
        return list;
    }
   
    public static void main(String[] args) {
        int pascalNum = 5;
        // int pascalNum = 1;
        System.out.println("pascal number: " + pascalNum);
        List<List<Integer>> pascalTriangle = getPascalTriangle(pascalNum);
        System.out.println("pascal triangle: " + Arrays.deepToString(pascalTriangle.toArray()));
    }
}
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
    VARS:
        lastLineArr(int[]): an array type representing the last line of current iterated line
        curLineArr(int[]): an array type representing the current iterated line
        result(List<List<Integer>>): 2d list for final return
    DESCRIPTION:
        STEP 1
        Initialize lastLineArr, curLineArr with the size of (pascalNum + 1)
        STEP 2
        Add the first element with 1 to lastLineArr
            lastLineArr[0] = 1;
        STEP 3
        Iterate line index i from 1 to pascalNum - 1
            STEP 4
            Extrac all non-zero elements in lastLineArr to a new ArrayList
                List<Integer> lastLineList = convertNonZerosToList(lastLineArr);
            STEP 5
            Add curLineList to final result
                result.add(lastLineList);
            STEP 6
            Iterate col index j from 0 to i
                If j == 0, meaning the first element in curLineArr, the top left corner of current position in curLineArr is outside the left edge of lastLineArr
                    STEP 7
                    Update curLineArr[j] with the sum of 0 and lastLineArr[j]
                        curLineArr[j] = 0 + lastLineArr[j]
                Else if j == i, (meaning the last element in curLineArr, the top position of current position in curLineArr is outside the right edge of lastLineArr)
                    STEP 8
                    Update curLineArr[j] with the sum of curLineArr[j - 1] and 0
                Else, (meaning the positions where its top and top-left corner in lastLineArr is within range)
                    STEP 9
                    Update curLineArr[j] with the sum of lastLineArr[j - 1] and lastLineArr[j]
            STEP 10
            Copy each non-zero elements in curLineArr back to lastLineArr
                copyBackToLastLineArr(curLineArr, lastLineArr);
        STEP 11
        Return result
    TIME:
        O(n(n + 1) / 2) ~ O(n^2), the sum of 1 + 2 + 3 + .. + n
    SPACE:
        O(2n) ~ O(n), two extra linear arrays are used
*/


class PascalTriangleV2 {
   
    public static List<List<Integer>> getPascalTriangle(int pascalNum) {
        // STEP 1
        int[] lastLineArr = new int[pascalNum + 1];
        int[] curLineArr = new int[pascalNum + 1];
        List<List<Integer>> result = new ArrayList<>();
        // STEP 2
        lastLineArr[0] = 1;
        // STEP 3
        for (int i = 1; i < pascalNum + 1; i++) {
            // STEP 4
            List<Integer> lastLineList = convertNonZerosToList(lastLineArr);
            // STEP 5
            result.add(lastLineList);
            // STEP 6
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    // STEP 7
                    curLineArr[j] = 0 + lastLineArr[j];
                } else if (j == i) {
                    // STEP 8
                    curLineArr[j] = lastLineArr[j - 1] + 0;
                } else {
                    // STEP 9
                    curLineArr[j] = lastLineArr[j - 1] + lastLineArr[j];
                }
            }
            // STEP 10
            copyBackToLastLineArr(curLineArr, lastLineArr);
        }
        // STEP 11
        return result;
    }
    
    public static List<Integer> convertNonZerosToList(int[] arr) {
        List<Integer> curLineList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                break;
            }
            curLineList.add(arr[i]);
        }
        return curLineList;
    }
    
    public static void copyBackToLastLineArr(int[] curLineArr, int[] lastLineArr) {
        for (int i = 0; i < curLineArr.length; i++) {
            if (curLineArr[i] != 0) {
                lastLineArr[i] = curLineArr[i];
            }
        }
    }
   
    public static void main(String[] args) {
        int pascalNum = 5;
        // int pascalNum = 1;
        System.out.println("pascal number: " + pascalNum);
        List<List<Integer>> pascalTriangle = getPascalTriangle(pascalNum);
        System.out.println("pascal triangle: " + Arrays.deepToString(pascalTriangle.toArray()));
    }
}
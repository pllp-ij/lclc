import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
    VARS:
        lastLineList(List<Integer>): represent the last line of current iterated line
        curLineList(List<Integer>): represent the current iterated line
        result(List<List<Integer>>): the final return result of 2d ArrayList
    DESCRIPTION:
        STEP 1
        Initialize lastLineList to ArrayList<Integer> and add 1 to it
            List<Integer> lastLineList = new ArrayList<>(Arrays.asList(1));
        STEP 2
        First add the lastLineList into final result
            result.add(lastLineList);
        STEP 3
        Iterate row index i from 1 to pascalNum - 1
            STEP 4
            Initialize curLineList to ArrayList<Integer>
            STEP 5
            Iterate col index j from 0 to i
                If j == 0, meaning the top left corner position of current index j in curLineList is outside the left edge of lastLineList
                    STEP 6
                    Add the sum of 0 and lastLineList.get(j) to curLineList
                        curLineList.add(0 + lastLineList.get(j));
                Else if j == i, (meaning the last index in curLineList to add element)
                    STEP 7
                    Add the sum of lastLineList.get(j - 1) and 0 to curLineList
                        curLineList.add(lastLineList.get(j - 1) + 0);
                Else, (meaning in the middle where each index has two top elements in lastLineList to add)
                    STEP 8
                    Add the sum of lastLineList.get(j - 1) and lastLineList.get(j) to curLineList
                        curLineList.add(lastLineList.get(j - 1) + lastLineList.get(j));
            STEP 9
            Add curLineList to final result
                result.add(curLineList);
            STEP 10
            Update lastLineList to curLineList for next iteration
                lastLineList = curLineList;
        STEP 11
        Return result
    TIME:
        O(n(n + 1) / 2) ~ O(n^2), the sum of 1 + 2 + 3 + .. + n
    SPACE:
        O(n(n + 1) / 2) ~ O(n^2), the sum of 1 + 2 + 3 + .. + n
    TO BE OPTIMIZED:
        Only use one single linear array
*/


class PascalTriangleV1 {
   
    public static List<List<Integer>> getPascalTriangle(int pascalNum) {
        // STEP 1
        List<Integer> lastLineList = new ArrayList<>(Arrays.asList(1));
        List<List<Integer>> result = new ArrayList<>();
        // STEP 2
        result.add(lastLineList);
        // STEP 3
        for (int i = 1; i < pascalNum; i++) {
            // STEP 4
            List<Integer> curLineList = new ArrayList<>();
            // STEP 5
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    // STEP 6
                    curLineList.add(0 + lastLineList.get(j));
                } else if (j == i) {
                    // STEP 7
                    curLineList.add(lastLineList.get(j - 1) + 0);
                } else {
                    // STEP 8
                    curLineList.add(lastLineList.get(j - 1) + lastLineList.get(j));
                }
            }
            // STEP 9
            result.add(curLineList);
            // STEP 10
            lastLineList = curLineList;
        }
        // STEP 11
        return result;
    }
   
    public static void main(String[] args) {
        int pascalNum = 5;
        // int pascalNum = 1;
        System.out.println("pascal number: " + pascalNum);
        List<List<Integer>> pascalTriangle = getPascalTriangle(pascalNum);
        System.out.println("pascal triangle: " + Arrays.deepToString(pascalTriangle.toArray()));
    }
}
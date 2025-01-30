import java.util.Arrays;
import java.util.Comparator;

/*
    VARS:
        trimmedStrAndIdxStr(String[][]): a 2d array used to store trimmed substring of nums and its index in nums
        k(int): the kth smallest of trimmed substring after sorted
        trimLen(int): the length used to trim each string of nums from right end to left
        result(int[]): for final return result
    DESCRIPTION:
        STEP 1
        Initialize result to new int[queries.length]
            int[] result = new int[queries.length]
        Initialize trimmedStrAndIdxStr to new String[nums.length][2]
            String[][] trimmedStrAndIdxStr = new String[nums.length][2]
        STEP 2
        Iterate each query of queries with idxQuery
            STEP 3
            Get k and trimLen from current iterated query
                int k = queries[idxQuery][0]
                int trimLen = queries[idxQuery][1]
            STEP 4
            Iterate each row/index of nums with idx
                STEP 5
                Get trimmed substring from nums[idx] and trimLen and assign it to trimmedStrAndIdxStr
                    String trimmedStr = nums[idx].substring(nums[idx].length() - trimLen);
                STEP 6
                Get string value of idx as second element of current row/index
                    String idxStr = String.valueOf(idx)
                STEP 7
                Update current row value of trimmedStrAndIdxStr with new String[] {trimmedStr, idxStr}
                    trimmedStrAndIdxStr[idx] = new String[] {trimmedStr, idxStr};
            STEP 8
            Sort trimmedStrAndIdxStr by first element as key then the second element, here ".thenComparing" is not needed but kept to indicate the usage of this syntax
                Arrays.sort(trimmedStrAndIdxStr, Comparator.comparing((String[] a) -> a[0]).thenComparing(a -> Integer.parseInt(a[1])))
            STEP 9
            Get the second element of kth (index of k-1) row of trimmedStrAndIdxStr and convert it to int type then add assign it to result[idxQuery]
                result[idxQuery] = Integer.parseInt(trimmedStrAndIdxStr[k - 1][1]);
        STEP 10
        Return result
        
    TIME:
        O(m * nlogn), m is for the size of queries, n is the size of nums, nlogn is for the consumption on sort for each query
    SPACE:
        O(2 * n) ~ O(n), 2 * n is the size of trimmedStrAndIdxStr 2d array used
*/

class QueryKthSmallestTrimmedNumV1 {
    
    public static int[] query(String[] nums, int[][] queries) {
        // STEP 1
        int[] result = new int[queries.length];
        String[][] trimmedStrAndIdxStr = new String[nums.length][2];
        // STEP 2
        for (int idxQuery = 0; idxQuery < queries.length; idxQuery++) {
            // STEP 3
            int k = queries[idxQuery][0];
            int trimLen = queries[idxQuery][1];
            // STEP 4
            for (int idx = 0; idx < nums.length; idx++) {
                // STEP 5
                String trimmedStr = nums[idx].substring(nums[idx].length() - trimLen);
                // STEP 6
                String idxStr = String.valueOf(idx);
                // STEP 7
                trimmedStrAndIdxStr[idx] = new String[] {trimmedStr, idxStr};
            }
            // STEP 8
            Arrays.sort(trimmedStrAndIdxStr, Comparator.comparing((String[] a) -> a[0]));
            // STEP 9
            result[idxQuery] = Integer.parseInt(trimmedStrAndIdxStr[k - 1][1]);
        }
        // STEP 10
        return result;
    }
    
    public static void main(String[] args) {
        // String[] nums = {"102", "473", "251", "814"};
        // int[][] queries = {
            // {1, 1},
            // {2, 3},
            // {4, 2},
            // {1, 2}
        // };
        
        // String[] nums = {"24", "37", "96", "04"};
        // int[][] queries = {
            // {2, 1},
            // {2, 2}
        // };
        
        
        String[] nums = {"64333639502", "65953866768", "17845691654", "87148775908", "58954177897", "70439926174", "48059986638", "47548857440", "18418180516", "06364956881", "01866627626", "36824890579", "14672385151", "71207752868"};
        int[][] queries = {
            {9, 4},
            {6, 1},
            {3, 8},
            {12, 9},
            {11, 4},
            {4, 9},
            {2, 7},
            {10, 3},
            {13, 1},
            {6, 1},
            {5, 10}
        };
        
        System.out.println("initial string nums: " + Arrays.toString(nums));
        System.out.println("initial queries: " + Arrays.deepToString(queries));
        
        int[] result = query(nums, queries);
        System.out.println("final result: " + Arrays.toString(result));
    }
}
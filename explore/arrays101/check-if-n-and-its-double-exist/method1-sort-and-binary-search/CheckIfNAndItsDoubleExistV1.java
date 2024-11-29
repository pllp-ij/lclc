import java.util.Arrays;

/**
    VARS:
        idxNonNegStart(int): the start index of non negative range in sorted array, including the zero into this
        inNonNeg(boolean): if now is in non-negative range
        idxLastCanDouble(int): the last element that can be doubled without exceeding the left most or right most edge in non-neg range or neg range
        foundInPos(boolean): found the (i, j) pair in non negative range
        foundInNeg(boolean): found the (i, j) pair in negative range
    DESCRIPTION:
        STEP 1
        Sort the array using nlog(n) method
        STEP 2
        Find the start index of non negative range in sorted array by calling method
            int idxNonNegStart = getIdxNonNegStart(nums)
            
        Now split the sorted array into two parts,
            [idxNonNegStart, nums.length - 1]
            [0, idxNonNegStart - 1]
            
        STEP 3
        Initialize foundInPos to false by default
            boolean foundInPos = false;
        STEP 4
        If idxNonNegStart within the right most position of sorted array, (meaning if exceeding the right most position, the command within if condition will not execute)
            STEP 5
            Check existence in array range from idxNonNegStart to (nums.length - 1)
            foundInPos = checkExistInRange(nums, idxNonNegStart, nums.length - 1, true);
        STEP 6
        Check if condition exist in array range from (idxNonNegStart - 1) to 0 in reverse direction
            boolean foundInNeg = checkExistInRange(nums, idxNonNegStart - 1, 0, false);
        STEP 7
        Return any one exist of foundInPos and foundInPos
            Return foundInPos || foundInPos
            
            
        FOR <checkExistInRange> FUNCTION
        STEP 1
        Initialize found to false, for final return
        STEP 2
        Get idxLastCanDouble by calling method and passing range flag
                idxLastCanDouble = getLastCanDoubledIndex(nums, start, end, inNonNeg);
        If current is in non-neg range,
            STEP 3
            Iterate index i from start to idxLastCanDouble,
                STEP 4
                Binary search (2 * nums[i]) in range from (i + 1) to end, and assign result to found variable
                    found = binarySearch(nums, i + 1, end, 2 * nums[i]);
                STEP 5
                If found is true,
                    return true
        Else, (meaning now is in negative range)
            STEP 6
            Iterate index i from start to idxLastCanDouble
                STEP 7
                Binary search (2 * nums[i]) in range from end to (i - 1), and assign result to found variable
                    found = binarySearch(nums, end, i - 1, 2 * nums[i])
                STEP 8
                If found is true,
                    return true
        STEP 9
        Return false
        
        FOR <getLastCanDoubledIndex> FUNCTION
        STEP 1
        Initialize idxLastCanDouble to start
        If current is in non-neg range,
            STEP 2
            Loop while idxLastCanDouble <= end and nums[idxLastCanDouble] is less than or equal to half of the element in end position, and increase idxLastCanDouble by one
                while (idxLastCanDouble <= end && nums[idxLastCanDouble] <= nums[end] / 2)
                    idxLastCanDouble++;
            STEP 3
            Return idxLastCanDouble - 1, WARNING, don't use idxLastCanDouble-- !
        Now meaning current is in neg range after the above if not execute
        STEP 4
        Loop while idxLastCanDouble >= end and  nums[idxLastCanDouble] is more than or equal to half of the element in end position,
            while (idxLastCanDouble >= end && nums[idxLastCanDouble] >= nums[end] / 2)
                idxLastCanDouble--;
        STEP 5
        Return idxLastCanDouble + 1
    TIME: 
        O(n * log(n)), log(n) is from binary, and n is the iteration of range of numbers that can be doubled
    SPACE:
        O(1), no extra space used
    TO BE OPTIMIZED:
        Avoid sorting first
*/

class CheckIfNAndItsDoubleExistV1 {
    
    public static boolean checkExist(int[] nums) {
        // STEP 1
        Arrays.sort(nums);
        // System.out.println("sorted arr: " + Arrays.toString(nums));
        // STEP 2
        int idxNonNegStart = getIdxNonNegStart(nums);
        // System.out.println("idxNonNegStart: " + idxNonNegStart);
        // STEP 3
        boolean foundInPos = false;
        // STEP 4
        if (idxNonNegStart < nums.length) {
            // STEP 5
            foundInPos = checkExistInRange(nums, idxNonNegStart, nums.length - 1, true);
        }
        // System.out.println("foundInPos: " + foundInPos);
        // STEP 6
        boolean foundInNeg = checkExistInRange(nums, idxNonNegStart - 1, 0, false);
        // System.out.println("foundInNeg: " + foundInNeg);
        // STEP 7
        return foundInPos || foundInNeg;
    }
    
    public static int getIdxNonNegStart(int[] nums) {
        int idxNonNegStart = 0;
        while (idxNonNegStart < nums.length && nums[idxNonNegStart] < 0) {
            idxNonNegStart++;
        }
        return idxNonNegStart;
    }
    
    public static boolean checkExistInRange(int[] nums, int start, int end, boolean inNonNeg) {
        // STEP 1
        boolean found = false;
        // STEP 2
        int idxLastCanDouble = getLastCanDoubledIndex(nums, start, end, inNonNeg);
        if (inNonNeg) {
            // System.out.println("idxLastCanDouble in pos: " + idxLastCanDouble);
            // STEP 3
            for (int i = start; i <= idxLastCanDouble; i++) {
                // STEP 4
                found = binarySearch(nums, i + 1, end, 2 * nums[i]);
                if (found) {
                    // STEP 5
                    return true;
                }
            }
        } else {
            // System.out.println("idxLastCanDouble in neg: " + idxLastCanDouble);
            // STEP 6
            for (int i = start; i >= idxLastCanDouble; i--) {
                // STEP 7
                found = binarySearch(nums, end, i - 1, 2 * nums[i]);
                if (found) {
                    // STEP 8
                    return true;
                }
            }
        }
        // STEP 9
        return false;
    }
    
    public static boolean binarySearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
    
    public static int getLastCanDoubledIndex(int[] nums, int start, int end, boolean inNonNeg) {
        // STEP 1
        int idxLastCanDouble = start;
        if (inNonNeg) {
            // STEP 2
            while (idxLastCanDouble <= end && nums[idxLastCanDouble] <= nums[end] / 2) {
                idxLastCanDouble++;
            }
            // STEP 3
            return idxLastCanDouble - 1;
        }
        // STEP 4
        while (idxLastCanDouble >= end && nums[idxLastCanDouble] >= nums[end] / 2) {
            idxLastCanDouble--;
        }
        // STEP 5
        return idxLastCanDouble + 1;
    }
    
    public static void main(String[] args) {
        int[] nums = {10, 2, 5, 3};
        // int[] nums = {3, 1, 7, 11};
        // int[] nums = {0, 0};
        // int[] nums = {-10, 12, -20, -8, 15};
        // int[] nums = {-16, -9};
        System.out.println("nums: " + Arrays.toString(nums));
        boolean exist = checkExist(nums);
        if (exist) {
            System.out.println("i and j exist");
        } else {
            System.out.println("Not exist");
        }
    }
}
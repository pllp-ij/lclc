import java.util.Arrays;

/**
    VARS:
        newArr(int[]): new array with the same size of initial array to store the sorted array
        curNum(int): the value of current iterated number in initial array
    
    DESCRIPTION:
        STEP 1
        Copy the initial array to newArr and sort it
        STEP 2
        Iterate each number with index i in initial array
            STEP 3
            If i == nums.length - 1, (meaning the last element of initial array)
                STEP 4
                Assign -1 to nums[i]
                    nums[i] = -1;
                STEP 5
                Break the loop
                
            Following is for all the elements except the last element in initial array
                
            STEP 6
            Iterate each number with index j in newArr from right most to left
                If newArr[j] == nums[i], (meaning find first valid elements in newArr which equals to current iterated element in initial array)
                    STEP 7
                    Set newArr[j] to -1
                    STEP 8
                    Break the loop for newArr
            STEP 9
            Iterate each number with index j in newArr from right most to left
                If newArr[j] != -1, (meaning first valid larget element in newArr, which should be copied to current iterated index in initial array)
                    STEP 10
                    Assign newArr[j] to nums[i]
                    STEP 11
                    Break the loop
    
    TIME:
        O(nlog(n) + n^2) ~ O(n^2), nlog(n) for sorting, and n^2 for iterating both initial and new array
    SPACE:
        O(n)
    TO BE OPTIMIZED:
        Avoid using extra space
*/

class ReplaceElementsWithGreatestElementOnRightSideV1 {
    
    public static void replaceElements(int[] nums) {
        // STEP 1
        int[] newArr = copyArray(nums);
        Arrays.sort(newArr);
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            if (i == nums.length - 1) {
                // STEP 4
                nums[i] = -1;
                // STEP 5
                break;
            }
            // STEP 6
            for (int j = newArr.length - 1; j >= 0; j--) {
                if (newArr[j] == nums[i]) {
                    // STEP 7
                    newArr[j] = Integer.MIN_VALUE;
                    // STEP 8
                    break;
                }
            }
            // STEP 9
            for (int j = newArr.length - 1; j >= 0; j--) {
                if (newArr[j] != Integer.MIN_VALUE) {
                    // STEP 10
                    nums[i] = newArr[j];
                    // STEP 11
                    break;
                }
            }
        }
    }
    
    public static int[] copyArray(int[] nums) {
        int[] newArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArr[i] = nums[i];
        }
        return newArr;
    }
    
    public static void main(String[] args) {
        // int[] nums = {17, 18, 5, 4, 6, 1};
        int[] nums = {400};
        System.out.println("Before: " + Arrays.toString(nums));
        replaceElements(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}
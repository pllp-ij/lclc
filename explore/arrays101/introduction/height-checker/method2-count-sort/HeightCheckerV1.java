import java.util.Arrays;

/**
    VARS:
        hashArr(int[]): a hash map mapping value in nums to its appearance number
        notMatchNum(int): the number of mis-match, for final return
        curExpectedVal(int): when iterate initial array, the corresponding expected value in this iterated position if in sorted array
    DESCRIPTION:
        STEP 1
        Initialize hashArr with the size of (100 + 1), to keep mapping concise, initialize hash map array to map height in heights as index in hash map, map number of height in heights as value in hash map
        STEP 2
        Iterate each number in initial array
            STEP 3
            Update current iterated nums[i] to hashArr[nums[i]] by one
                hashArr[nums[i]] += 1
        STEP 4
        Initialize notMatchNum to 0
        Initialize curExpectedVal to 0
        STEP 5
        Iterate each number in initial array again with index i
            STEP 6
            Find first non-zero element's index of hashArr from left to right by calling method
                curExpectedValIdx = getFirstNonZeroValIdx(hashArr)
            If nums[i] != hashArr[curExpectedValIdx], (meaning in same position, two array are not match)
                STEP 7
                Increase notMatchNum by one
                    notMatchNum++;
            STEP 8
            Decrease hashArr[curExpectedValIdx] by one for next iteration's comparasion, 
            curExpectedVal also means the index of first non zero element in hashArr, so it can be included within square bracket
                hashArr[curExpectedValIdx] -= 1
        STEP 9
        Return notMatchNum
    TIME:
        O(100n)~O(n), each search in hashArr is at most 100 times, so finally it's linaer
    SPACE:
        O(n)
*/

class HeightCheckerV1 {
    
    public static int checkHeight(int[] nums) {
        // STEP 1
        int[] hashArr = new int[100 + 1];
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            hashArr[nums[i]] += 1;
        }
        // STEP 4
        int notMatchNum = 0;
        int curExpectedVal = 0;
        // STEP 5
        for (int i = 0; i < nums.length; i++) {
            // STEP 6
            curExpectedVal= getFirstNonZeroValIdx(hashArr);
            if (nums[i] != curExpectedVal) {
                // STEP 7
                notMatchNum++;
            }
            // STEP 8
            hashArr[curExpectedVal] -= 1;
        }
        // STEP 9
        return notMatchNum;
    }
    
    public static int getFirstNonZeroValIdx(int[] hashArr) {
        int firstNonZeroIdx = 0;
        while (hashArr[firstNonZeroIdx] == 0) {
            firstNonZeroIdx++;
        }
        return firstNonZeroIdx;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 1, 4, 2, 1, 3};
        // int[] nums = {5, 1, 2, 3, 4};
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("Before: " + Arrays.toString(nums));
        int count = checkHeight(nums);
        System.out.println(count + " of indices not match");
    }
}
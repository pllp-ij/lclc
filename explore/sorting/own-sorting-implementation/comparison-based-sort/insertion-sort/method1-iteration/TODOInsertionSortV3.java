import java.util.Arrays;

/*
    NOTE: 
        V3 use binary search to find the position to insert current processed element
    VARS:
        
    DESCRIPTION:
        
    TIME:
        
    SPACE:
        
*/

class InsertionSortV1 {
    
    public static void sort(int[] nums) {
        
    }
    
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}
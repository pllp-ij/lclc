import java.util.Arrays;

/**
    VARS:
        newArr(String[]): new array for reversed string
        idxNewArr(int): index for adding elements into new array
    DESCRIPTION:
        STEP 1
        Initialize newArr with the same size of initial char array
        STEP 2
        Iterate each char in initial array from end to start
            STEP 3
            Add current char into new array
            STEP 4
            Increase idxNewArr by one
                idxNewArr++;
        STEP 5
        Copy each char in newArr back to initial array
    TIME:
        O(n)
    SPACE:
        O(n)
    TO BE OPTIMIZED:
        Avoid using extra array
*/

class ReverseStringV1 {
    
    public static void reverseString(char[] str) {
        // STEP 1
        char[] newArr = new char[str.length];
        int idxNewArr = 0;
        // STEP 2
        for (int i = str.length - 1; i >= 0; i--) {
            // STEP 3
            newArr[idxNewArr] = str[i];
            // STEP 4
            idxNewArr++;
        }
        // STEP 5
        for (int i = 0; i < idxNewArr; i++) {
            str[i] = newArr[i];
        }
    }
    
    public static void main(String[] args) {
        char[] str = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("Before: " + Arrays.toString(str));
        reverseString(str);
        System.out.println("After: " + Arrays.toString(str));
    }
}
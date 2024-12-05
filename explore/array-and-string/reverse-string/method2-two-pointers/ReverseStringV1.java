import java.util.Arrays;

/**
    VARS:
        idxLeft(int): the left pointer to iterate from left to right
        idxRight(int): the right pointer to iterate from right to left
    DESCRIPTION:
        STEP 1
        Initialize idxLeft to 0
        Initialize idxRight to str.length - 1
        STEP 2
        Loop while idxLeft < idxRight, (meaning there is need to swap the two)
            STEP 3
            Swap the two elements in array by calling method
                swapTwo(str, idxLeft, idxRight);
            STEP 4
            Update idxLeft and idxRight
                idxLeft++;
                idxRight--;
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class ReverseStringV1 {
    
    public static void reverseString(char[] chars) {
        // STEP 1
        int idxLeft = 0;
        int idxRight = chars.length - 1;
        // STEP 2
        while (idxLeft < idxRight) {
            // STEP 3
            swapTwo(chars, idxLeft, idxRight);
            // STEP 4
            idxLeft++;
            idxRight--;
        }
    }
    
    public static void swapTwo(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    public static void main(String[] args) {
        char[] str = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("Before: " + Arrays.toString(str));
        reverseString(str);
        System.out.println("After: " + Arrays.toString(str));
    }
}
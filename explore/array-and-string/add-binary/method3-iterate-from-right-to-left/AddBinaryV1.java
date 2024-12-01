/**
    VARS:
        idxA(int): iterate index for string a from right to left
        idxB(int): iterate index for string b from right to left
        carryFromLast(int): the carry over from last calculation
        result(StringBuilder): a StringBuilder type to store final result
    DESCRIPTION:
        STEP 1
        Initialize idxA to a.length - 1
        Initialize idxB to b.length - 1
        Initialize carryFromLast to 0
        Initialize result to StringBuilder()
        STEP 2
        Loop while idxA >= 0 or idxB >= 0 or carryFromLast > 0, (meaning the share range of a and b, the exclude range in one of a or b, the final carry over after last addtion)
            If idxA >= 0, (meaning idxA is in valid range of string a)
                STEP 3
                Get char at idxA and convert it to integer
                    int curInt = a.charAt(idxA) - '0';
                STEP 4
                Add curInt to carryFromLast
                    carryFromLast += curInt;
                STEP 5
                Decrease idxA by one
                    idxA--;
            If idxB >= 0, (meaning idxB is in valid range of string b)
                STEP 6
                Get char at idxB and convert it to integer
                    int curInt = b.charAt(idxB) - '0';
                STEP 7
                Add it to carryFromLast
                    carryFromLast += curInt;
                STEP 8
                Decrease idxB by one
                    idxB--;
            STEP 9
            Get current updated int value in current position and add it to fianl result
                result.append(carryFromLast % 2);
            STEP 10
            Update carryFromLast for next iteration
                carryFromLast /= 2;
        STEP 11
        Reverse final result and return it
            result.reverse().toString();
    TIME:
        O(max(a.length, b.length) + 1) ~ O(n), depend on the longer string between a and b
    SPACE:
        O(n), extra StringBuilder used to save final result
*/

class AddBinaryV1 {
    
    public static String addBinary(String a, String b) {
        // STEP 1
        int idxA = a.length() - 1;
        int idxB = b.length() - 1;
        int carryFromLast = 0;
        StringBuilder result = new StringBuilder();
        // STEP 2
        while (idxA >= 0 || idxB >= 0 || carryFromLast > 0) {
            if (idxA >= 0) {
                // STEP 3
                int curInt = a.charAt(idxA) - '0';
                // STEP 4
                carryFromLast += curInt;
                // STEP 5
                idxA--;
            }
            if (idxB >= 0) {
                // STEP 6
                int curInt = b.charAt(idxB) - '0';
                // STEP 7
                carryFromLast += curInt;
                // STEP 8
                idxB--;
            }
            // STEP 9
            result.append(carryFromLast % 2);
            // STEP 10
            carryFromLast /= 2;
        }
        // STEP 11
        return result.reverse().toString();
    }
    
    public static void main(String[] args) {
        String[] pair = {"11", "1"};
        // String[] pair = {"101", "110"};
        String result = addBinary(pair[0], pair[1]);
        System.out.println(pair[0] + " + " + pair[1] + " = " + result);
    }
}
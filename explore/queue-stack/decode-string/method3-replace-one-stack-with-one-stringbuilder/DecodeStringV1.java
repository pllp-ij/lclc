import java.util.Stack;

/*
    VARS:
        resultSB(StringBuilder): for final returned resultSB, also for cache calculation
        curSubStrSB(StringBuilder): for collecting current substring with "[..]"
        count(int): for collecting repeatation number represented by continuous characters
        curChar(Character): for storing current iterated character
        idxS(int): index when iterate s from 0 to (s.length() - 1)
    DESCRIPTION:
        STEP 1
        Initialize resultSB to StringBuilder()
        Initialize curSubStrSB to null
        Initialize count to 0
        Initialize curChar to null
        Initialize idxS to 0
        STEP 2
        Iterate each character in s with idxS from 0 to s.length() - 1
            STEP 3
            Get current character and assign it to curChar
                curChar = s.charAt(idxS);
            STEP 4
            If curChar == ']', (meaning there is a need to process resultSB)
                STEP 5
                Create a new StringBuilder object and assign it to curSubStrSB
                    curSubStrSB = new StringBuilder();
                STEP 6
                Loop while resultSB.length() > 0 and resultSB.charAt(resultSB.length() - 1) != '[', (meaning the resultSB is not empty and its top element is not '[')
                    STEP 7
                    Insert the top element of resultSB into curSubStrSB's index 0
                        curSubStrSB.insert(0, resultSB.charAt(resultSB.length() - 1));
                    STEP 8
                    Remove top element from resultSB
                        resultSB.deleteCharAt(resultSB.length() - 1);
                
                Now there are two conditions after existing above while loop
                    1. resultSB.length() <= 0, (meaning resultSB is empty, actually this condition won't be executed, because there is at least one '[' to match current ']')
                    2. resultSB.length() > 0 and resultSB.charAt(resultSB.length() - 1) == '[', (meaning current top element of resultSB is '[', it need to be popped out too)
                    
                STEP 9
                If resultSB.charAt(resultSB.length() - 1) == '[',
                    STEP 10
                    Pop top element from resultSB again to remove '[', so all contents with "[..]" has been removed
                        resultSB.deleteCharAt(resultSB.length() - 1);
                
                Now current substring is stored in curSubStrSB
                
                STEP 11
                Reset count to 0 for storing repeatation number represented by current continuous characters
                    count = 0;
                STEP 12
                Set digitPos to 0 to iterate digit from least significant to most significant
                    int digitPos = 0;
                STEP 13
                Loop while resultSB.length() > 0,
                    STEP 14
                    Get current digit character and assign it to digitChar
                        Character digitChar = resultSB.charAt(resultSB.length() - 1);
                    STEP 15
                    Get current character and convert it to integer value then assign it to topNum
                        int digitNum = digitChar - '0';
                    STEP 16
                    If digitNum < 0 || digitNum > 9, (meaning integer value of current top char is not within valid range)
                        STEP 17
                        Break while loop
                            break;
                    
                    Now digitNum is within range [0, 9]
                    
                    STEP 18
                    Multiply digitNum with pow(10, digitPos) then reassign it to count by calling method
                        count += digitNum * pow(10, digitPos);
                    STEP 19
                    Pop top element from resultSB
                        resultSB.deleteCharAt(resultSB.length() - 1);
                    STEP 20
                    Increase digitPos by one to move to next digit
                        digitPos++;
                
                Now current substring is stored in curSubStrSB, and the number of repeatation is stored in count
                
                STPE 21
                Iterate i from 0 to count - 1, 
                    STEP 22
                    Add curSubStrSB to resultSB
                        resultSB.append(curSubStrSB)
                STEP 23
                Skip current iteration
                    continue;
                    
            Now all remaining conditions (curChar is digit, character, '['), push them into resultSB directly
            
            STEP 24
            resultSB.append(curChar);
        
        Now final resultSB is stored in resultSB
        
        STEP 25
        Return resultSB.toString();
                
        -FUNC int pow(int base, int exp),
        STEP 1
        If exp == 0, (exponential value is 0)
            STEP 2
            Return 1;
        STEP 3
        If exp == 1, (exponential value is 1)
            STEP 4
            Return base
        STEP 5
        Return base * pow(base, exp - 1);
                
    TIME:
        O(N), N is the length of s
    SPACE:
        O(N + N) ~ O(N), first N is for resultSB, second N is for curSubStrSB
*/

class DecodeStringV1 {
    
    public static String decodeStr(String s) {
        // STEP 1
        StringBuilder resultSB = new StringBuilder();
        StringBuilder curSubStrSB = null;
        int count = 0;
        Character curChar = null;
        int idxS = 0;
        // STEP 2
        for (; idxS < s.length(); idxS++) {
            // STEP 3
            curChar = s.charAt(idxS);
            // STEP 4
            if (curChar == ']') {
                // STEP 5
                curSubStrSB = new StringBuilder();
                // STEP 6
                while (resultSB.length() > 0 && resultSB.charAt(resultSB.length() - 1) != '[') {
                    // STEP 7
                    curSubStrSB.insert(0, resultSB.charAt(resultSB.length() - 1));
                    // STEP 8
                    resultSB.deleteCharAt(resultSB.length() - 1);
                }
                // STEP 9
                if (resultSB.charAt(resultSB.length() - 1) == '[') {
                    // STEP 10
                    resultSB.deleteCharAt(resultSB.length() - 1);
                }
                // STEP 11
                count = 0;
                // STEP 12
                int digitPos = 0;
                // STEP 13
                while (resultSB.length() > 0) {
                    // STEP 14
                    Character digitChar = resultSB.charAt(resultSB.length() - 1);
                    // STEP 15
                    int digitNum = digitChar - '0';
                    // STEP 16
                    if (digitNum < 0 || digitNum > 9) {
                        // STEP 17
                        break;
                    }
                    // STEP 18
                    count += digitNum * pow(10, digitPos);
                    // STEP 19
                    resultSB.deleteCharAt(resultSB.length() - 1);
                    // STEP 20
                    digitPos++;
                }
                // STEP 21
                for (int i = 0; i < count; i++) {
                    // STEP 22
                    resultSB.append(curSubStrSB);
                }
                // STEP 23
                continue;
            }
            // STEP 24
            resultSB.append(curChar);
        }
        // STEP 25
        return resultSB.toString();
    }
    
    public static int pow(int base, int exp) {
        // STEP 1
        if (exp == 0) {
            // STEP 2
            return 1;
        }
        // STEP 3
        if (exp == 1) {
            // STEP 4
            return base;
        }
        // STEP 5
        return base * pow(base, exp - 1);
    }
    
    public static void main(String[] args) {
        // String s = "3[a]2[bc]";
        
        // String s = "3[a2[c]]";
        
        // String s = "2[abc]3[cd]ef";
        
        String s = "100[apple]";
        
        String decodedStr = decodeStr(s);
        System.out.println("initial string: " + s);
        System.out.println("decoded string: " + decodedStr);
        
    }
}
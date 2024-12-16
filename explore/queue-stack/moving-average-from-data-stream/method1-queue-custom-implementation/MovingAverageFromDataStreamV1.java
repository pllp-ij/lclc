/*
    VARS:
        windowCapacity(int): the size of the window offered by question
        window(int[]): a fixed-size array to represent the window
        numsProcessed(int): a recorder store the number of nums have been processed, also means the position to place number
        sum(double): the sum of numbers of recent most k integers
        idxToInsert(int): the idx in window to insert new value, only in values: 0, 1, 2, .. k-1
    DESCRIPTION:
        STEP 1
        Initialize windowCapacity to k in constructor
        Initialize window to static array with size of k
        Intialize numsProcessed to 0
        Initialize sum to 0
        Initialize idxToInsert to 0
        STEP 2
        Calculate the position to place current value with moduling the capacity and assign it to idxToInsert
            idxToInsert = numsProcessed % windowCapacity
        
        Now idxToInsert is pointing at the position to place current number
        
        STEP 3
        Substract the window[idxToInsert] firstly from sum, then add value to sum, (assuming the initial state of window is filled with all zeros that make window full, then add new value into it, so first should move the old value from window then add new value into it)
            sum = (sum - window[idxToInsert] + value);
        STEP 4
        Assign value to window[idxToInsert] to replace old value with new value
            window[idxToInsert] = value;
        STEP 5
        Increase numsProcessed by one, (meaning one more integer has been processed)
            numsProcessed++;
        STEP 6
        Return latest moving average which is sum divided by the minimum between numsProcessed and capacity, when numsProcessed are not more than capacity, the number of integers is determined by numsProcessed, once it exceed capacity, the divider is always capacity
            return sum / Math.min(numsProcessed, capacity);
    TIME:
        O(k), before adding value, there should be a fixed-size static array created with value of k, the add operation is O(1)
    SPACE:
        O(k), a static array with size of k should be maintained
*/

class MovingAverageFromDataStreamV1 {
    public int windowCapacity;
    public int[] window;
    public int numsProcessed;    // always point to the position to be placed numbers
    public double sum;
    public int idxToInsert;
    
    public MovingAverageFromDataStreamV1(int k) {
        // STEP 1
        windowCapacity = k;
        window = new int[windowCapacity];
        numsProcessed = 0;
        sum = 0.0;
        idxToInsert = 0;
    }
    
    public double next(int value) {
        // STEP 2
        idxToInsert = numsProcessed % windowCapacity;
        // STEP 3
        sum = (sum - window[idxToInsert] + value);
        // STEP 4
        window[idxToInsert] = value;
        // STEP 5
        numsProcessed++;
        // STEP 6
        return sum / Math.min(numsProcessed, windowCapacity);
    }
    
    public static void main(String[] args) {
        MovingAverageFromDataStreamV1 movingAverage = new MovingAverageFromDataStreamV1(3);
        double avg = movingAverage.next(1);
        System.out.println("after add 1, moving average: " + avg);
        avg = movingAverage.next(10);
        System.out.println("after add 10, moving average: " + avg);
        avg = movingAverage.next(3);
        System.out.println("after add 3, moving average: " + avg);
        avg = movingAverage.next(5);
        System.out.println("after add 5, moving average: " + avg);
    }
}
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        m(int): row number of image
        n(int): col number of image
        oldColor(int): the initial color for connected region in image
        newColor(int): the color used to fill connected region in image
        directions(int[]): all next possible directions
    DESCRIPTION:
        STEP 1
        Initialize m to image.length
        Initialize n to image[0].length
        Initialize oldColor to image[sr][sc]
        Initialize newColor to color
        Initialize directions to {-1, 0, 1, 0, -1} used in each recursion
        STEP 2
        Do DFS recursively without return value
            DFS(image, m, n, sr, sc, oldColor, newColor, directions);
        
        -FUNC void DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor, int[] directions)
        STEP 1
        If isNotValid(image, m, n, i, j, oldColor, newColor), (meaning if current level of recursion is not valid, directly return)
            STEP 2
            Directly return
        STEP 3
        Fill (i, j) with newColor in image
            image[i][j] = newColor;
        STEP 4
        Iterate each directions with idxDirection from 0 to 3
            STEP 5
            Calculate (nextI, nextJ) by (i, j) and directions, idxDirection
                int nextI = i + directions[idxDirection];
                int nextJ = j + directions[idxDirection + 1];
            STPE 6
            Recursively do DFS for next steps
                DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);

        -FUNC boolean isNotValid(int[][] image, int m, int n, int i, int j, int oldColor, int newColor)
        STEP 1
        If i < 0 || i >= m ||
           j < 0 || j >= n ||
           image[i][j] == newColor ||
           image[i][j] != oldColor, (first two conditions is to check the boundary validation, third condition is for the cases the starting point pixel color is equal to newColor, the fourth color is combined with third condition that if starting point is not initially equal to newColor, then it must equal to oldColor)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
    TIME:
        O(m * n), each node in image will be visited only once
    SPACE:
        O(m * n), in worst case, there are at most (m * n) depth of calling stack
*/

public class FloodFillV1 {
    
    public static void floodFill(int[][] image, int sr, int sc, int color) {
        // STEP 1
        int m = image.length;
        int n = image[0].length;
        int[] directions = {-1, 0, 1, 0, -1};
        int oldColor = image[sr][sc];
        int newColor = color;
        // STEP 2
        DFS(image, m, n, sr, sc, oldColor, newColor, directions);
    }
    
    public static void DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor, int[] directions) {
        // STEP 1
        if (isNotValid(image, m, n, i, j, oldColor, newColor)) {
            // STEP 2
            return;
        }
        // STEP 3
        image[i][j] = newColor;
        // STEP 4
        for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
            // STEP 5
            int nextI = i + directions[idxDirection];
            int nextJ = j + directions[idxDirection + 1];
            // STEP 6
            DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);
        }
    }
    
    public static boolean isNotValid(int[][] image, int m, int n, int i, int j, int oldColor, int newColor) {
        // STEP 1
        if (i < 0 || i >= m || 
            j < 0 || j >= n ||
            image[i][j] == newColor ||
            image[i][j] != oldColor) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int[] node = {1, 1, 2};
        
        // int[][] image = {
            // {0, 0, 0},
            // {0, 0, 0}
        // };
        // int[] node = {0, 0, 0};
        
        System.out.println("initial image: \n" + Arrays.deepToString(image));
        floodFill(image, node[0], node[1], node[2]);
        System.out.println("after fill from position (" + node[0] + ", " + node[1] + ") with color=" + node[2] + ", result is: \n" + Arrays.deepToString(image));
    }
}
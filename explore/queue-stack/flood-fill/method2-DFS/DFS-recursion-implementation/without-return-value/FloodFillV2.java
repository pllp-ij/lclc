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
        Fill (i, j) with newColor in image
            image[i][j] = newColor;
        STEP 2
        Iterate each directions with idxDirection from 0 to 3
            STEP 3
            Calculate (nextI, nextJ) by (i, j) and directions, idxDirection
                int nextI = i + directions[idxDirection];
                int nextJ = j + directions[idxDirection + 1];
            STEP 4
            If isValid(image, m, n, nextI, nextJ, oldColor, newColor, directions), (meaning next position (nextI, nextJ) is valid)
                STPE 5
                Recursively do DFS for next steps
                    DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);

        -FUNC boolean isValid(int[][] image, int m, int n, int i, int j, int oldColor, int newColor)
        STEP 1
        If i >= 0 && i < m &&
           j >= 0 && j < n &&
           image[i][j] != newColor &&
           image[i][j] == oldColor, (first two conditions to check if position is within valid range, and third condition is useless when combined with fourth condition, the reason keep it is for the unify format with V1
            STEP 2
            Return true;
        STEP 3
        Return false;
        
    TIME:
        O(m * n), each node in image will be visited only once
    SPACE:
        O(m * n), in worst case, there are at most (m * n) depth of calling stack
*/

public class FloodFillV2 {
    
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
        image[i][j] = newColor;
        // STEP 2
        for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
            // STEP 3
            int nextI = i + directions[idxDirection];
            int nextJ = j + directions[idxDirection + 1];
            // STEP 4
            if (isValid(image, m, n, nextI, nextJ, oldColor, newColor)) {
                // STEP 5
                DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);
            }
        }
    }
    
    public static boolean isValid(int[][] image, int m, int n, int i, int j, int oldColor, int newColor) {
        // STEP 1
        if (i >= 0 && i < m &&
            j >= 0 && j < n &&
            image[i][j] != newColor &&
            image[i][j] == oldColor) {
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
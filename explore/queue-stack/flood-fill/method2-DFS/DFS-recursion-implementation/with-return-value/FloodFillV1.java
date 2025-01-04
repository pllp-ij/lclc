import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    NOTE:
        Compared to "without-return-value", this "with-return-value" version of code will process each step of filling operation within each recursion, and return a new state of image at the end of each recursion,
        dive deep into the two [Note] in the following, compare the differences and relationships between them, 
            1. [Note-B] is a subset of [Note-A]
            2. [Note-A] = one step of operation + [Note-B]
            3. [Note-B] is the next step of [Note-A] in the direction where [Note-A] become smaller and smaller
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
        Initialize directions to {-1, 0, 1, 0, -1}
        STEP 2
        Do DFS with return value
            imageFilled = DFS(image, m, n, sr, sc, oldColor, newColor, directions);
        STEP 3
        Return imageFilled
        
        [Note-A], the following recursive method
            -FUNC int[][] DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor, int[] directions)
        is translated to:
            fill image from starting point (i, j) with newColor, after all nodes filled, return final state of image
            
        -FUNC int[][] DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor, int[] directions)
        STEP 1
        If isNotValid(image, m, n, i, j, oldColor, newColor), (meaning current state is not valid)
            STEP 2
            Directly return image to pass the current state of image to last level of recursion
        STEP 3
        Fill image in position (i, j) with newColor
            image[i][j] = newColor;
        STEP 4
        Iterate each next direction with idxDirection from 0 to 3
            STEP 5
            Calculate (nextI, nextJ) from (i, j)
                int nextI = i + directions[idxDirection];
                int nextJ = j + directions[idxDirection + 1];
            STEP 6
            Do DFS recursively
                image = DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);
            
                [Note-B], the above STEP 6 recursive method
                return DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);
            can be translated to:
                fill image from starting point (nextI, nextJ) with newColor, after all nodes filled, return final state of image
        
        Now all nodes are filled with five steps:
            1. fill starting point (i, j)
                2. fill all nodes using DFS from point (i - 1, j), which is the top point of (i, j)
                3. fill all nodes using DFS from point (i, j + 1), which is the right point of (i, j + 1)
                4. fill all nodes using DFS from point (i + 1, j), which is the down point of (i, j)
                5. fill all nodes using DFS from point (i, j - 1), which is the left point of (i, j)
        all nodes already filled in the before steps will be skipped/ignored, and
        the solution of whole problem is splitted into above five parts, so combined them together will get the final filled image state, then return that final state of image
        
        STEP 7
        Return image

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
    
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // STEP 1
        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];
        int newColor = color;
        int[] directions = {-1, 0, 1, 0, -1};
        // STEP 2
        int[][] imageFilled = DFS(image, m, n, sr, sc, oldColor, newColor, directions);
        // STEP 3
        return imageFilled;
    }
    
    public static int[][] DFS(int[][] image, int m, int n, int i, int j, int oldColor, int newColor, int[] directions) {
        // STEP 1
        if (isNotValid(image, m, n, i, j, oldColor, newColor)) {
            // STEP 2
            return image;
        }
        // STEP 3
        image[i][j] = newColor;
        // STEP 4
        for (int idxDirection = 0; idxDirection < 4; idxDirection++) {
            // STEP 5
            int nextI = i + directions[idxDirection];
            int nextJ = j + directions[idxDirection + 1];
            // STEP 6
            image = DFS(image, m, n, nextI, nextJ, oldColor, newColor, directions);
        }
        // STEP 7
        return image;
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
        image = floodFill(image, node[0], node[1], node[2]);
        System.out.println("after fill from position (" + node[0] + ", " + node[1] + ") with color=" + node[2] + ", result is: \n" + Arrays.deepToString(image));
    }
}
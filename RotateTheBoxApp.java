import java.util.Arrays;

public class RotateTheBoxApp {

    public static char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        char[][] result = new char[m][n];

        // Process each row of the box
        for (int i = 0; i < n; i++) {
            int j = m - 1; // Pointer to traverse row from right to left
            int idx = m - 1; // Pointer to place the stones after they "fall"
            
            while (j >= 0) {
                if (box[i][j] == '#') {
                    // Move the stone
                    result[j][n - i - 1] = '.';
                    result[idx--][n - i - 1] = '#';
                } else {
                    // Copy the obstacle or empty cell
                    char c = box[i][j];
                    result[j][n - i - 1] = c;
                    if (c == '*') {
                        idx = j - 1; // Reset the stone placement index if obstacle is found
                    }
                }
                j--;
            }
        }
        return result;
    }

    public static void printBox(char[][] box) {
        for (char[] row : box) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        // Sample box setup
        char[][] box = {
            {'#', '#', '*', '.', '#'},
            {'#', '#', '#', '*', '.'},
            {'.', '#', '.', '.', '#'}
        };

        System.out.println("Original Box:");
        printBox(box);

        System.out.println("\nAfter Stones Fall:");
        char[][] intermediate = simulateStonesFalling(box);
        printBox(intermediate);

        System.out.println("\nRotated Box:");
        char[][] rotatedBox = rotateTheBox(box);
        printBox(rotatedBox);
    }

    private static char[][] simulateStonesFalling(char[][] box) {
        // Create a copy of the box for visualization of the intermediate state
        char[][] simulatedBox = new char[box.length][box[0].length];
        for (int i = 0; i < box.length; i++) {
            Arrays.fill(simulatedBox[i], '.');
            int idx = box[0].length - 1;
            for (int j = box[0].length - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    simulatedBox[i][j] = '*';
                    idx = j - 1;
                } else if (box[i][j] == '#') {
                    simulatedBox[i][idx--] = '#';
                }
            }
        }
        return simulatedBox;
    }
}

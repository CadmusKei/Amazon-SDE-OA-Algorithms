import java.util.LinkedList;
import java.util.Queue;

public class GridClassPractice {

    private static class Grid {

        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0}
        };

        int rows = grid.length;
        int cols = grid[0].length;

        private void displayGrid() {

            for (int i = 0; i < rows;i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }

        private int maxIslandSize(){
            int count = 0;
            int max = 0;
            int currentMax = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        count++;
                        max = Math.max(max, dfs(i, j));
                    }
                }
            }
            System.out.println("Number of Islands: " + count);
            System.out.println();
            return max;
        }

        private int dfs(int i, int j){
            if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) return 0;

            grid[i][j] = 0;
            return 1 + dfs(i -1, j) + dfs(i + 1, j) + dfs(i, j -1) + dfs(i, j + 1);
        }

        private int spreadRot(){

            // Create Queue and additional variables
            Queue<int[]> queue = new LinkedList<int[]>();
            int fresh = 0;
            int minutes = 0;

            // find Rotten
            // Count Fresh
            for (int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) queue.add(new int[]{i, j});
                    if (grid[i][j] == 1) fresh++;
                }
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};





        }

    }

    public static void main(String[] args) {
        Grid grid = new Grid();

        grid.displayGrid();

        System.out.println(grid.maxIslandSize());

    }
}

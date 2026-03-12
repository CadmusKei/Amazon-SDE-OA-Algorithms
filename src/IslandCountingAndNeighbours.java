import java.util.Arrays;

public class IslandCountingAndNeighbours {

    private static class Grid {

        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0}
        };

        int rows = grid.length;
        int cols = grid[0].length;

        private void printGrid()
        {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }
        }

        private void getNeighbours(int[] pos) {
            int[] dr =  {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            System.out.println("Neighbours of " + Arrays.toString(pos));

            for (int k = 0; k < 4; k++)
            {
                int newRow = pos[0] + dr[k];
                int newCol = pos[1] + dc[k];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    System.out.print(grid[newRow][newCol] + " ");

                }

            }
        }

        private void countIslandsAndSize() {
            int count = 0;
            int maxSize = 0;
            int currentMax = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        maxSize = Math.max(maxSize, currentMax);
                        count++;
                        currentMax = dfs(i, j);
                    }
                }
            }
            System.out.println("Number of islands: " + count);
            System.out.println("Max Island Size: " + maxSize);
        }

        private int dfs(int i, int j) {

            if (i < 0 || i >= rows || j<0 || j>= cols || grid[i][j] == 0) return 0;

            grid[i][j] = 0;
            return 1
               + dfs(i - 1, j)
               + dfs(i + 1, j)
               + dfs(i, j -1)
               + dfs(i, j + 1);

        }

    }

    public static void main(String[] args) {

        Grid grid = new Grid();
        grid.countIslandsAndSize();

    }


}

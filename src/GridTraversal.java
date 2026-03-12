import java.util.Arrays;

public class GridTraversal {

    private static class Grid {

        int[][] grid = {
            {0, 1, 1, 0, 0},
            {1, 3, 0, 4, 1},
            {0, 0, 2, 1, 0},
            {1, 0, 1, 1, 1}
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
    }

    public static void main(String[] args) {

        Grid grid = new Grid();
        grid.printGrid();
        int[] pos = {1, 2};
        grid.getNeighbours(pos);

    }


}

import java.util.LinkedList;
import java.util.Queue;

public class OrangeBFS {

    private static class Grid {

        int[][] grid = {
                {2, 0, 1, 1, 1},
                {1, 1, 0, 0, 2},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 0, 0}
        };

        int rows = grid.length;
        int cols = grid[0].length;

        private void displayGrid(){
            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        private int spreadRot() {

            Queue<int[]> queue = new LinkedList<>();
            int fresh = 0;
            int minutes = -0;

            displayGrid();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) queue.add(new int[]{i,j});
                    if (grid[i][j] == 1) fresh++;
                }
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!queue.isEmpty() && fresh > 0) {

                int size = queue.size();

                for (int s = 0; s < size; s++)
                {
                    int[] rottenPos = queue.poll();
                    int r = rottenPos[0];
                    int c = rottenPos[1];

                    for (int k = 0; k < 4; k++) {

                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                            grid[nr][nc] = 2;
                            fresh--;
                            queue.add(new int[]{nr, nc});
                            displayGrid();
                        }
                    }
                }
                minutes++;
            }
            return fresh == 0 ? minutes : -1;
        }
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        int minutes = grid.spreadRot();
        System.out.println("Minutes: " + minutes);
    }


}

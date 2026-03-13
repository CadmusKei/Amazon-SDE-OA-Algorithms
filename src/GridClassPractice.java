import java.util.LinkedList;
import java.util.Queue;

public class GridClassPractice {

    private static class Grid {

        int[][] grid = {
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
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

        private int spreadZombies(){

            // Create Queue and additional variables
            Queue<int[]> queue = new LinkedList<int[]>();
            int humans = 0;
            int minutes = 0;

            // Show Original Grid
            displayGrid();

            // find Zombies
            // Count humans
            for (int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) queue.add(new int[]{i, j});
                    if (grid[i][j] == 1) humans++;
                }
            }

            // define direcrions
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            //Start simulation!
            // As long as queue isnt empty and there are humans to infect
            while (!queue.isEmpty() && humans > 0) {
                // While current snapshot of queue isnt empty, spread rot based on its current size
                int size = queue.size();
                for (int s = 0; s < size; s++) {

                    // Store zombie coords
                    int[] zombiePos = queue.poll();
                    int r = zombiePos[0];
                    int c = zombiePos[1];

                    for (int k = 0; k < 4; k++) {

                        // Consult Zombie neighours
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        // Infect valid neighbouring humans
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                            grid[nr][nc] = 2;
                            humans--;
                            queue.add(new int[]{nr, nc});
                            displayGrid();
                            System.out.println();
                        }
                    }
                }
                minutes++;
            }
            return humans == 0 ? minutes : -1;
        }

        private int shortestPath(){

            // Create queue and init steps
            Queue<int[]> queue = new LinkedList<>();
            int steps = 0;
            // Keep track of visitors!
            boolean[][] visited = new boolean[rows][cols];

            // Make sure start and goal are valid.
            if (grid[0][0] == 1 || grid[rows-1][cols-1] == 1) return -1;

            // Vital Start steps.
            // Add the top to queue for first wave
            queue.add(new int[]{0,0});
            // Mark as visited to stop backtracking (Minor effic)
            visited[0][0] = true;

            // Set direction vairants
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int s = 0; s < size; s++) {

                    int[] currentPos = queue.poll();
                    int r = currentPos[0];
                    int c = currentPos[1];

                    if (r == rows-1 && c == cols-1) return steps;

                    for (int k = 0; k < 4; k++) {

                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc] && grid[nr][nc] == 0) {

                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc});

                        }
                    }
                }
                // After processing 1 layer
                steps++;
            }
            return -1;
        }

    }

    public static void main(String[] args) {
        Grid grid = new Grid();

        grid.displayGrid();
//        System.out.println(grid.maxIslandSize());
//        int minutes = grid.spreadZombies();
//        System.out.println("Map Infection took " + minutes + " minutes.");
        int steps = grid.shortestPath();
        System.out.println("The shortest path down from (0,0)  took: " + steps + " steps.");

    }
}

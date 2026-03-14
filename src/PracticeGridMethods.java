import java.util.LinkedList;
import java.util.Queue;

public class PracticeGridMethods {

    public static void main(String[] args) {
        Grid grid = new Grid();

        System.out.println("=== CITY GRID ===");
        grid.displayGrid();
        System.out.println("  (0 = road, 1 = building, 2 = power station)");

        System.out.println("\n=== POWER GRID SIMULATION (Multi-Source BFS) ===");
        int powerTime = grid.timeToSpreadPower();
        if (powerTime == -1) {
            System.out.println("Some buildings cannot be reached by the power grid!");
        } else {
            System.out.println("All buildings powered in: " + powerTime + " minutes");
        }

        System.out.println("\n=== SUBURB ANALYSIS (DFS) ===");
        int biggestSuburb = grid.biggestSuburb();
        System.out.println("Biggest connected suburb: " + biggestSuburb + " buildings");

        System.out.println("\n=== SHORTEST PATH DOWNTOWN (BFS) ===");
        int travelTime = grid.timeDownTown();
        if (travelTime == -1) {
            System.out.println("No valid path to downtown exists!");
        } else {
            System.out.println("Fastest route downtown: " + travelTime + " steps");
        }
    }

    private static class Grid {
        int[][] grid = {
                {0,0,0,1,1,2,1},
                {2,1,0,1,0,0,0},
                {0,1,0,0,0,1,2},
                {0,0,0,1,0,1,1},
                {1,2,1,1,0,0,0},
        };

        int rows = grid.length;
        int cols = grid[0].length;

        // =============================================
        // DISPLAY: Prints the grid to the console
        // =============================================
        private void displayGrid(){
            for (int i = 0; i < rows; i++) {
                System.out.print("  ");
                for (int j = 0; j < cols; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }

        // =============================================
        // DFS: Finds the biggest connected island
        // of buildings (1s and 2s) in the grid.
        // Uses recursive DFS, marking visited cells
        // as 0 to avoid revisiting.
        // Time: O(n*m) | Space: O(n*m) recursion stack
        // =============================================
        private int biggestSuburb(){
            int count = 0, max = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1 || grid[i][j] == 2) {
                        count++;
                        max = Math.max(max, dfs(i, j));
                    }
                }
            }
            System.out.println("Total suburbs found: " + count);
            return max;
        }

        private int dfs(int i, int j) {
            if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) return 0;
            grid[i][j] = 0;
            return 1 + dfs(i - 1, j) + dfs(i + 1, j) + dfs(i, j - 1) + dfs(i, j + 1);
        }

        // =============================================
        // MULTI-SOURCE BFS: Simulates power spreading
        // from multiple power stations (2s) simultaneously.
        // Returns minutes until all buildings (1s) are
        // powered, or -1 if some are unreachable.
        // Time: O(n*m) | Space: O(n*m)
        // =============================================
        private int timeToSpreadPower() {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[rows][cols];
            int buildings = 0, minutes = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) {
                        queue.add(new int[]{i, j});
                        visited[i][j] = true;
                    }
                    if (grid[i][j] == 1) buildings++;
                }
            }

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!queue.isEmpty() && buildings > 0) {
                int size = queue.size();
                for (int s = 0; s < size; s++) {
                    int[] stationPos = queue.poll();
                    int r = stationPos[0];
                    int c = stationPos[1];

                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc] && grid[nr][nc] == 1) {
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc});
                            buildings--;
                        }
                    }
                }
                minutes++;
            }
            return buildings == 0 ? minutes : -1;
        }

        // =============================================
        // SHORTEST PATH BFS: Finds the minimum steps
        // to travel from top-left (0,0) to bottom-right
        // corner through open roads (0s).
        // Returns step count or -1 if no path exists.
        // Time: O(n*m) | Space: O(n*m)
        // =============================================
        private int timeDownTown() {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[rows][cols];
            int time = 0;

            if (grid[0][0] != 0 || grid[rows - 1][cols - 1] != 0) return -1;

            queue.add(new int[]{0, 0});
            visited[0][0] = true;

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int s = 0; s < size; s++) {
                    int[] pos = queue.poll();
                    int r = pos[0];
                    int c = pos[1];

                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];

                        if (r == rows - 1 && c == cols - 1) return time;

                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc] && grid[nr][nc] == 0) {
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc});
                        }
                    }
                }
                time++;
            }
            return -1;
        }
    }
}
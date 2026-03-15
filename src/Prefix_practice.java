import java.util.*;

public class Prefix_practice {

    public static void main(String[] args) {

        String substring = "*|**|*|*";
        int[] startIndex = {0, 4};
        int[] endIndex = {6, 6};
        System.out.println("Number of compartments");

    }

    public static List<Long> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        int n = s.length();

        // Build star prefix sum
        int[] starPrefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            starPrefix[i + 1] = starPrefix[i] + (s.charAt(i) == '*' ? 1 : 0);
        }

        // Precompute pipe positions
        List<Integer> pipes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') pipes.add(i);
        }

        List<Long> results = new ArrayList<>();

        for (int i = 0; i < startIndices.size(); i++) {
            int start = startIndices.get(i) - 1; // convert to 0-indexed
            int end = endIndices.get(i) - 1;

            // Binary search for first pipe >= start
            int left = Collections.binarySearch(pipes, start);
            if (left < 0) left = -left - 1;

            // Binary search for last pipe <= end
            int right = Collections.binarySearch(pipes, end);
            if (right < 0) right = -right - 2;

            // No closed compartment
            if (left >= pipes.size() || right < 0 || left >= right) {
                results.add(0L);
                continue;
            }

            int firstPipe = pipes.get(left);
            int lastPipe = pipes.get(right);

            // Count stars strictly between firstPipe and lastPipe
            long count = starPrefix[lastPipe] - starPrefix[firstPipe + 1];
            results.add(count);
        }

        return results;
    }




}

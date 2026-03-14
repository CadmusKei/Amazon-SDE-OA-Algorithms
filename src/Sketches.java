import java.util.*;

public class Sketches {

    // ####### TWO SUM VARIANTS ####### //

    private int[] twoSum(int[] arr, int target) {
        if (arr == null || arr.length <= 0) return new int[]{};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++ ) {
            int compliment = target - arr[i];
            if (map.containsKey(compliment)) {
                // Give indexs
                return new int[]{map.get(compliment), i};
            }
            // If we haven't found a solution yet, place current value at current index
            map.put(arr[i], i);
        }
        return new int[]{};
    }

    private int countTwoSums(int[] arr, int target){
        if (arr == null || arr.length <= 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num : arr)
        {
            int complement = target - num;
            if (map.containsKey(complement)) {
                count += map.get(complement);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    // Two Pointer
    private  int[] twoPointerSum(int[] arr, int target) {
        if (arr == null || arr.length <= 0) return new int[]{};
        int right = arr.length-1, left = 0;
        while (right > left){
            int sum = arr[right] + arr[left];
            if (sum == target) return new int[]{left, right};
            else if (target < sum) left++;
            else if (target > sum) right--;
        }
        return new int[]{};
    }

    // uniquePairs
    private static HashSet<List<Integer>> uniqueTwoSumPairs(int[] arr, int target) {
        HashSet<Integer> freqMap = new HashSet<>();
        HashSet<List<Integer>> uniqueSums = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (freqMap.contains(complement)) uniqueSums.add(Arrays.asList(Math.min(num, complement), Math.max(num, complement)));
            freqMap.add(num);
        }
        return uniqueSums;
    }

    // ####### PREFIX SUM VARIANTS ####### //

    // Check if a subarray sums to target
    private boolean hasPrefixSubArray(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (map.containsKey(currentSum - target)) {
                return true;
            }
            map.put(currentSum, i);
        }
        return false;
    }

    // Show the integers that sum to target
    public static int[] subarrays(int[] arr, int target) {
        if (arr == null || arr.length == 0 ) return new int[]{};
        HashMap<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (map.containsKey(currentSum - target)) {
                int start = map.get(currentSum - target) + 1;
                return new int[]{start, i};
            }
            map.put(currentSum, i);
        }
        return new int[]{};
    }

    // ####### SLIDING WINDOW VARIANTS ####### //

    // The classic max sum in k sized window problem
    private int maxSumWindow(int[] arr, int k ) {
        int maxSum = 0, windowSum = 0;

        for (int i = 0; i < k; i++){
            windowSum += arr[i];
        }
        maxSum = windowSum;
        for (int i = k; i < arr.length; i++)
        {
            windowSum = windowSum - arr[i-k] + arr[i];
            maxSum = Math.max(windowSum, maxSum);
        }
        return maxSum;
    }

    // Most interviews will use dynamic windows which change size ot meet a criteria
    private int minlength(int[] arr, int target) {
        int sum = 0, start = 0, minLen = Integer.MAX_VALUE;

        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];
            while (sum >= target) {
                minLen = Math.min(minLen, end - start);
                sum -= arr[start];
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // Max possible substring
    private int maxSubarray(String str, int k) {
        if (str==null || str.equals("") || k <= 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = str.toCharArray();
        int start = 0, max = 0;

        for (int end = 0; end < arr.length; end++) {
            map.put(arr[end], map.getOrDefault(arr[end], 0) + 1);
            while (map.size() > k)
            {
                map.put(arr[start], map.get(arr[start]) - 1);
                if (map.get(arr[start]) == 0) {
                    map.remove(arr[start]);
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

}

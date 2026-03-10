
import java.util.*;

public class ArraySumAndSubArrayProblems {

    public static void main(String[] args)
    {
        int[] arr = {1, 5, 7, 8, 3, 9, 12, 4};
        System.out.println((hasPair(arr, 10)));
    }

    public static int[] twoSum(int[] arr, int target) {
        if (arr == null || arr.length <= 0) return new int[]{};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
        {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{};
    }

    public static boolean hasPair(int[] arr, int target) {

        if (arr == null || arr.length <= 0) return false;
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (set.contains(complement)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static int[] twoPointerSum(int[] arr, int target) {
        if (arr == null || arr.length == 0) return new int[]{};
        int right = arr.length - 1, left = 0;
        while (right > left)
        {
            int sum = arr[right] + arr[left];
            if (sum == target) return new int[]{left, right};
            else if (sum < target) left++;
            else if (sum > target) right--;
        }
        return new int[]{};
    }

    public static HashSet<List<Integer>> uniqueTwoSumPairs(int[] arr, int target) {
        if (arr == null || arr.length == 0) return new HashSet<>();
        HashSet<Integer> seen = new HashSet<>();
        HashSet<List<Integer>> uniqueSums = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                uniqueSums.add(Arrays.asList(Math.min(num, complement), Math.max(num, complement)));
            }
            seen.add(num);
        }
        return uniqueSums;
    }


    public static int countUniquePairs(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;
        HashSet<Integer> seen = new HashSet<>();
        HashSet<List<Integer>> uniqueSums = new HashSet<>();
        for (int num : arr) {
            int complement = target - num;
            if (seen.contains(complement)) {
                uniqueSums.add(Arrays.asList(Math.min(num, complement), Math.max(num, complement)));
            }
            seen.add(num);
        }
        return uniqueSums.size();
    }

    public static boolean hasSubarraySum(int[] arr, int target) {
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1);
        int currSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (prefixSumMap.containsKey(currSum - target)) {
                return true;
            }
            prefixSumMap.put(currSum, i);
        }
        return false; // no subarray found
    }

    // Return SubArray That Sums To Target
    public static int[] subArrayToTarget(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // Each key is a PREFIX Sum
        map.put(0, -1);
        int currSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (map.containsKey(currSum - target)) {
                int start = (currSum - target) + 1;
                return new int[]{start, i};
            }
            map.put(currSum, i);
        }
        return new int[]{};
    }

    public static int[] removeDuplicates(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);
        int[] cleanArr = new int[set.size()];
        int i = 0;
        for (int num : set) cleanArr[i++] = num;
        return cleanArr;
    }
}

import java.util.*;

public class Sketches {

    public static void main(String[] args){

        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(countContArrays(arr, 5));

    }

    private static int[] twoSum(int[] arr, int target) {
//        if () Edge case
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{};
    }

    // Given an integer array and a target k, return the number of continuous subarrays that sum to k.
    private static int countContArrays(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int currentSum = 0, count = 0;
        for (int i =0;i < arr.length; i++) {
            currentSum += arr[i];
            if (map.containsKey(currentSum - target)) {
                count += map.get(currentSum - target);
            }
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }

    // Given an integer array and a value k, return the number of unique pairs where the difference between the two numbers equals k.
    private static int countDifContArrays(int[] arr, int target) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for (int num : arr) {
            if (set.contains(num - target)) count++;
            set.add(num);
        }
        return count;
        // k1 - k2 = target => target + k1 = k2;
    }



}

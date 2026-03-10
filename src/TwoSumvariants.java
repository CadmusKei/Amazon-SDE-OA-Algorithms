
import java.util.HashMap;
import java.util.HashSet;

public class TwoSumvariants {

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




}

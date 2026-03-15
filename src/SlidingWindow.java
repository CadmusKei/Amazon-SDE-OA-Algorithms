import java.util.*;

public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 1};
        int target = 11;
        int max = longestUnqiueSubstrting("aweroaakjhagf");
        System.out.println("Max " + max);
    }

    // The classic max sum in k sized window problem
    private static int maxSumOf(int[] arr, int k)
    {
        int windowSum = 0, maxSum = 0;
        // Create first window
        for (int i =0; i < k; i++) {
            windowSum += arr[i];
        }
        // Initialise maxSum
        maxSum = windowSum;
        // Find the max, Notice how we start after the first window (i=k) and compute the ends for efficiency
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i-k] + arr[i];
            maxSum = Math.max(windowSum, maxSum);
        }
        return maxSum;
    }

    // Most interviews will use dynamic windows which change size ot meet a criteria
    // Minimum subarray sum >= s
    private static int minSubArrayLens(int[] arr, int s) {
        int start = 0, sum = 0, minLen =  Integer.MAX_VALUE;

        for (int end = 0; end < arr.length; end++){
            sum += arr[end];
            while (sum >= s) {
                minLen = Math.min(minLen, end - start);
                sum -= arr[start];
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // This version shows how the above works and how we grow to the top first and then shrink the bottom.
    // (Think we quickly fill the bucket fast and then slowly remove the most we possibly can.)
    // Here we are looking for a minimum so we use (and should recoginse) the pattern - Shrink while still valid.
    public static int minSubArrayLen(int s, int[] nums) {
        int start = 0, sum = 0, minLen = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end]; // expand window
            System.out.println("sum: " + sum);
            System.out.println("Loop if sum " + sum + " is greater than " + s);
            while (sum >= s) { // shrink window
                System.out.println();
                System.out.println();
                System.out.println(sum + " is greater than or equal " + s);
                System.out.println("end: " +  end + ", start: " + start);
                minLen = Math.min(minLen, end - start + 1);
                System.out.println("minLen = Math.min(minLen, end - start + 1): " + minLen);
                sum -= nums[start];
                System.out.println("sum -= nums[start]: " + sum);
                start++;
                System.out.println("Start++: " + start);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println(minLen + " is the minimum length");
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // longest substring with k unique elements
    // For maximums we use the - while invalid - structure.
    private static int maxSubstring(String str, int k) {
        if (str==null || str.equals("") || k <= 0) return 0;
        // We need a map to count and see how many values of the integer exist
        HashMap<Character, Integer> freqMap = new HashMap<>();
        char[] arr = str.toCharArray();
        int max = 0, start = 0;

        // O(n) loop through the array once
        for (int end = 0; end < arr.length; end++) {
            // Increase the size of the window and keep track of what we're adding.
            freqMap.put(arr[end], freqMap.getOrDefault(arr[end], 0) + 1);
            // While invalid
            while (freqMap.size() > k){
                // Remove values from the front of the value
                freqMap.put(arr[start], freqMap.get(arr[start]) - 1);
                // Until none remain, then remove it from our map
                if (freqMap.get(arr[start]) == 0) {
                    freqMap.remove(arr[start]);
                }
                start++;
            }
            // Find max
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    // Longest string with no duplicate characters
    private static int longestUnqiueSubstrting(String str) {
        if (str==null || str.equals("")) return 0;
        HashSet<Character> set = new HashSet<>();
        char[] arr = str.toCharArray();
        int start = 0, max  = 0;

        for (int end = 0; end < arr.length; end++) {
            while(set.contains(arr[end])) {
                set.remove(arr[start]);
                start++;
            }
            set.add(arr[end]);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    // Max consecutive 1s of a binary array with k allowed flips.
    private static int longestBinarySubarray(int[] arr, int k) {
        int start = 0, max = 0, zeroCount = 0;

        for (int end = 0; end < arr.length; end++) {
            if (arr[end] == 0) zeroCount++;
            while (zeroCount > k) {
                if (arr[start] == 0) zeroCount--;
                start++;
            }
            max = Math.max(max, end-start+1);
        }
        return max;
    }



}


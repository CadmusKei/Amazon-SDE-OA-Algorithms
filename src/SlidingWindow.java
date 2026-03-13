public class SlidingWindow {

    // Find the max sum of a subarray with length k
    private int maxSumOf(int[] arr, int k) {
        int windowSum = 0;
        int maxSum = 0;
        for (int i = 0; i < k; i++)
        {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        for (int i = k; k < arr.length; i++)
        {
            windowSum = windowSum - arr[i - k] + arr[i];
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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 1};
        int target = 11;
        int minLen = minSubArrayLen(target, arr);
    }
}

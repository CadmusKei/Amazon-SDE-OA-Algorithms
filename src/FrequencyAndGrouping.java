import java.util.*;

public class FrequencyAndGrouping {

    public static void main(String[] args) {

        System.out.println(firstNonRepeat("aabb"));

    }

    // A very basic frequency counter
    private static HashMap<Character, Integer> countVowels(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if ("AEIOUaeiou".contains(Character.toString(c))) map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    // Simple maxElement lookup
    private static int returnMax(int[] arr) {
        if (arr==null || arr.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxCount = 0, maxElement = -1;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > maxCount) {
                maxCount = map.get(num);
                maxElement = num;
            }
        }
        return maxElement;
    }

    // Return the index of the first non repeat character
    private static int firstNonRepeat(String str) {
        if (str == null || str.isEmpty()) return -1;
        char[] arr = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap();
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < arr.length; i++) if (map.get(arr[i]) == 1) return i;
        return -1;
    }

    // Grouping numbergrams
    private static ArrayList<List<Integer>> numberGrams(int[] arr) {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<List<Integer>> finalArr = new ArrayList<>();
        for (int num : arr) {
            String sig = signature(num);
            map.putIfAbsent(sig, new ArrayList<>());
            map.get(sig).add(num);
        }
        return new ArrayList<>(map.values());
    }
    // Helper Method
    private static String signature(int num) {
        String numAsString = Integer.toString(num);
        char[] letters = numAsString.toCharArray();
        Arrays.sort(letters);
        return Arrays.toString(letters);
    }



}




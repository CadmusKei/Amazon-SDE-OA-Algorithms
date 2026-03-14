import java.util.*;

public class CountingAndFrequency {

    // A very basic frequency counter
    private static HashMap<Character, Integer> countVowels(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if ("AEIOUaeiou".contains(Character.toString(c))) map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

}

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Greedy {

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 3, 5, 2};
        List<Integer> list = Arrays.asList(arr);
        int max =maxMem(list);
        System.out.println(max);
    }

    // Sort primary and Secondary servers such that the secondary servers have >= memory.
    // Since for all primary servers, there must be a second. The size of n (number of servers)
    // must be even.
    private static int maxMem(List<Integer> memory) {
        if (memory==null || memory.isEmpty());
        int n = memory.size();
        Collections.sort(memory);
        int start = (n % 2 == 0 ? 0 : 1);
        int sum = 0;
        for (int i = start; i < memory.size(); i+=2) {
            sum += memory.get(i);
        }
        return sum;
    }

}

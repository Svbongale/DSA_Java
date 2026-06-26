package ArraysDSA;


// Input -> [1,2,4,1,3,2,3]
// Output -> 4 (Since 4 occurs only once)

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingElement {
    public static void main(String[] args) {
        int[] arr = {1,2,4,4,7,1,3,2,3};

        // Key = Digit, Value = Count of Digit.
        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int val : arr) {
            if (map.containsKey(val)) {
                // increment count
                map.put(val, map.get(val) + 1);
            } else {
                map.put(val, 1);
            }
        }

        map.entrySet().forEach(entry -> {
            if (entry.getValue().equals(1)) {
                System.out.println("Non repeating digit " + entry.getKey());
            }
        });


//        Using XOR - More efficient

//        int unique = arr[0];
//
//        for (int i = 1; i < arr.length; i++) {
//            unique = unique ^ arr[i];
//        }
//
//        return; unique;
    }
}

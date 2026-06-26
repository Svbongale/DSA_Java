package ArraysDSA;

// Input -> {-2,-5,6,-2,-3,1,5,-6}
// Output -> "7" from subarray {6,-2,-3,1,5} Because sum of the elements in sub array is max
// compared to other subarray sum

public class MaxContiguousSubArraySum {
    public static void main(String[] args) {
        int[] arr1 = {1,3,-1,4,2,5,8,9,0,-2};
        int[] arr = {-2,-5,6,-2,-3,1,5,-6};

        int finalMax = arr[0];
        int currMax = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currMax = Math.max(arr[i], arr[i] + currMax);

            finalMax = Math.max(currMax, finalMax);
        }
        System.out.println("Max subarray sum = " + finalMax);
    }
}

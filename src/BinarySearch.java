public class BinarySearch {
    public static void main(String[] args) {
        BinarySearchImpl obj = new BinarySearchImpl();

        int length = 100;
        int searchEle = 45;
        int[] sortedArr = new int[length];

        for (int i = 0; i < length; i++) {
            sortedArr[i] = i*2;
        }

        boolean retVal = obj.binarySearch(searchEle, sortedArr);
        System.out.println(retVal);
    }
}


class BinarySearchImpl {
    public boolean binarySearch(int searchEle, int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right)/2;

            if (searchEle == arr[mid]) {
                System.out.println("Element found at index " + mid);
                return true;
            }

            if (searchEle > arr[mid]) {
                left = mid + 1;
            } else if (searchEle < arr[mid]) {
                right = mid - 1;
            }
        }

        return false;
    }
}

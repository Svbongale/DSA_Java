public class BubbleSort {
    public static void main(String[] args) {
        BubbleSortImpl bubbleSortObj = new BubbleSortImpl();

        int[] arr = {2,5,1,3,7,8,4,8};

        System.out.println("Before sorting");
        bubbleSortObj.printArray(arr);

        bubbleSortObj.bubbleSort(arr);

        System.out.println("After sorting");
        bubbleSortObj.printArray(arr);
    }
}

class BubbleSortImpl {
    public void bubbleSort(int[] arr) {

        for (int j = arr.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                int temp = 0;
                if (arr[i] > arr[i + 1]) {  // reverse condition to change order or sorting
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
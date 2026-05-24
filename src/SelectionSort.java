public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {3,2,5,7,9,8,0,1};
        SelectionSortImpl obj = new SelectionSortImpl();

        System.out.println("Before Sorting: ");
        obj.printArray(arr);

        obj.selectionSort(arr);

        System.out.println("After Sorting: ");
        obj.printArray(arr);
    }
}

class SelectionSortImpl {
    public void selectionSort(int[] arr) {
         int size = arr.length;
         int minIndex = -1;
         int temp = 0;

        for (int i = 0; i < size - 1; i++) {
            minIndex = i;
            for (int j = i+1; j < size; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }

            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

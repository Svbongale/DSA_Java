import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSortImpl bubbleSortObj = new BubbleSortImpl();

        int[] arr =new int[20];
        int i = 0;
        Random random = new Random();
        while (i < arr.length) {
            arr[i] = random.nextInt();
            i++;
        }
        bubbleSortObj.bubbleSort(arr);
    }
}

class BubbleSortImpl {
    public void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int temp = 0;
            if ((i < arr.length - 1) && (arr[i] < arr[i+1])) {
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
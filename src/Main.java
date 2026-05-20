import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Linear Search");

        int size = 10;
        int[] nums = new int[10];
        int searchElement = 0;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt();

            if(i == size/2) {
                searchElement = nums[i];
            }
        }
        System.out.println("Search Element " + searchElement);

        LinearSearch obj = new LinearSearch();
        boolean retVal = obj.searchElement(searchElement, nums);
        System.out.println(retVal);
    }
}

class LinearSearch {
    public boolean searchElement(int searchElement, int[] arr) {
           for (int i = 0; i < arr.length; i++) {
               if(arr[i] == searchElement) {
                   System.out.println("Search element found at index " + i);
                   return true;
               }
           }
           return false;
    }
}
import java.util.Scanner;

public class LinkedListRemoveNode {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LLNodeOps llNodeOps = new LLNodeOps();

        System.out.println("Enter the size of the Lise");
        int size = scanner.nextInt();


        System.out.println("Insert elements: ");

        for (int i = 0; i < size; i++) {
            boolean isHead = i == 0;
            int val = scanner.nextInt();
            llNodeOps.insertNewNode(val, isHead);
        }

        System.out.println("Formed Linked list -> ");
        llNodeOps.printLinkedList(llNodeOps.getHead());
    }
}


class LLNode {
    int val;
    LLNode next;

    LLNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class LLNodeOps {

    LLNode head = null;
    LLNode ptr = null;

    public void insertNewNode(int val, boolean isHead) {
        LLNode node = new LLNode(val);
        if (isHead) {
            head = node;
            ptr = head;
        }
        ptr.next = node;
        node.next = null;
    }

    public LLNode getHead() {
        return head;
    }

    public void printLinkedList(LLNode ptr) {
        while (ptr.next != null) {
            System.out.print( "[" + ptr.val + "]" + "->");
            ptr = ptr.next;
        }
    }
}

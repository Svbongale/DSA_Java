import org.w3c.dom.Node;

public class LinkedListImpl {

    public static void main(String[] args) {

        // Init linked list with a head node
        LinkedListNode l1 = new LinkedListNode(10);

        // Create new nodes with values
        LinkedListNode l2 = new LinkedListNode(20);
        LinkedListNode l3 = new LinkedListNode(30);

        // Link all the created nodes
        l1.next = l2;
        l2.next = l3;
        l3.next = null;


        TraverseLinkedList traverseLinkedList = new TraverseLinkedList();
        traverseLinkedList.printLinkedList(l1);
    }
}


class LinkedListNode {
    int val;
    LinkedListNode next;

    LinkedListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class TraverseLinkedList {
    public void printLinkedList(LinkedListNode head) {
        LinkedListNode ptr = head;

        while (ptr != null) {
            System.out.print(ptr.val + "-->");
            ptr = ptr.next;
        }
    }
}

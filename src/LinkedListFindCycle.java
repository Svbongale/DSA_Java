public class LinkedListFindCycle {

    public static void main(String args[]) {

        int[] values = {1, 2, 5, 6, 7, 3, 2};
        Node node = new Node(1);
        Node head = node.formLinkedList(values);
        node.printLinkedList(head);
        node.createCycle(head, 5);


        FindCycle findCycle = new FindCycle();
        boolean hasCycle = findCycle.findCycle(head);

        System.out.println("Linked List has cycle: " + hasCycle);
    }
}


class FindCycle {
    public boolean findCycle(Node head) {

        Node fastPtr = head;
        Node slowPtr = head;

        if (head == null && head.next == null) {
            return false;
        }

        while (fastPtr.next != null && slowPtr !=null && slowPtr != null) {

            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;

            if (fastPtr == slowPtr) {
                return true;
            }
        }

        return false;
    }
}

class Node {
    int val;
    Node next;

    // Constructor to initialize the node with data
    public Node(int val) {
        this.val = val;
        this.next = null;
    }

    public Node formLinkedList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        Node head = new Node(values[0]);
        Node current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }

        return head;
    }

    public void printLinkedList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print("->" + current.val + " ");
            current = current.next;
        }
    }

    public void createCycle(Node head, int pos) {
        if (pos == -1) {
            return;
        }

        Node tail = head;
        Node cycleNode = null;
        int index = 0;

        while (tail.next != null) {
            if (index == pos) {
                cycleNode = tail;
            }
            tail = tail.next;
            index++;
        }

        tail.next = cycleNode;
    }
}

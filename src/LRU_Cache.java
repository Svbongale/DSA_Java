import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LRU_Cache {
    public static void main(String[] args) {

        MemMap memMap = new MemMap();
        NodeInit nodeInit = new NodeInit();
        LRU_CacheImpl lruCacheImpl = new LRU_CacheImpl();
        LinkedListOps linkedListOps = new LinkedListOps();



        // Initialize Doubly linked list with a head and tail
        NodeInit[] headAndTail = nodeInit.initHeadAndTail();

        System.out.println("Head node is with value: " + headAndTail[0].value);
        System.out.println("Tail node is with value: " + headAndTail[1].value);

        linkedListOps.printLinkedList(headAndTail[0]);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cache capacity: ");
        int capacity = scanner.nextInt();
        memMap.setMapCapacity(capacity);
        System.out.println("Cache capacity set to: " + memMap.getMapCapacity());

        int[] values = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            System.out.print("Enter " + (i+1) + " value to insert into LRU Cache: ");
            values[i] = scanner.nextInt();
        }

        System.out.println("Values to be inserted in the cache are: " + Arrays.toString(values));

        linkedListOps.addNodes(values, headAndTail[0], headAndTail[1]);
        linkedListOps.printLinkedList(headAndTail[0]);
    }
}

class NodeInit {

    int value;
    NodeInit next;
    NodeInit prev;

    private NodeInit head;
    private NodeInit tail;

    NodeInit() {
    }

    NodeInit(int value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public NodeInit[] initHeadAndTail() {
        NodeInit head = new NodeInit(-1);
        NodeInit tail = new NodeInit(-1);

        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;

        return new NodeInit[]{head, tail};
    }
}


// Handle Doubly LinkedList Operations
class LRU_CacheImpl {


}

class LinkedListOps {

    MemMap memMap = new MemMap();

    public void addNodes(int[] values, NodeInit head, NodeInit tail) {
        if (values == null || values.length == 0) {
            return;
        }

        NodeInit ptr = head;

        for (int i = 0; i < values.length; i++) {

            NodeInit newNode = new NodeInit(values[i]); // Create new node with value from the list
            ptr.next = newNode; // Link head to new node
            newNode.prev = head; // Link new node to head
            newNode.next = tail; // Link new node to tail
            tail.prev = newNode; // Link tail node to new node
            ptr = newNode; // Move pointer to new node

            // Update value in MemMap once a new node is inserted
            memMap.addValAndAddressToMap(values[i], newNode);
        }

        System.out.println("Map Used Capacity: " + memMap.getMapUsedCapacity());
    }

    public int getValueFromNode(int value) {
        return -1;
    }

    public void setNodeValue(int key, int value) {
    }

    // Remove Next and Previous pointers of a node
    public void deleteNode(int value) {
        // delete Node
        // delete value in map
    }

    public void printLinkedList(NodeInit head) {
        NodeInit ptr = head;

        System.out.println("Updated Linked List: ");

        System.out.print("->");
        while (ptr != null) {
            System.out.print( "[" + ptr.value + "]" + "->");
            ptr = ptr.next;
        }

        System.out.println();
        System.out.println();
    }
}


// Handle HashMap operations
class MemMap {

    private int capacity;
    private int usedCapacity;

    //Store <Node Value, Node Address>
    Map<Integer, NodeInit> map = new HashMap<>();

    public void setMapCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMapCapacity() {
        return this.capacity;
    }

    public int getMapUsedCapacity() {
        return this.usedCapacity;
    }

    // Maintain map to hold value and address of a Node in the Doubly-Linked-List
    public void addValAndAddressToMap(int value, NodeInit node) {
        usedCapacity++;
        map.put(value, node);
    }

    public void printMap() {

        if (map.isEmpty()) {
            System.out.println("Map has no values!");
            return;
        }

        System.out.println("Updated Map---> ");
        for (int i = 0; i < capacity; i++) {
            System.out.println(i+1 + "--> " + map.get(i).value);
        }
    }
}

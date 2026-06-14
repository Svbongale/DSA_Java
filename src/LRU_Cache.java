import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LRU_Cache {
    public static void main(String[] args) {

        MemMap memMap = new MemMap();
        NodeInit nodeInit = new NodeInit();
        LRU_CacheImpl lruCacheImpl = new LRU_CacheImpl();
        LinkedListOps linkedListOps = new LinkedListOps(memMap, nodeInit);

        // Initialize Doubly linked list with a head and tail
        System.out.println("======================================");
        System.out.println("Initializing LRU Cache");
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

        System.out.println("======================================");
        System.out.println("Adding user input values to Cache");

        linkedListOps.addNodes(values, headAndTail[0], headAndTail[1]);
        linkedListOps.printLinkedList(headAndTail[0]);
        memMap.printMap();
        System.out.println();

        System.out.println("======================================");
        System.out.println("Update Existing Node");
        System.out.println("Insert the value to update in the cache ");
        int valueToUpdate = scanner.nextInt();
        System.out.println("Insert the updated value to update " + valueToUpdate + " in the cache ");
        int updatedValueToInsert = scanner.nextInt();
        System.out.println("Updating node " + valueToUpdate + "with value" + updatedValueToInsert);
        linkedListOps.setNodeValue(valueToUpdate, updatedValueToInsert);
        linkedListOps.lruCheckForGetAndUpdate(updatedValueToInsert);
        System.out.println("Performing LRU update...");
        System.out.println();
        memMap.printMap();
        linkedListOps.printLinkedList(headAndTail[0]);
        System.out.println();

        System.out.println("======================================");
        System.out.println("Delete Existing Node");

        System.out.println("Enter a value to delete from Cache: ");
        int valueToDelete = scanner.nextInt();
        System.out.println("Deleting node with value " + valueToDelete);
        linkedListOps.deleteNode(valueToDelete);

        System.out.println("======================================");
        System.out.println("Print Cache after Deleting Node");

        System.out.println("LRU Cache After deleting the node");
        linkedListOps.printLinkedList(headAndTail[0]);
        System.out.println("MemMap After deleting the node");
        memMap.printMap();
        System.out.println("======================================");

        System.out.println("Enter a value to fetch from the Cache");

        int valueToGet = scanner.nextInt();
        int fetchedValue = linkedListOps.getValueFromNode(valueToGet);
        System.out.println("Found value" + fetchedValue + "in cache");

        System.out.println("Performing LRU update...");
        linkedListOps.lruCheckForGetAndUpdate(fetchedValue);

        System.out.println("Cache after LRU");
        linkedListOps.printLinkedList(headAndTail[0]);
        System.out.println("MemMap after LRU");
        memMap.printMap();

        System.out.println("======================================");
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
        head = new NodeInit(-1);
        tail = new NodeInit(-1);

        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;

        return new NodeInit[]{head, tail};
    }

    public NodeInit[] getHeadAndTail() {
        return new NodeInit[]{this.head, this.tail};
    }
}


// Handle Doubly LinkedList Operations
class LRU_CacheImpl {



    // Update a node value
    // Add new Node (LRU check)
    // Delete a Node
    // Get a node (Update LRU)
    public NodeInit startLRUCache(NodeInit head) {
        MemMap memMap = new MemMap();
        NodeInit nodeInit = new NodeInit();
        LRU_CacheImpl lruCacheImpl = new LRU_CacheImpl();
        LinkedListOps linkedListOps = new LinkedListOps(memMap, nodeInit);

        // Initialize Doubly linked list with a head and tail
        System.out.println("======================================");
        System.out.println("Initializing LRU Cache");
        NodeInit[] headAndTail = nodeInit.initHeadAndTail();




        return head;
    }

}

class LinkedListOps {

    MemMap memMap;
    NodeInit nodeInit;

    public LinkedListOps(MemMap memMap, NodeInit nodeInit) {
        this.memMap = memMap;
        this.nodeInit = nodeInit;
    }

    public void addNodes(int[] values, NodeInit head, NodeInit tail) {
        if (values == null || values.length == 0) {
            return;
        }

        NodeInit ptr = head;

        for (int i = 0; i < values.length; i++) {

            NodeInit newNode = new NodeInit(values[i]); // Create new node with value from the list
            ptr.next = newNode; // Link head to new node
            newNode.prev = ptr; // Link new node to previous node
            newNode.next = tail; // Link new node to tail
            tail.prev = newNode; // Link tail node to new node
            ptr = newNode; // Move pointer to new node

            // Update value in MemMap once a new node is inserted
            memMap.addValAndAddressToMap(values[i], newNode);
        }

        System.out.println("Map Used Capacity: " + memMap.getMapUsedCapacity());
    }

    public int getValueFromNode(int value) {
        NodeInit nodeToGet = memMap.getValueNode(value);
        if (nodeToGet == null) {
            return -1;
        } else {
            return nodeToGet.value;
        }

    }

    public void setNodeValue(int prevValue, int updatedValue) {
        NodeInit nodeInit = memMap.getValueNode(prevValue);
        if (nodeInit != null) {
            System.out.println();
            System.out.println();
            System.out.println("Before update -> " + "Node Value = " + nodeInit.value + " , Prev Pointer = " + nodeInit.prev.value + " , NextPointer = " + nodeInit.next.value);
            memMap.printMap();
            nodeInit.value = updatedValue;
            memMap.setValueNode(updatedValue, prevValue, nodeInit);
            System.out.println("After update -> " + "Node Value = " + nodeInit.value + " , Prev Pointer = " + nodeInit.prev.value + " , NextPointer = " + nodeInit.next.value);
            memMap.printMap();
        } else {
            System.out.println("Node does not exist");
        }
    }

    public void lruCheckForNewNodeInsert() {}

    public void lruCheckForGetAndUpdate(int updatedValue) {
        // update the list using LRU check
        NodeInit updatedNode = memMap.getValueNode(updatedValue); // Latest updated/used node
        NodeInit[] headAndTail = nodeInit.getHeadAndTail();

        // Find the next and prev nodes of updatedNode
        NodeInit updatedNodeNext = updatedNode.next;
        NodeInit updatedNodePrev = updatedNode.prev;

        // Unlink updatedNode
        updatedNode.next = null;
        updatedNodeNext.prev = null;

        // Re-Link the updatedNodeNext and updatedNodePrev
        updatedNodePrev.next = updatedNodeNext;
        updatedNodeNext.prev = updatedNodePrev;

        // pointer to the first node in the list
        NodeInit current = headAndTail[0].next;
        headAndTail[0].next = updatedNode;
        updatedNode.prev = headAndTail[0];
        updatedNode.next = current;
        current.prev = updatedNode;
    }

    // Remove Next and Previous pointers of a node
    public void deleteNode(int value) {
        // delete Node
        NodeInit nodeToDelete = memMap.getValueNode(value);

        NodeInit tempPrev = nodeToDelete.prev;
        NodeInit tempNext = nodeToDelete.next;

        // All valid nodes are middle nodes as first and last node are with value [-1]
        nodeToDelete.prev = null;
        nodeToDelete.next = null;
        tempPrev.next = tempNext;
        tempNext.prev = tempPrev;

        // delete value in map
        memMap.deleteValueNode(value);

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

    public NodeInit getValueNode(int currentValue) {
        return map.get(currentValue);
    }

    public void setValueNode(int newValue, int oldValue, NodeInit nodeInit) {
       map.remove(oldValue);
       map.put(newValue, nodeInit);
    }

    public void deleteValueNode(int valueToDelete) {
        map.remove(valueToDelete);
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
        for (Map.Entry<Integer, NodeInit> entry : map.entrySet()) {
            System.out.print("Key -> " + entry.getKey());
            System.out.println("Value -> " + entry.getValue().value);
        }
    }
}
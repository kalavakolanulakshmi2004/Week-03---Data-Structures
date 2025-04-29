import java.util.*;
class CustomHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int SIZE = 10;
    private LinkedList<Node<K, V>>[] buckets;
    public CustomHashMap() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }
    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }
    public void put(K key, V value) {
        int index = getIndex(key);
        for (Node<K, V> node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node<>(key, value));
    }
    public V get(K key) {
        int index = getIndex(key);
        for (Node<K, V> node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }
    public void remove(K key) {
        int index = getIndex(key);
        Iterator<Node<K, V>> it = buckets[index].iterator();
        while (it.hasNext()) {
            if (it.next().key.equals(key)) {
                it.remove();
                return;
            }
        }
    }
    public void display() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("Bucket " + i + ": ");
            for (Node<K, V> node : buckets[i]) {
                System.out.print("[" + node.key + "=" + node.value + "] ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomHashMap<String, String> map = new CustomHashMap<>();
        while (true) {
            System.out.println("\n1. Put\n2. Get\n3. Remove\n4. Display\n5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    String key = sc.nextLine();
                    System.out.print("Enter value: ");
                    String value = sc.nextLine();
                    map.put(key, value);
                    break;
                case 2:
                    System.out.print("Enter key to get value: ");
                    System.out.println("Value: " + map.get(sc.nextLine()));
                    break;
                case 3:
                    System.out.print("Enter key to remove: ");
                    map.remove(sc.nextLine());
                    break;
                case 4:
                    map.display();
                    break;
                case 5:
                    sc.close();
                    return;
            }
        }
    }
}
import java.util.*;

public class MyHashMap<K, V> {
    private static class Node<K, V> {
        K key; V value; Node<K, V> next;
        Node(K k, V v) { key = k; value = v; }
    }

    private final int SIZE = 997;
    private Node<K, V>[] buckets;

    public MyHashMap() {
        buckets = new Node[SIZE];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> head = buckets[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        Node<K, V> node = new Node<>(key, value);
        node.next = buckets[index];
        buckets[index] = node;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> head = buckets[index];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        Node<K, V> head = buckets[index], prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) prev.next = head.next;
                else buckets[index] = head.next;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        System.out.println(map.get("a"));
        map.remove("a");
        System.out.println(map.get("a"));
    }
}

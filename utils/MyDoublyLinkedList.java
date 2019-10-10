package utils;

public class MyDoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addFirst(T data) {
        if (head == null) {
            head = new Node<>(data);
            tail = head;
            return;
        }

        Node<T> newHead = new Node<>(data);
        newHead.next = head;
        head.prev = newHead;
        head = newHead;
    }

    public void addLast(T data) {
        if (tail == null) {
            tail = new Node<>(data);
            head = tail;
            return;
        }
        Node<T> newTail = new Node<>(data);
        tail.next = newTail;
        newTail.prev = tail;
        tail = newTail;
    }

    public void removeFirst() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            tail = null;
            return;
        }
        head = head.next;
        head.prev = null;
    }

    public void removeLast() {
        if (tail == null) {
            return;
        }
        if (tail.prev == null) {
            tail = null;
            head = null;
            return;
        }
        tail = tail.prev;
        tail.next = null;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node<T> curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.data.equals(data)) {
                curr.next = curr.next.next;
                if (curr.next != null && curr.next.next != null) {
                    curr.next.next.prev = curr;
                }
                return;
            }
            curr = curr.next;
        }
    }

    public boolean find(T data) {
        if (head == null) {
            return false;
        }
        Node<T> curr = head;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return true;
            }
            curr = curr.next;
        }

        return false;
    }

    public void print() {
        if (head == null) {
            return;
        }
        Node<T> curr = head;
        while (curr.next != null) {
            System.out.print(curr.data + "->");
            curr = curr.next;
        }
        System.out.print(curr.data);
        System.out.println();
    }

    public static void main(String[] args) {
        MyDoublyLinkedList list = new MyDoublyLinkedList();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.addLast(5);
        list.print();

        list.removeFirst();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.print();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        System.out.println(list.find(1));
        System.out.println(list.find(2));
        System.out.println(list.find(3));
        System.out.println(list.find(4));

        list.remove(2);
        list.print();
        list.remove(3);
        list.print();
        list.remove(1);
        list.print();
    }


}

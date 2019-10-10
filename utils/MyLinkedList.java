package utils;

import java.util.Deque;
import java.util.LinkedList;

public class MyLinkedList<T> {

    private Node<T> head;

    public void addLast(T data) {
        if (head == null) {
            head = new Node<T>(data);
            return;
        }
        Node<T> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new Node<T>(data);
    }

    public void addFirst(T data) {
        if (head == null) {
            head = new Node<>(data);
            return;
        }
        Node<T> newHead = new Node<>(data);
        newHead.next = head;
        head = newHead;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data) && head.next == null) {
            head = null;
            return;
        }
        Node<T> curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.data.equals(data)) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
    }

    public boolean search(T data) {
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
        while (curr != null && curr.next != null) {
            System.out.print(curr.data + "->");
            curr = curr.next;
        }
        System.out.print(curr.data);
        System.out.println();
    }

    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.print();
        System.out.println(list.search(1));
        System.out.println(list.search(33));

        list.remove(2);
        list.print();
        list.remove(3);
        list.print();
        list.remove(1);
        list.print();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.print();
    }
}

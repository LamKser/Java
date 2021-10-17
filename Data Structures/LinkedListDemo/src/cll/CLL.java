/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cll;

/**
 *
 * @author Dinh Hoang Lam
 */
public class CLL<T extends Comparable<T>> {

    Node<T> head;
    Node<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        int count = 0;
        Node tmp = head;
        do {
            count += 1;
            tmp = tmp.next;
        } while (tmp != head);
        return count;
    }

    public void print() {
        Node tmp = head;
        do {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        } while (tmp != head);
        System.out.println("");
    }

    public void addFirst(T data) {
        Node p = new Node(data);
        if (isEmpty()) {
            head = tail = p;
            tail.next = head;
        } else {
            p.next = head;
            head = p;
            tail.next = head;
        }
    }

    public void addLast(T data) {
        Node p = new Node(data);
        if (isEmpty()) {
            head = tail = p;
            tail.next = head;
        } else {
            tail.next = p;
            tail = p;
            tail.next = head;
        }
    }

    public void addPos(T data, int index) {
        Node p = new Node(data);
        Node tmp = head;
        if (isEmpty()) {
            head = tail = p;
            tail.next = head;
        } else {
            if (index == length() - 1) {
                addLast(data);
            }
            if (index == 0) {
                addFirst(data);
            }
            if (index > 0 && index < length() - 1) {
                for (int i = 0; i < index - 1; i++) {
                    tmp = tmp.next;
                }
                p.next = tmp.next;
                tmp.next = p;
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> tmp = head;
            head = head.next;
            tail.next = head;
            return tmp.data;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> tmp = head;
            Node<T> del = tail;
            while (tmp.next != tail) {
                tmp = tmp.next;
            }
            tail = tmp;
            tail.next = head;
            return del.data;
        }
    }

    public T remove(int index) {
        if (isEmpty()) {
            return null;
        } else if (index >= 0 && index <= length() - 1) {
            if (index == 0) {
                return removeFirst();
            }
            if (index == length() - 1) {
                return removeLast();
            }
            Node<T> tmp = head;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            Node<T> del = tmp.next;
            tmp.next = tmp.next.next;
            return del.data;
        } else {
            return null;
        }
    }

    public T getFirst() {
        return head.data;
    }

    public T getLast() {
        return tail.data;
    }

    public T get(int index) {
        Node<T> tmp = head;
        if (index == 0) {
            return getFirst();
        }
        if (index == length() - 1) {
            return getLast();
        }
        if (index > 0 && index < length() - 1) {
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.data;
        }
        return null;
    }
}

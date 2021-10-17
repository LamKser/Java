/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dll;

/**
 *
 * @author Dinh Hoang Lam
 */
public class DLL<T extends Comparable<T>> {

    private Node<T> head;
    private Node<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        int count = 0;
        if (isEmpty()) {
            return 0;
        }
        Node tmp = head;
        while (tmp != null) {
            count += 1;
            tmp = tmp.next;
        }
        return count;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            Node tmp = head;
            while (tmp != null) {
                System.out.print(tmp.data + " ");
                tmp = tmp.next;
            }
            System.out.println("");
        }
    }

    public void addFirst(T data) {
        Node<T> p = new Node(data);
        if (isEmpty()) {
            head = tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    public void addLast(T data) {
        Node<T> p = new Node(data);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            p.pre = tail;
            tail = p;
        }
    }

    public void addPos(T data, int index) {
        Node<T> p = new Node(data);
        Node tmp = head;
        if (index >= 0) {
            if (index == 0) {
                addFirst(data);
            } else if (index == length() - 1) {
                addLast(data);
            } else if (index > length() - 1) {
                System.out.println("Out of index, can not add!!!");
            } else {
                for (int i = 0; i < index - 1; i++) {
                    tmp = tmp.next;
                }
                tmp.next.pre = p;
                p.pre = tmp;
                p.next = tmp.next;
                tmp.next = p;
            }
        } else {
            System.out.println("Add positive index!!!");
        }
    }

    public void addAfter(T data, int index) {
        Node<T> p = new Node(data);
        Node tmp = head;
        if (index >= 0) {
            if (index > length() - 1) {
                System.out.print("Out of index, can not add!!!");
                return;
            }
            if (index == length() - 1) {
                addLast(data);
            } else {
                for (int i = 0; i < index; i++) {
                    tmp = tmp.next;
                }
                tmp.next.pre = p;
                p.pre = tmp;
                p.next = tmp.next;
                tmp.next = p;
            }
        } else {
            System.out.println("Add positive index!!!");
        }
    }

    public T remove(int index) {
        Node<T> tmp = head;
        if (isEmpty()) {
            return null;
        } else {
            if (index == 0) {
                return removeFirst();
            } else if (index == length() - 1) {
                return removeLast();
            } else if (index > 0 && index < length() - 1) {
                for (int i = 0; i < index; i++) {
                    tmp = tmp.next;
                }
                return tmp.data;
            }
        }
        return null;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> tmp = head;
            head = head.next;
            head.pre = null;
            return tmp.data;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> tmp = tail;
            tail = tail.pre;
            tail.next = null;
            return tmp.data;
        }
    }
}

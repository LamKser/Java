/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sll;

/**
 *
 * @author Dinh Hoang Lam
 */
public class SLL<T extends Comparable<T>> {

    Node<T> head, tail;
//    int size = 0;

    public SLL() {
    }

    public void addLast(T infor) {
        Node<T> last = new Node(infor);
        if (isEmpty()) {
            head = tail = last;
        } else {
            tail.next = last;
            tail = last;
        }
    }

    public void addFirst(T infor) {
        if (isEmpty()) {
            head = tail = new Node(infor);
        } else {
            Node<T> first = new Node(infor);
            first.next = head;
            head = first;
        }
    }

    public void addMany(T a[]) {
        for (int i = 0; i < a.length; i++) {
            addFirst(a[i]);
//            size++;
        }
    }

    public void addAfter(T infor, int index) {
        Node<T> after = new Node(infor);
        if (isEmpty()) {
            head = tail = after;
        } else if (index >= 0 && index <= size() - 1) {
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            after.next = temp.next;
            temp.next = after;
        } else {
            System.out.println("Out of list size");
        }
//        size++;
    }

    public void addPosition(T infor, int position) {
        if (isEmpty()) {
            head = tail = new Node(infor);
        } else if (position >= 0 && position <= size() - 1) {
            if (position == 0) {
                addFirst(infor);
            } else if (position == size() - 1) {
                addLast(infor);
            } else {
                Node<T> pos = new Node(infor);
                Node<T> temp = head;
                for (int i = 0; i < position - 1; i++) {
                    temp = temp.next;
                }
                pos.next = temp.next;
                temp.next = pos;
            }
        } else {
            System.out.println("Out of list size");
        }
    }

    public Node search(T infor) {
        Node<T> tmp = head;
        while (tmp != null && infor.compareTo(tmp.infor) != 0) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public T remove(int index) {
        Node<T> tmp = head;
        T delete = null;
        if (index < 0) {
            return null;
        } else {
            if (index == (size() - 1)) {
                delete = removeLast();
            } else if (index == 0) {
                delete = removeFirst();
            } else {
                for (int i = 0; i < index - 1; i++) {
                    tmp = tmp.next;
                }
                delete = (T) tmp.next.infor;
                tmp.next = tmp.next.next;
                //        size--;
            }
        }
        return delete;
    }

    public T removeLast() {
        Node<T> tmp = head;
        Node<T> delete = null;
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            while (tmp.next != tail) {
                tmp = tmp.next;
            }
            delete = tmp.next;
            tail = tmp;
            tail.next = null;
        }
        //        size--;
        return delete.infor;
    }

    public T removeFirst() {
        Node<T> delete = null;
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            delete = head;
            head = head.next;
        }
        //        size--;
        return delete.infor;
    }

    public T get(int index) {
        Node<T> tmp = head;
        if (index < 0) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
        }
        return tmp.infor;
    }

    public void set(T infor, int index) {
        Node<T> newNode = new Node(infor);
        Node<T> tmp = head;
        if (index == 0) {
            newNode.next = head.next;
            head = newNode;
        } else if (index == (size() - 1)) {
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
            tail = newNode;
        } else {
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            newNode.next = tmp.next.next;
            tmp.next = newNode;

        }
    }

    public int size() {
        int size = 0;
        Node tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void showData() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.infor + " ");
            temp = temp.next;
        }
    }

}

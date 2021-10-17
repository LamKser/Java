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
public class Node<T> {

    T infor;
    Node next;

    public Node() {
    }

    public Node(T infor) {
        this.infor = infor;
        this.next = null;
    }
}

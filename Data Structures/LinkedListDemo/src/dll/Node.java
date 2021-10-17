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
public class Node<T> {
    T data;
    Node next;
    Node pre;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.pre = null;
    }  
}

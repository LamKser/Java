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
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SLL<Integer> sll = new SLL();
        sll.addFirst(10);
        sll.addFirst(12);
        sll.addFirst(13);
        sll.addFirst(9);
        sll.addAfter(4, 3);
        sll.showData();
    }
    
}

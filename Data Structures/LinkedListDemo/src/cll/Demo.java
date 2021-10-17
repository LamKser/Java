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
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CLL<Integer> cll = new CLL();
        cll.addFirst(6);
        cll.addFirst(3);
        cll.addFirst(7);
        cll.addLast(5);
        cll.addPos(4, 2);
        cll.print();
//        System.out.println(cll.removeFirst());
//        System.out.println(cll.removeLast());
//        System.out.println(cll.remove(1));
        System.out.println(cll.getFirst());
        System.out.println(cll.getLast());
        System.out.println(cll.get(1));
        cll.print();
    }

}

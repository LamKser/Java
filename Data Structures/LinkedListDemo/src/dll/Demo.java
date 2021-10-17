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
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DLL dll = new DLL();
        dll.addFirst(3);
        dll.addFirst(4);
        dll.addFirst(1);
        dll.addLast(6);
        dll.addPos(7, 2);
        dll.addAfter(5, 2);
        System.out.println(dll.remove(0));
        dll.print();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

/**
 *
 * @author Dinh Hoang Lam
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InjectionList list = new InjectionList();
        list.addFromFile("injection.dat", true);
        int choice;
        do {
            System.out.println("1. Show information all students have been injected");
            System.out.println("2. Add student's vaccine injection information");
            System.out.println("3. Updating information of students' vaccine injection");
            System.out.println("4. Delete student vaccine injection information");
            System.out.println("5. Search for injection information by studentID");
            System.out.println("6. Save injection information to file");
            System.out.println("Others- Quit");
            choice = Utils.getInt("Enter your choice: ");
            switch(choice) {
                case 1: 
                    list.showInjected();
                    break;
                case 2: 
                    list.addInjection();
                    break;
                case 3: 
                    list.updateInjection();
                    break;
                case 4: 
                    list.deleteInjection();
                    break;
                case 5: 
                    list.searchInjection();
                    break;
                case 6: 
                    list.saveToFile("injection.dat");
                    break;
                default: 
                    System.out.println("Quit");
                    break;
            }
        }while(choice >=1 && choice <= 7);
    }

}

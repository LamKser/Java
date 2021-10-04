/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.crypto.Cipher;

/**
 *
 * @author Dinh Hoang Lam
 */
public class InjectionList extends ArrayList<Injection> {

    public void showInjected() {
        if (this.isEmpty()) {
            System.out.println("Injection list is empty!!!");
        } else {
            for (Injection injection : this) {
                System.out.println(injection.toString());
            }
            System.out.println("-----------------------");
        }
    }

    public void addInjection() {
        StudentList studentList = new StudentList();
        VaccineList vaccineList = new VaccineList();

        studentList.addStudentFromFile();
        vaccineList.addVaccineFromFile();
        do {
            String injectionID = null;
            String studentID = null;
            String studentName = null;
            String vaccineID = null;
            String firstPlace = null;
            LocalDate firstDate = null;
            String secondPlace = null;
            LocalDate secondDate = null;

            if (this.isEmpty()) {
                injectionID = Utils.getString("Enter Injection ID: ");
            } else {
                do {
                    injectionID = Utils.getString("Enter Injection ID: ");
                    if (checkInjectionID(injectionID)) {
                        System.out.println("Duplicate Injection ID!!!");
                    }

                } while (checkInjectionID(injectionID));
            }

            studentList.showStudent();
            do {
                studentID = Utils.getString("Enter Student ID: ").toUpperCase();
                if (checkStudentID(studentID)) {
                    System.out.println("Duplicate Student ID!!!");
                }
                if (checkExistStudent(studentID)) {
                    System.out.println("Student ID does not exist!!!");
                }
            } while (checkStudentID(studentID) || checkExistStudent(studentID));

            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getStudentID().equals(studentID)) {
                    studentName = studentList.get(i).getStudentName();
                }
            }

            vaccineList.showVaccine();
            do {
                vaccineID = Utils.getString("Enter vaccine ID: ");
                if (checkExistVaccine(vaccineID)) {
                    System.out.println("Vaccine ID does not exist!!!");
                }
            } while (checkExistVaccine(vaccineID));

            firstPlace = Utils.getString("Enter 1st place: ");

            firstDate = Utils.getDate("Enter 1st date (yyyy-mm-dd): ");

            if (Utils.getConfirm("Do you want to add 2nd information (Yes/No)? ")) {

                String checkDoseType;
                do {
                    checkDoseType = Utils.getString("Enter 2nd vaccine ID: ");
                    if (!checkDoseType.equals(vaccineID)) {
                        System.out.println("The 1st dose and 2nd dose must have the same type!!!");
                    }
                } while (!checkDoseType.equals(vaccineID));

                secondPlace = Utils.getString("Enter 2nd place: ");

                long checkDate;
                do {
                    secondDate = Utils.getDate("Enter 2nd date (yyyy-mm-dd): ");
                    checkDate = DAYS.between(firstDate, secondDate);
                    if ((checkDate < 28 || checkDate > 84)) {
                        System.out.println("The 2nd dose must be 4 to 12 weeks");
                    }
                } while ((checkDate < 28 || checkDate > 84));
            }
            Injection injection = new Injection(injectionID, studentID, vaccineID, firstPlace, firstDate, secondPlace, secondDate);
            this.add(injection);
        } while (Utils.getConfirm("Do you want to continue adding (Yes/No)? "));
    }

    public void updateInjection() {
        if (this.isEmpty()) {
            System.out.println("The injection information is empty!!!");
        } else {
            String injectionID = Utils.getString("Enter injection ID you want to update: ");
            Injection injection = searchInjectionID(injectionID);
            if (injection == null) {
                System.out.println("Injection does not exist");
            } else {
                LocalDate newSecondDate = null;
                String newSecondPlace = null;
                boolean check = false;
                long checkDate;
                do {
                    newSecondDate = Utils.getDate("Enter new 2nd date (yyyy-mm-dd): ");
                    checkDate = DAYS.between(injection.getFirstDate(), newSecondDate);
                    if ((checkDate < 28 || checkDate > 84)) {
                        System.out.println("The 2nd dose must be 4 to 12 weeks");
                    }
                } while ((checkDate < 28 || checkDate > 84));
                newSecondPlace = Utils.getString("Enter new 2nd place: ");
                injection.setSecondDate(newSecondDate);
                injection.setSecondPlace(newSecondPlace);

                System.out.println("--------------After updating--------------");
                System.out.println(injection.toString());
                if ((injection.getFirstDate() != null && injection.getFirstPlace() != null)
                        && (injection.getSecondDate() != null && injection.getSecondPlace() != null)) {
                    System.out.println("Student has completed 2 injections!");
                }
            }
        }
    }

    public void deleteInjection() {
        boolean check = false;
        if (this.isEmpty()) {
            System.out.println("Injection list is empty");
        } else {
            String studentID = Utils.getString("Enter Student ID you want to remove: ");
            if (Utils.getConfirm("Do you want to delete (Yes/No)? ")) {
                Injection injection = searchStudentID(studentID);
                if (injection == null) {
                    System.out.println("Injection information does not exist!!!");
                } else {
                    this.remove(injection);
                    check = true;
                }
            }
        }
        if (check) {
            System.out.println("Remove success!!!");
        } else {
            System.out.println("Removed fail!!!");
        }
    }

    public void searchByStudentID() {
        if (this.isEmpty()) {
            System.out.println("Injection information list is empty");
        } else {
            String studentID = Utils.getString("Enter Student ID you want to search: ");
            Injection inject = this.searchStudentID(studentID);
            if (inject == null) {
                System.out.println("The injection information is not exist!!!");
            } else {
                System.out.println(inject.toString());
            }
        }
    }

    public void searchByStudentName() {
        if (this.isEmpty()) {
            System.out.println("Injection information list is empty");
        } else {
            StudentList list = new StudentList();
            list.addStudentFromFile();
            list.showStudent();
            String studentName = Utils.getString("Enter Student Name you want to search: ");
            boolean check = true;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getStudentName().contains(studentName)) {
                    String id = list.get(i).getStudentID();
                    for (int j = 0; j < this.size(); j++) {
                        if (this.get(j).getStudentID().equals(id)) {
                            System.out.println(this.get(j).toString() );
                            check = false;
                        }
                    }
                }
            }
            if (check) {
                System.out.println("Not found");
            } else {
                System.out.println("Found");
            }
        }
    }

    public void searchInjection() {

        int choice;
        do {
            searchSubMenu();
            choice = Utils.getInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    searchByStudentID();
                    break;
                case 2:
                    searchByStudentName();
                    break;
                case 3:
                    System.out.println("Return main menu");
                    break;
                default:
                    System.out.println("Enter 1 to 3");
                    break;
            }
        } while (choice != 3);
    }

    public void saveToFile(String fileName) {
        try {
            FileOutputStream fo = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fo);
            for (Injection injection : this) {
                os.writeObject(injection);
            }
            fo.close();
            os.close();
        } catch (Exception e) {
            System.out.println("Save file fail!!!");
        }
    }

    public void addFromFile(String fileName, boolean check) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream os = new ObjectInputStream(fi);
                while (fi.available() > 0) {
                    Injection injection = (Injection) os.readObject();
                    if (check) {
                        this.add(injection);
                    } else {
                        System.out.println(injection.toString());
                    }
                }
                os.close();
                fi.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private Injection searchStudentID(String studentID) {
        Injection injection = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getStudentID().equals(studentID)) {
                injection = this.get(i);
            }
        }
        return injection;
    }

    private boolean checkStudentID(String studentID) {
        boolean check = true;
        Injection stuID = searchStudentID(studentID);
        if (stuID == null) {
            check = false;
        }
        return check;
    }

    private Injection searchInjectionID(String injectionID) {
        Injection injection = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getInjectionID().equals(injectionID)) {
                injection = this.get(i);
            }
        }
        return injection;
    }

    private boolean checkInjectionID(String injectionID) {
        boolean check = true;
        Injection injectID = searchInjectionID(injectionID);
        if (injectID == null) {
            check = false;
        }
        return check;
    }

    private boolean checkExistStudent(String studentID) {
        StudentList studentList = new StudentList();
        studentList.addStudentFromFile();
        boolean check = true;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID().equals(studentID)) {
                check = false;
            }
        }
        return check;
    }

    private boolean checkExistVaccine(String vaccineID) {
        VaccineList vaccineList = new VaccineList();
        vaccineList.addVaccineFromFile();
        boolean check = true;
        for (int i = 0; i < vaccineList.size(); i++) {
            if (vaccineList.get(i).getVaccineID().equals(vaccineID)) {
                check = false;
            }
        }
        return check;
    }

    private void searchSubMenu() {
        System.out.println("1. Search by Student ID");
        System.out.println("2. Search by Student Name");
        System.out.println("3. Return to main menu");
    }

    private byte[] convertByte(Injection injection) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(injection);
            os.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return out.toByteArray();
    }

    private Injection convertObject(byte[] data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        Injection injection = new Injection();
        try {
            ObjectInputStream is = new ObjectInputStream(in);
            is.close();
            in.close();
            injection = (Injection) is.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return injection;
    }

    private byte[] encryption(Injection injection, PublicKey publicKey) {
        byte[] encryptedMessageBytes = null;
        try {
            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] secretMessageBytes = convertByte(injection);
            encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        } catch (Exception e) {
            System.out.println(e);
        }
        return encryptedMessageBytes;
    }

    private Injection decryption(byte[] encryptedMessageBytes, PrivateKey privateKey) {
        Injection injection = new Injection();
        try {
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
            injection = convertObject(decryptedMessageBytes);
        } catch (Exception e) {
            System.out.println(e);
        }
        return injection;
    }

    public KeyPair getkey() {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {
            System.out.println(e);
        }
        generator.initialize(2550);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

    private void savePublicKey(PublicKey publicKey) {
        try {
            FileOutputStream fos = new FileOutputStream("public.txt");
            fos.write(publicKey.getEncoded());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void savePrivateKey(PrivateKey privateKey) {
        try {
            FileOutputStream fos = new FileOutputStream("private.txt");
            fos.write(privateKey.getEncoded());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PublicKey readPublicKey(String file) {
        PublicKey key = null;
        try {
            File publicKeyFile = new File(file);
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey pubKey = keyFactory.generatePublic(publicKeySpec);
            key = pubKey;
        } catch (Exception e) {
            System.out.println(e);
        }
        return key;
    }

    public PrivateKey readPrivateKey(String file) {
        PrivateKey key = null;
        try {
            File privateKeyFile = new File(file);
            byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey priKey = keyFactory.generatePrivate(privateKeySpec);
            key = priKey;
        } catch (Exception e) {
            System.out.println(e);
        }
        return key;
    }

    public void saveEncryption(String fileName, PublicKey publicKey) {
        System.out.println("-----------After encrypt-----------");
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for (Injection injection : this) {
                byte[] encryp = encryption(injection, publicKey);
                String f = Base64.getEncoder().encodeToString(encryp);
                System.out.println(f);
                pw.println(f);
            }
            pw.close();
            fw.close();
            System.out.println("Ecrypt and save success!!!");
        } catch (Exception e) {
            System.out.println("Save encryption fail!!!");
        }
        System.out.println("-----------------------------------");
    }

    public void readEcryption(String fileName, PrivateKey privateKey) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String decrypt;
                while ((decrypt = br.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(decrypt);
                    String str = stk.nextToken();
                    byte[] d = Base64.getDecoder().decode(str);
                    Injection injection = new Injection();
                    injection = decryption(d, privateKey);
                    System.out.println(injection.toString());
                }
                br.close();
                fr.close();
                System.out.println("-----------------------");
            } else {
                System.out.println("File does not exist");
            }
        } catch (Exception e) {
            System.out.println("Read fail!!!");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Dinh Hoang Lam
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        // TODO code application logic here
//        InjectionList list = new InjectionList();
//        list.addFromFile("injection.dat", true);
////        list.showInjected();
//
//        KeyPair pair = list.getkey();
//        PrivateKey privateKey = pair.getPrivate();
//        PublicKey publicKey = pair.getPublic();
//
//        for (Injection e : list) {
//            byte[] a = list.encryption(e, publicKey);
////            System.out.println(f);
//            Injection l = new Injection();
//            l = list.decryption(a, privateKey);
//        }
//        list.saveEncryption("encr.txt", publicKey);
//        System.out.println("=--------------");
//        list.readEcryption("encr.txt", privateKey);
//        System.out.println("----------------");
    }

}

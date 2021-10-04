/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
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
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException {
//        // TODO code application logic here
//        InjectionList list = new InjectionList();
//        list.addFromFile("injection.dat", true);
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//        generator.initialize(2550);
//        KeyPair pair = generator.generateKeyPair();
//        PrivateKey privateKey = pair.getPrivate();
//        PublicKey publicKey = pair.getPublic();
//        System.out.println("Orgin: " + privateKey.getFormat());
//        System.out.println("Orgin: " + publicKey.getFormat());
//        savePublicKey(publicKey);
//        savePrivateKey(privateKey);
//        System.out.println("========================");
//        PrivateKey pri = readPrivateKey();
//        PublicKey pub = readPublicKey();
//        System.out.println("Read: " + pri.getFormat());
//        System.out.println("Read: " + pub.getFormat());

        LocalDate expDate = LocalDate.parse("2021-12-12", DateTimeFormatter.ISO_DATE);
        LocalDate p = null;
        long checkDate = DAYS.between(expDate, p);
        System.out.println(checkDate);
    }

    public static void savePublicKey(PublicKey publicKey) {
        try {
            FileOutputStream fos = new FileOutputStream("public.txt");
            fos.write(publicKey.getEncoded());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void savePrivateKey(PrivateKey privateKey) {
        try {
            FileOutputStream fos = new FileOutputStream("private.txt");
            fos.write(privateKey.getEncoded());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static PublicKey readPublicKey() {
        PublicKey key = null;
        try {
            File publicKeyFile = new File("public.txt");
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
            key = pubKey;
        } catch (Exception e) {
            System.out.println(e);
        }
        return key;
    }

    public static PrivateKey readPrivateKey() {
        PrivateKey key = null;
        try {
            File privateKeyFile = new File("private.txt");
            byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            RSAPrivateKey pubKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
            key = pubKey;
        } catch (Exception e) {
            System.out.println(e);
        }
        return key;
    }

}

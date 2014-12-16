package th.go.motorcycles.app.enjoy.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class EnjoyEncryptDecrypt {
    private static String md5(String input) throws Exception {
        MessageDigest	md 				= null;
        byte[] 			messageDigest 	= null;
        BigInteger 		number 			= null;
        String			md5				= null;
        
        try{
        	md 				= MessageDigest.getInstance("MD5");
        	messageDigest 	= md.digest(input.getBytes());
        	number 			= new BigInteger(1, messageDigest);
        	md5				= String.format("%032x", number);
        	
        }catch(Exception e){
        	throw new Exception(e.getMessage());
        }
        
        return md5;
    }
    
    public static String enCryption(String userId, String pass) {
        String encryptedData 		= null;
        
        try {
        	encryptedData 	= md5(userId + "%" + pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    public String encrypt(String dataToEncrypt) {
        String encryptedData 		= null;
        byte[] encodedBytes 		= null;
        
        try {
        	encodedBytes 	= Base64.encodeBase64(dataToEncrypt.getBytes());
        	encryptedData 	= new String(encodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    public String decrypt(String encryptedData) {
        String decryptedData = null;
        byte[] decodedBytes  = null;
        
        try {
        	decodedBytes 		= Base64.decodeBase64(encryptedData);
        	decryptedData		= new String(decodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedData;
    }

}

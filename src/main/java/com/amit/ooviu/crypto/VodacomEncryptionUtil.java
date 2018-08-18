package com.amit.ooviu.crypto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.amit.ooviu.controller.OmanController;


/**
* @author Rajeev Kumar
*
*/
@Component
public class VodacomEncryptionUtil {
	
private static final Logger LOGGER = LoggerFactory.getLogger(OmanController.class);
	
	 @Autowired
	 private ResourceLoader resourceLoader;
	 private static RSAPrivateKey rsaPrivateKey;
	 
	

	
	@PostConstruct
	 public void init() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		BufferedReader br=null;
		String privateKey = "";
        // Read key from file
        try {
	        Resource resource = resourceLoader.getResource("classpath:dcb_viu_prvkey.pem");
	        InputStream fileStream = resource.getInputStream(); 
	         // Initialize the reader
	         br = new BufferedReader(new InputStreamReader(fileStream));
	        String line;
	        while ((line = br.readLine()) != null) {
	        	privateKey += line ;
	        }
	        LOGGER.info("Key found in pem file : "+privateKey);
	         // Remove the first and last lines
	        privateKey= privateKey.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("\n", "");
	        privateKey = privateKey.replace("-----END RSA PRIVATE KEY-----", "");
	        // Base64 decode data
	        byte[] encoded = Base64.decodeBase64(privateKey);
	        KeyFactory kf = KeyFactory.getInstance("RSA");
	        rsaPrivateKey=(RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
	    } catch (IOException | NullPointerException  e) {
            LOGGER.info("Exception while reading pem file" +e);
        }finally {
        	br.close();
        }
        
    }
	
	public static String decrypt(String cipherText) throws IOException, GeneralSecurityException {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		String decryptedMsisdn="";
		if(cipherText!=null && !cipherText.isEmpty()) {
		   PrivateKey privateKey =rsaPrivateKey;
	  	   Cipher cipher = Cipher.getInstance("RSA");
	       cipher.init(Cipher.DECRYPT_MODE, privateKey);
	       decryptedMsisdn=new String(cipher.doFinal(DatatypeConverter.parseHexBinary(cipherText)));
	       
		}
		LOGGER.info("decryptedMsisdn :" +decryptedMsisdn);
	   return decryptedMsisdn ;
  }
	
	

    



}

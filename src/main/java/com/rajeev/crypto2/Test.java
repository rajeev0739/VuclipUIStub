package com.rajeev.crypto2;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

public class Test {

private static String RSA_PRIVATE_KEY="-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEAuCSDkSfJg+NQqkAM6DyXiUCYsKYX2i6O5cCnM+YQrg/nJ7/q\n" + 
			"88NArhwFwE2yhWubiDillXaKmP2i3xjXnXJDtOb+MCWUJCoiAs2CuDDBOB+28FNd\n" + 
			"LbEoXOQ5jc3ABwuF2y6DmTdSVJ8dVxp+aQfuFF306By+eNZvHnkWoNpxcu+zrYzx\n" + 
			"+Bv0NG9BU5CjXlQaoUpUGdcs9P1Va73pcsXCx1SS4hd1nfRs70MAqLAB3UGoO4TC\n" + 
			"iGzh7boQVxmBB+bvw95IVR3epxmMm4NhxYkBR+HyNk/ygbfFTZb0F9aohKqmyn3X\n" + 
			"AtCYUzMH+IDgSFhRaCqsxhtNZzsuLhtlruVuxwIDAQABAoIBACNypAElb517/ONJ\n" + 
			"QTLTtzgsvzgCQUU3ATspQwaymzNmzFDSmSAjzie2LUU/r8YPWPl4Lif/kd6xtNDN\n" + 
			"r3QXswD4FqaeFU6A7dssRKXAfPGY6VaKNb2+BM8dABdULEsu5K203pB4Hdk8aCGE\n" + 
			"cCE37ZTuIV8taV1JqkprdPeyFXTSa/CvE+mkrwrXJApi/HTo4ejyEwNt9v+bUqAc\n" + 
			"xYWvcdDt1/4v0ly/pOeaCcWwBXW9fOf/TnlK16fowAIjmFKy1M7U5bEq3HqqMMfS\n" + 
			"/zEXwMe/12lwcNEZ+rJFddn97bxeR9yZOc0i4wd4D1iGGv88JAHFwzhp7Zvf6VZM\n" + 
			"VyDeosECgYEA8OvBIVyhCpxqLbcZ2o12TqekObufpoSJ21VFuG617td8h9wWG9nt\n" + 
			"QY2f2iqJNc8pjW99rFnk32AivbcGIju946h34Bn3b4IZxAic6cJTL5to/vj8UF5p\n" + 
			"sE/9CIWLBmrr2bgWjYRInCnUdo4d3b6mnOMGms1VpKO9e+eK79PuTI0CgYEAw6sC\n" + 
			"GnRYe92yokV5FKC1EzPYgiZas8eTvGmYtMflyynNzB5b6+9+IFunBLujuuSjqGci\n" + 
			"EKZ4FQDW9YyvUqg4ep9XtJEs+apNTxuCKqimRzabDqXfW1RF42SEfVusttUouBKL\n" + 
			"Mf0384KXFYsRSOHb2Jm4qpowg8vSAQsyhX73taMCgYEAzoq/GxPneEpaePDFuGmY\n" + 
			"qJvuHhStLTvpV+FGEXTQgn+44lybk6sETTPWfFoFId2FmRyhGDZFEf/AKPcUyhp/\n" + 
			"lAIlKzn3uGBIKJjdtUYEvQa2QAlDfr9CSoZ4vmqGoqzdn9zDcPWcrkzqOe1iy20c\n" + 
			"N9omGma46pn545TAtxTw8i0CgYByUzTDaD9bAEojbhw05UFnmt9iDC7q5C155OkI\n" + 
			"l9O4RRCttv9Wd3RmS6bmqQwdYwIMn1XUDLAU9YyjpDO8Q/XcD8YbBsgUiGDrW9NO\n" + 
			"QYQGD+3BPUGoBzFej5ip9iqQGTPVeWBDMQTEDwVf3QWYY/8kK40ugTDfkPN/EJ/Q\n" + 
			"SENLqQKBgQCJ9SHZny0gz85TpTrS0DV9c+4/9QVoxVfVMK7X92o+EMeQyI/xfRvD\n" + 
			"nDD85QUH+AS5zag7EHMA7206DBT3vgLrthKr7/G0C/JB5y3h7myR5puhr99xAvdi\n" + 
			"T8mNptwJzqHomPD9M6FP1QTiGqDnpBzFXi7I3hpAVlpooXX/xQesWA==\n" + 
			"-----END RSA PRIVATE KEY-----";


	public static String decrypt(String cipherText) throws IOException, GeneralSecurityException {
		
	 PrivateKey privateKey =getPrivateKey();
  	// PrivateKey privateKey =getPrivateKeyFromString(RSA_PRIVATE_KEY);
  	
       Cipher cipher = Cipher.getInstance("RSA");
       cipher.init(Cipher.DECRYPT_MODE, privateKey);
       ;
       return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(cipherText)));
   }

	public  static RSAPrivateKey getPrivateKey() throws IOException, GeneralSecurityException {
		
		File privKeyFile = new File("/home/rajeev/Documents/dcb_viu_prvkey/dcb_viu_prvkey.pem");

		// read private key DER file
		DataInputStream dis = new DataInputStream(new FileInputStream(privKeyFile));
		byte[] privKeyBytes = new byte[(int) privKeyFile.length()];
		dis.read(privKeyBytes);
		dis.close();
		String privKeyPEM=new String(privKeyBytes);
		privKeyPEM= privKeyPEM.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("\n", "");
		// Remove the first and last lines
		privKeyPEM = privKeyPEM.replace("-----END RSA PRIVATE KEY-----", "");
		System.out.println(privKeyPEM);
        // Base64 decode the data
		String privateKeyPEM =privKeyPEM ;
        return getPrivateKeyFromString(privateKeyPEM);
   }

	public static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
       String privateKeyPEM = key;
       
       // Remove the first and last lines
       privateKeyPEM = privateKeyPEM.replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("\n", "");;
       privateKeyPEM = privateKeyPEM.replace("-----END RSA PRIVATE KEY-----", "");
       
       // Base64 decode data
       byte[] encoded = Base64.decodeBase64(privateKeyPEM);
       
       KeyFactory kf = KeyFactory.getInstance("RSA");
       return (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
        
   }
	
	public static void main(String[] args) {
		String encryptedString = "549847055bef55aa57e9c548547a9781b75325c936137c91f16f17f6a6c7cf2174ef39e3a0ef92fc50f853e4eca3b1a141cc8a7c66982edfebd1bb2ab7052f6a654c03f3bf51728dc476dc490c66d7f5377ca02a74c017fd8bf131c24448778e54c247d70b0db2d6c0045bae6f8b866e5219f5b63fad3e03fcdd2bf2d7379a019bdece66aada141aae63067c717738744db729f4909a6899f4abae17858539c1520cbb7d40ab94e33b8e562e5e30960956cee8e09503561881319f95f38e2fd3234ee9040fcdb756d26aac611520973033e5484a95f5fba3928ef62afc1cafcf60542d257a88b2a29898459ff15efcdf8175714cd561e8fe63345ddc913e82c6";
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		try {
			System.out.println(decrypt(encryptedString));
		} catch (Exception e) {
			
			System.out.println(e);
		} 
		
	}

}

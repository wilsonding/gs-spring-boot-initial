package hello;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainClass {
	public static void main(String[] args) throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyGenerator generator = KeyGenerator.getInstance("AES", "BC");
		generator.init(128);
		Key keyToBeWrapped = generator.generateKey();
		System.out.println("input    : " + new String(keyToBeWrapped.getEncoded()));

		// create a wrapper and do the wrapping
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");
		keyGen.init(128);
		Key wrapKey = keyGen.generateKey();
		cipher.init(Cipher.ENCRYPT_MODE, wrapKey);
		byte[] wrappedKey = cipher.doFinal(keyToBeWrapped.getEncoded());
		System.out.println("wrapped  : " + new String(wrappedKey));

		// unwrap the wrapped key
		cipher.init(Cipher.DECRYPT_MODE, wrapKey);
		Key key = new SecretKeySpec(cipher.doFinal(wrappedKey), "AES");
		System.out.println("unwrapped: " + new String(key.getEncoded()));
	}
}
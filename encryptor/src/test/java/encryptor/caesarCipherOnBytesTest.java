package encryptor;

import static org.junit.Assert.*;

import org.junit.Test;

public class caesarCipherOnBytesTest {

	@Test
	public void test() {
		byte b, key;
		b = 50;
		key = 51;
		assertTrue(Encryptor.caesarCipherOnBytes(b, key,1) == 101);
		b = 0;
		key = 127;
		assertTrue(Encryptor.caesarCipherOnBytes(b, key,1) == 127);
		b = 1;
		key = 127;
		System.out.println(Encryptor.caesarCipherOnBytes(b, key,1));
		assertTrue(Encryptor.caesarCipherOnBytes(b, key,1) == -128);
		b = -128;
		key = -1;
		System.out.println(Encryptor.caesarCipherOnBytes(b, key,1));
		assertTrue(Encryptor.caesarCipherOnBytes(b, key,1) == 127);
		
		
	}

}

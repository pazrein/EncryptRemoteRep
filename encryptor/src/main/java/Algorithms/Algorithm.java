package Algorithms;

import java.io.IOException;

public interface Algorithm {

	byte[] encrypt(byte key, byte[] array) throws IOException;

	byte[] decrypt(byte key, byte[] array);

	byte encryptPerByte(byte b, byte k);

	byte decryptPerByte(byte b, byte k);

}

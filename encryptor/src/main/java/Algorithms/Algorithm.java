package Algorithms;

import java.io.IOException;

public interface Algorithm {

	byte[] encrypt(byte[] array, byte key) throws IOException;

	byte[] decrypt(byte[] array,byte key) throws IOException;
}

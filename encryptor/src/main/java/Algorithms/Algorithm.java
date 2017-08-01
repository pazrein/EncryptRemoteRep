package Algorithms;

import java.io.IOException;

public interface Algorithm {

	byte[] encrypt(byte[] array) throws IOException;

	byte[] decrypt(byte[] array) throws IOException;
}

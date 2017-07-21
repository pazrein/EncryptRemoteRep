package Algorithms;

import java.io.IOException;

public class Multiplication extends AlgorithmAbstract {

	@Override
	public byte[] encrypt(byte key, byte[] array) throws IOException {
		if (key % 2 == 0) {
			//throw new IllegalArgumentException("The chosen key was illegal for this algorithm.");
			key++;
			System.out.println("The chosen key was illegal for this algorithm." + "\n The new key is: " + key);
		}

		writeKeysToFile(key, (byte) 0, 0, 0);

		return super.encrypt(key, array);
	}

	@Override
	public byte[] decrypt(byte key, byte[] array) {
		for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
			if (decryptPerByte((byte) i, key) == (byte) 1) {
				key = (byte) i;
				break;
			}
		}
		return super.decrypt(key, array);
	}

	@Override
	public byte encryptPerByte(byte b, byte key) {
		return (byte) (b * key);
	}

	@Override
	public byte decryptPerByte(byte b, byte key) {
		return encryptPerByte(b, key);
	}

}

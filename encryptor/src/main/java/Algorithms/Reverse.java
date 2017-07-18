package Algorithms;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;

public class Reverse extends AlgorithmAbstract {

	@Override
	public byte[] encrypt(byte key, byte[] array) throws IOException {
		int algo;

		algo = chooseAlgorithmRandomly();
		key = generateRandomByte();
		ArrayUtils.reverse(array);

		array = encAlgo(key, algo, array);

		writeKeysToFile(key, (byte) 0, algo, 0);// need to be the last line
		return array;

	}

	public byte[] decrypt(byte key, int algo, byte[] array) {
		array = decAlgo(key, algo, array);
		ArrayUtils.reverse(array);
		return array;
	}

	@Override
	public byte encryptPerByte(byte b, byte key) {
		// NOT USED
		return 0;
	}

	@Override
	public byte decryptPerByte(byte b, byte key) {
		// NOT USED
		return 0;
	}

}

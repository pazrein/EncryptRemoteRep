package Algorithms;

import java.io.IOException;

public class DoubleAlgo extends AlgorithmAbstract {

	@Override
	public byte[] encrypt(byte key, byte[] array) throws IOException {
		int algo1, algo2;
		byte key1, key2;

		algo1 = chooseAlgorithmRandomly();
		key1 = generateRandomByte();
		algo2 = chooseAlgorithmRandomly();
		key2 = generateRandomByte();

		array = encAlgo(key1, algo1, array);
		array = encAlgo(key2, algo2, array);

		writeKeysToFile(key1, key2, algo1, algo2);// need to be the last line
		return array;
	}

	public byte[] decrypt(byte key1, byte key2, int algo1, int algo2, byte[] array) {
		array = decAlgo(key1, algo1, array);
		array = decAlgo(key2, algo2, array);
		return array;
	}

	@Override
	public byte encryptPerByte(byte b, byte key) {
		// Not used
		return 0;
	}

	@Override
	public byte decryptPerByte(byte b, byte key) {
		// Not used
		return 0;
	}

}

package Algorithms;

import java.io.IOException;
import java.util.Arrays;

public class Split extends AlgorithmAbstract {
	@Override
	public byte[] encrypt(byte key, byte[] array) throws IOException {
		int algo1, algo2;
		byte key1, key2;
		byte[] array1, array2, tempArray;

		algo1 = chooseAlgorithmRandomly();
		key1 = generateRandomByte();
		algo2 = chooseAlgorithmRandomly();
		key2 = generateRandomByte();

		tempArray = Arrays.copyOf(array, array.length);
		array1 = encAlgo(key1, algo1, tempArray);
		tempArray = Arrays.copyOf(array, array.length);
		array2 = encAlgo(key2, algo2, tempArray);

		buildArray(array, array1, array2);

		writeKeysToFile(key1, key2, algo1, algo2);// need to be the last line
		return array;
	}

	public byte[] decrypt(byte key1, byte key2, int algo1, int algo2, byte[] array) {
		byte[] array1, array2, tempArray;
		tempArray = Arrays.copyOf(array, array.length);
		array1 = decAlgo(key1, algo1, tempArray);
		tempArray = Arrays.copyOf(array, array.length);
		array2 = decAlgo(key2, algo2, tempArray);
		buildArray(array, array1, tempArray);
		return array;
	}

	byte[] buildArray(byte[] array, byte[] array1, byte[] array2) {
		for (int i = 0; i < array.length; i++) {
			if (i % 2 != 0) {
				array[i] = array1[i];
			} else {
				array[i] = array2[i];
			}
		}
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

package Algorithms;

import java.io.IOException;
import java.util.Arrays;

import encryptor.FileOperation;

public class Split extends AlgorithmAbstract {
	public Split(FileOperation operation, encryptor.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	
	public Split (FileOperation operation, byte key, byte secKey,int algo1,int algo2) {
		super(operation, key, secKey, algo1, algo2);
	}

	@Override
	public byte[] encrypt(byte[] array) throws IOException {
		byte[] array1, array2, tempArray;

		tempArray = Arrays.copyOf(array, array.length);
		array1 = doAlgo(key, algo1, tempArray);
		tempArray = Arrays.copyOf(array, array.length);
		array2 = doAlgo(secKey, algo2, tempArray);

		buildArray(array, array1, array2);

//		writeKeysToFile(key, secKey, algo1, algo2);
		return array;
	}

	public byte[] decrypt(byte[] array) throws IOException {
		byte[] array1, array2, tempArray;
		tempArray = Arrays.copyOf(array, array.length);
		array1 = doAlgo(key, algo1, tempArray);
		tempArray = Arrays.copyOf(array, array.length);
		array2 = doAlgo(secKey, algo2, tempArray);
		buildArray(array, array1, array2);
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

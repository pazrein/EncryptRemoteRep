package Algorithms;

import java.io.IOException;
import java.util.Arrays;

import encryptor.FileOperation;

public class Split extends AlgorithmAbstract {
	public Split(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		super(operation, AF);
	}


	@Override
	public byte[] encrypt(byte[] array, byte key) throws IOException {
		byte[] array0, array1, tempArray;

		tempArray = Arrays.copyOf(array, array.length);
		array0 = doAlgo(this.getKeys().get(0), this.getAlgos().get(0), tempArray);
		
		tempArray = Arrays.copyOf(array, array.length);
		array1 = doAlgo(this.getKeys().get(1), this.getAlgos().get(1), tempArray);

		buildArray(array, array0, array1);

		return array;
	}

	public byte[] decrypt(byte[] array, byte key) throws IOException {
		return encrypt(array, key);
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

package Algorithms;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;

import encryptor.FileOperation;

public class Reverse extends AlgorithmAbstract {
	
	public Reverse(FileOperation operation, encryptor.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	
	public Reverse (FileOperation operation, byte key, byte secKey,int algo1,int algo2) {
		super(operation, key, secKey, algo1, algo2);
	}

	@Override
	public byte[] encrypt(byte[] array) throws IOException {
		ArrayUtils.reverse(array);

		array = doAlgo(key, algo1, array);

//		writeKeysToFile(key, secKey, algo1, algo2);
		return array;

	}

	public byte[] decrypt(byte[] array) throws IOException {
		array = doAlgo(key, algo1, array);
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

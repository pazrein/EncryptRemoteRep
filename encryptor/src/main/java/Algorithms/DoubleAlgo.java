package Algorithms;

import java.io.IOException;

import encryptor.FileOperation;

public class DoubleAlgo extends AlgorithmAbstract {

	public DoubleAlgo(FileOperation operation, encryptor.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	
	public DoubleAlgo (FileOperation operation, byte key, byte secKey,int algo1,int algo2) {
		super(operation, key, secKey, algo1, algo2);
	}

	@Override
	public byte[] encrypt(byte[] array) throws IOException {
						
		array = doAlgo(key, algo1, array);
		array = doAlgo(secKey, algo2, array);

//		writeKeysToFile(key, secKey, algo1, algo2);
		return array;
	}

	public byte[] decrypt(byte[] array) throws IOException {
		array = doAlgo(secKey, algo2, array);
		array = doAlgo(key, algo1, array);
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

package Algorithms;

import java.io.IOException;


import encryptor.FileOperation;

public abstract class AlgorithmAbstract implements Algorithm {

	int MAX_VALUE = 127;
	int MIN_VALUE = -128;
	byte key, secKey;
	int algo1, algo2;
	FileOperation operation;

	static int counter = 0;

	public AlgorithmAbstract(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		this.operation = operation;
		this.key = AF.getKey();
		this.secKey = AF.getSecKey();
		this.algo1 = AF.getAlgo1();
		this.algo2 = AF.getAlgo2();
	}

	public AlgorithmAbstract(FileOperation operation, byte key, byte secKey, int algo1, int algo2) {
		this.operation = operation;
		this.key = key;
		this.secKey = secKey;
		this.algo1 = algo1;
		this.algo2 = algo2;
	}

	public byte[] doOperation(byte[] array) throws IOException {
		if (operation == FileOperation.encryption) {
			return encrypt(array);
		} else {
			return decrypt(array);
		}
	}

	public byte[] encrypt(byte[] array) throws IOException {
		for (int i = 0; i < array.length; i++) {
			array[i] = encryptPerByte(array[i], key);
		}
		return array;
	}

	public byte[] decrypt(byte[] array) throws IOException {
		for (int i = 0; i < array.length; i++) {
			array[i] = decryptPerByte(array[i], key);
		}
		return array;

	}

	public abstract byte encryptPerByte(byte b, byte key);

	public abstract byte decryptPerByte(byte b, byte key);


	byte[] doAlgo(byte key, int algo, byte[] array) throws IOException {
		switch (algo) {
		case 1:
			CaesarCipher cc = new CaesarCipher(operation, key, secKey, algo1, algo2);
			return cc.doOperation(array);
		case 2:
			XOR x = new XOR(operation, key, secKey, algo1, algo2);
			return x.doOperation(array);
		case 3:
			Multiplication m = new Multiplication(operation, key, secKey, algo1, algo2);
			return m.doOperation(array);
		default:
			return null;
		}
	}


}

package Algorithms;

import java.io.IOException;

import encryptor.FileOperation;

public class Multiplication extends AlgorithmAbstract {

	public Multiplication(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	
	public Multiplication (FileOperation operation, byte key, byte secKey,int algo1,int algo2) {
		super(operation, key, secKey, algo1, algo2);
	}

	@Override
	public byte[] encrypt(byte[] array) throws IOException{
		if (key % 2 == 0) {
			throw new IllegalArgumentException("The chosen key was illegal for this algorithm.");
		}

		return super.encrypt(array);
	}

	@Override
	public byte[] decrypt(byte[] array) throws IOException {
		for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
			if (decryptPerByte((byte) i, key) == (byte) 1) {
				key = (byte) i;
				break;
			}
		}
		return super.decrypt(array);
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

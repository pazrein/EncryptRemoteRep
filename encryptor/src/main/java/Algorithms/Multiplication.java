package Algorithms;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import encryptor.FileOperation;

public class Multiplication extends AlgorithmAbstract {

	public Multiplication(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	


	public Multiplication(FileOperation operation,List <Byte> keys, List<Integer> algos) {
		super(operation, keys,algos);
	}



	@Override
	public byte[] encrypt(byte[] array,byte key) throws IOException{
		if (key % 2 == 0) {
			throw new IllegalArgumentException("The chosen key was illegal for this algorithm.");
		}

		return super.encrypt(array, key);
	}

	@Override
	public byte[] decrypt(byte[] array, byte key) throws IOException {
		for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
			if (decryptPerByte((byte) i, key) == (byte) 1) {
				key = (byte) i;
				break;
			}
		}
		return super.decrypt(array, key);
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

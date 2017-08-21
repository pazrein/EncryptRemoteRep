package Algorithms;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;

import encryptor.FileOperation;

public class Reverse extends AlgorithmAbstract {
	
	public Reverse(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	

	@Override
	public byte[] encrypt(byte[] array , byte key) throws IOException {
		ArrayUtils.reverse(array);

		array = doAlgo(key, this.getAlgos().get(0), array);

		return array;

	}

	public byte[] decrypt(byte[] array , byte key) throws IOException {
		array = doAlgo(key, this.getAlgos().get(0), array);
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

package Algorithms;

import java.io.IOException;

import encryptor.FileOperation;

public class DoubleAlgo extends AlgorithmAbstract {

	public DoubleAlgo(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	
	@Override
	public byte[] encrypt(byte[] array , byte key) throws IOException {

		array = doAlgo(this.getKeys().get(0), this.getAlgos().get(0), array);
		array = doAlgo(this.getKeys().get(1), this.getAlgos().get(1), array);

		return array;
	}

	public byte[] decrypt(byte[] array, byte key) throws IOException {

		
		array = doAlgo(this.getKeys().get(1), this.getAlgos().get(1), array);
		array = doAlgo(this.getKeys().get(0), this.getAlgos().get(0), array);
		
		return array;
	}

	@Override
	public byte encryptPerByte(byte b, byte key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte decryptPerByte(byte b, byte key) {
		// TODO Auto-generated method stub
		return 0;
	}



}

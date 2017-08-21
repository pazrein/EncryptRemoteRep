package Algorithms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import encryptor.FileOperation;
import lombok.Getter;

public @Getter abstract class AlgorithmAbstract implements Algorithm {

	protected int MAX_VALUE = 127;
	protected int MIN_VALUE = -128;
	private List <Byte> keys;
	private List <Integer> algos; 
	private FileOperation operation;

	static int counter = 0;

	public AlgorithmAbstract(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		this.keys = new ArrayList<Byte>(AF.getKeys());
		this.algos = new ArrayList<Integer>(AF.getAlgos());
		this.operation = operation;
	}
	
	public AlgorithmAbstract(FileOperation operation, List <Byte> keys, List<Integer> algos){
		this.operation = operation;
		this.keys = keys;
		this.algos = algos;
		
	}
	
	public byte[] doOperation(byte[] array) throws IOException {
		if (operation == FileOperation.encryption) {
			return encrypt(array,keys.get(0));
		} else {
			return decrypt(array,keys.get(0));
		}
	}

	public byte[] encrypt(byte[] array, byte key) throws IOException {
		for (int i = 0; i < array.length; i++) {
			array[i] = encryptPerByte(array[i], key);
		}
		return array;
	}

	public byte[] decrypt(byte[] array,  byte key) throws IOException {
		for (int i = 0; i < array.length; i++) {
			array[i] = decryptPerByte(array[i], key);
		}
		return array;

	}

	public abstract byte encryptPerByte(byte b, byte key);

	public abstract byte decryptPerByte(byte b, byte key);


	byte[] doAlgo(byte key, int algo, byte[] array) throws IOException {
		List <Byte> keys = new ArrayList<Byte>();
		keys.add(key);
		List <Integer> algos = new ArrayList<Integer>();
		algos.add(algo);
		switch (algo) {
		case 1:
			CaesarCipher cc = new CaesarCipher(operation,keys,algos);
			return cc.doOperation(array);
		case 2:
			XOR x = new XOR(operation, keys,algos);
			return x.doOperation(array);
		case 3:
			Multiplication m = new Multiplication(operation, keys,algos);
			return m.doOperation(array);
		default:
			return null;
		}
	}


}

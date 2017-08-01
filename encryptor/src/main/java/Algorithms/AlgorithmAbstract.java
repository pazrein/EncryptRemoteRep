package Algorithms;

import java.io.DataInputStream;
import encryptor.*;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.Random;

import encryptor.FileOperation;

public abstract class AlgorithmAbstract implements Algorithm {

	int MAX_VALUE = 127;
	int MIN_VALUE = -128;
	byte key, secKey;
	int algo1, algo2;
	FileOperation operation;

	static int counter = 0;

	public AlgorithmAbstract(FileOperation operation, encryptor.AlgoFields AF) throws IOException {
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

	// byte chooseAlgorithmRandomly() {
	// Random rnd;
	// rnd = new Random();
	// return (byte) (rnd.nextInt(3) + 1);
	// }

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

	// byte generateRandomKey() {
	// Random rnd;
	// rnd = new Random();
	// return (byte) (rnd.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
	// }

	// void writeKeysToFile(byte key, byte secKey, int algo1, int algo2) throws
	// IOException {
	// FileOutputStream fos = new FileOutputStream("key.bin");
	// DataOutputStream dos = new DataOutputStream(fos);
	// dos.writeByte(key);
	// dos.writeByte(secKey);
	// dos.writeInt(algo1);
	// dos.writeInt(algo2);
	// dos.close();
	// fos.close();
	// }

}

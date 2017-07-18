package Algorithms;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public abstract class AlgorithmAbstract implements Algorithm {

	int MAX_VALUE = 127;
	int MIN_VALUE = -128;

	public byte[] encrypt(byte key, byte[] array) throws IOException {
		for (int i = 0; i < array.length; i++) {
			array[i] = encryptPerByte(array[i], key);
		}
		return array;
	}

	public byte[] decrypt(byte key, byte[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = decryptPerByte(array[i], key);
		}
		return array;

	}

	public abstract byte encryptPerByte(byte b, byte key);

	public abstract byte decryptPerByte(byte b, byte key);

	byte chooseAlgorithmRandomly() {
		Random rnd;
		rnd = new Random();
		return (byte) (rnd.nextInt(3) + 1);
	}

	byte[] encAlgo(byte key, int algo, byte[] array) throws IOException {
		switch (algo) {
		case 1:
			CaesarCipher cc = new CaesarCipher();
			return cc.encrypt(key, array);
		case 2:
			XOR x = new XOR();
			return x.encrypt(key, array);
		case 3:
			Multiplication m = new Multiplication();
			return m.encrypt(key, array);
		default:
			return null;
		}
	}

	byte[] decAlgo(byte key, int algo, byte[] array) {
		switch (algo) {
		case 1:
			CaesarCipher cc = new CaesarCipher();
			return cc.decrypt(key, array);
		case 2:
			XOR x = new XOR();
			return x.decrypt(key, array);
		case 3:
			Multiplication m = new Multiplication();
			return m.decrypt(key, array);
		default:
			return null;
		}
	}

	byte generateRandomByte() {
		Random rnd;
		rnd = new Random();
		return (byte) (rnd.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
	}

	void writeKeysToFile(byte key1, byte key2, int algo1, int algo2) throws IOException {
		FileOutputStream fos = new FileOutputStream("key.bin");
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeByte(key1);
		dos.writeByte(key2);
		dos.writeInt(algo1);
		dos.writeInt(algo2);
		dos.close();
		fos.close();
	}

}

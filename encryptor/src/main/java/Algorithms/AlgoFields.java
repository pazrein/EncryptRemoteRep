package Algorithms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import encryptor.FileOperation;
import lombok.Data;

public  @Data class AlgoFields {
	
	private int MAX_VALUE = 127;
	private int MIN_VALUE = -128;
	private byte key, secKey;
	private int algo1, algo2;
	private FileOperation operation;
	
	public AlgoFields(FileOperation operation) throws IOException {
		switch (operation) {
		case encryption:
			key = generateRandomKey();
			secKey = generateRandomKey();
			algo1 = chooseAlgorithmRandomly();
			algo2 = chooseAlgorithmRandomly();
			writeKeysToFile(key, secKey, algo1, algo2);
			
			break;
		case decryption:
			FileInputStream fos = new FileInputStream("key.bin");
			DataInputStream dos = new DataInputStream(fos);
			key = dos.readByte();
			secKey = dos.readByte();
			algo1 = dos.readInt();
			algo2 = dos.readInt();
			dos.close();
			fos.close();
			break;
		}
	}
	
	byte generateRandomKey() {
		return generateRandomByteInRange(MIN_VALUE,MAX_VALUE);
	}
	
	byte chooseAlgorithmRandomly() {
		return generateRandomByteInRange(1,3);
	}
	
	byte generateRandomByteInRange(int minVal,int maxVal){
		Random rnd;
		rnd = new Random();
		return (byte) (rnd.nextInt(maxVal - minVal + 1) + minVal);
	}
	
	private void writeKeysToFile(byte key, byte secKey, int algo1, int algo2) throws IOException {
	FileOutputStream fos = new FileOutputStream("key.bin");
	DataOutputStream dos = new DataOutputStream(fos);
	dos.writeByte(key);
	dos.writeByte(secKey);
	dos.writeInt(algo1);
	dos.writeInt(algo2);
	dos.close();
	fos.close();
}
	
	
}

package Algorithms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import encryptor.FileOperation;
import lombok.Data;

public @Data class AlgoFields {

	private int MAX_VALUE = 127;
	private int MIN_VALUE = -128;
	private List<Byte> keys;
	private List<Integer> algos;
	private FileOperation operation;

	public AlgoFields(FileOperation operation) throws IOException {
		this.keys = new ArrayList<Byte>();
		this.algos = new ArrayList<Integer>();
		if (operation == FileOperation.encryption){
			this.keys.add(generateRandomKey());
		}
		else{
			int listSize;
			FileInputStream fos = new FileInputStream("key.bin");
			DataInputStream dos = new DataInputStream(fos);

			listSize = dos.readInt();
			for (int i = 0; i < listSize; i++) {
				keys.add(dos.readByte());
			}

			listSize = dos.readInt();
			for (int i = 0; i < listSize; i++) {
				algos.add(dos.readInt());
			}

			dos.close();
			fos.close();
		}
	}

	byte generateRandomKey() {
		return generateRandomByteInRange(MIN_VALUE, MAX_VALUE);
	}

	int chooseAlgorithmRandomly() {
		return generateRandomByteInRange(1, 3);
	}

	byte generateRandomByteInRange(int minVal, int maxVal) {
		Random rnd;
		rnd = new Random();
		return (byte) (rnd.nextInt(maxVal - minVal + 1) + minVal);
	}
	
	void addKeys(int numOfKeys){
		for (int i = 0; i < numOfKeys; i++) {
			keys.add(generateRandomKey()) ;
		}
	}
	void addAlgos(int numOfAlgos){
		for (int i = 0; i < numOfAlgos; i++) {
			algos.add(chooseAlgorithmRandomly()) ;
		}
	}
	
	public void addFields(int numOfKeys,int numOfAlgos){
		addKeys(numOfKeys);
		addAlgos(numOfAlgos);
	}
	
	

	 public void writeToFile() throws IOException {
		FileOutputStream fos = new FileOutputStream("key.bin");
		DataOutputStream dos = new DataOutputStream(fos);
		
		
		dos.writeInt(keys.size());
		for (Byte key : keys) {
			dos.writeByte(key);
		}

		dos.writeInt(algos.size());
		for (Integer algo : algos) {
			dos.writeInt(algo);
		}

		dos.close();
		fos.close();
	}

}

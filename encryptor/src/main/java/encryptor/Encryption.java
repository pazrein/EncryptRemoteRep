package encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import encryptor.InputReader;
import lombok.Data;

public @Data class Encryption {
	private static InputReader in = new InputReader();
	private String path;
	private byte key;
	private byte secKey;
	private Path inputFile;
	private Path outputFile;
	private byte[] fileArray;
	private int algorithm;
	private int MAX_VALUE = 127;
	private int MIN_VALUE = -128;
	
	

	public Encryption(String path) throws IOException {
		this.path = path;
		this.key = generateRandomByte();
		this.secKey = generateRandomByte();
		this.inputFile = Paths.get(path);
		this.outputFile = Paths.get(path + ".encrypted"); 
		this.fileArray = Files.readAllBytes(inputFile);
		this.algorithm = -1;
		
	}
	
	void chooseEncAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 4)) {
			System.out.println("Choose your encryption algorithm from the following list" +
			"\n 1 for caesarCipher"
			+ "\n 2 for XOR " + 
			"\n 3 for Multiplication");
			
			algorithm = in.nextInt();
			if (!firstTry){
				System.out.println("Pick a number between 1 to 3 only");
			}
			firstTry = false;
		}
	}
	
	void encrypt(){
		switch (algorithm) {
		case 1:
			caesarCipher();
			break;
		case 2:
			XOR();
			break;
		case 3:
			Multiplication();
			break;
		case 4:
			Double();
			break;
		case 5:
			Reverse();
			break;
		case 6:
			Split();
			break;

		default:
			break;
		}
	}
	
	void write() throws IOException{
		Files.write(outputFile, fileArray);
	}
	
	private void Reverse() {
		// TODO Auto-generated method stub
		
	}

	private void Double() {
		// TODO Auto-generated method stub
		
	}

	private void Split() {
		int algoOdd,algoEven;
		
		algoOdd = chooseAlgorithmRandomly();
		algoEven = chooseAlgorithmRandomly();
		
		
		
		
	}
	
	private byte algoPerByte(int algo, byte b , byte k){
		switch (algo) {
		case 1:
			return caesarCipherPerByte(b,k);
		case 2:
			return XORPerByte(b,k);
		case 3:
			return MultiPerByte(b,k);
		default:
			return 0;
		}
	}
	
	 private byte generateRandomByte(){
		Random rnd;
		rnd = new Random();
		return (byte) (rnd.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
	}
	 
	private int chooseAlgorithmRandomly() {
		Random rnd;
		rnd = new Random();
		return rnd.nextInt(4);
	}

	private void caesarCipher() {
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = caesarCipherPerByte(fileArray[i], key);
		}
	}
	
	private byte caesarCipherPerByte(byte b, byte key){
		return (byte) (b + key);
	}
	
	private void XOR() {
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = XORPerByte(fileArray[i], key);
		}
	}
	
	private byte XORPerByte(byte b, byte key){
		return (byte) (b ^ key);
	}
	
	private void Multiplication(){
		if (key % 2 == 0){
			key++;
			System.out.println("The chosen key was illegal for this algorithm."
					+ "\n The new key is: " + key);
		}
		
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = MultiPerByte(fileArray[i], key);
		}
	}
	
	private byte MultiPerByte(byte b, byte key){
		return (byte) (b * key);
	}
		
	
	
	

}
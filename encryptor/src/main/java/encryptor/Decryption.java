package encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

public @Data class Decryption {
	private static InputReader in = new InputReader();
	private String path;
	private byte key;
	private Path inputFile;
	private Path outputFile;
	private byte[] fileArray;
	private int algorithm;
	private String[] pathArr;
	private int MAX_VALUE = 127;
	private int MIN_VALUE = -128;
	
	
	
	public Decryption(String path) throws IOException {
		this.path = path;
		this.pathArr = path.split("\\.");
		this.key = 0;
		this.inputFile = Paths.get(path);
		this.outputFile = Paths.get(pathArr[0] + "_decrypted." + pathArr[1]);
		this.fileArray = Files.readAllBytes(inputFile);
		this.algorithm = -1;
		
	}
	
	void chooseEncAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 4)) {
			System.out.println("Choose your decryption algorithm *YOU USED* from the following list" +
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
	
	void inputKey(){
		System.out.println("Enter the key for decreption - should be the key you use for encryption");
		key = in.nextByte();
	}
	
	void decrypt(){
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
		default:
			break;
		}
	} 
	
	void write() throws IOException{
		Files.write(outputFile, fileArray);
	}
	private void caesarCipher() {
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = caesarCipherPerByte(fileArray[i], key);
		}
	}
	
	private byte caesarCipherPerByte(byte b, byte key){
		return (byte) (b - key);
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
		for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
			if(key * i == 1){
				key = (byte) i;
				break;
			}
		}
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = MultiPerByte(fileArray[i], key);
		}
	}
	
	private byte MultiPerByte(byte b, byte key){
		return (byte) (b * key);
	}

}

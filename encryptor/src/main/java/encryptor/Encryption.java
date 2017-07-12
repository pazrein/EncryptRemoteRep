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
	private Path inputFile;
	private Path outputFile;
	private byte[] fileArray;
	private int algorithm;

	public Encryption(String path) throws IOException {
		this.path = path;
		this.key = generateRandomByte();
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

		default:
			break;
		}
	}
	
	void write() throws IOException{
		Files.write(outputFile, fileArray);
	}
	
	 private byte generateRandomByte(){
		int max,min;
		Random rnd;
		max = 127;
		min = -128;
		rnd = new Random();
		return (byte) (rnd.nextInt(max - min + 1) + min);
	}

	private void caesarCipher() {
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = caesarCipherPerByte(fileArray[i], key);
		}
	}
	
	private byte caesarCipherPerByte(byte b, byte key){
		return (byte) (b + key);
	}
	
	

}
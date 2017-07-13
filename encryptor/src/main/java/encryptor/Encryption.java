package encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import encryptor.InputReader;
import lombok.Data;

public @Data class Encryption extends AbstractFileManipulation {
	private static InputReader in = new InputReader();


	public Encryption(String path) throws IOException {
		super(path);
		this.key = generateRandomByte();
		this.secKey = generateRandomByte();
		this.outputFile = Paths.get(path + ".encrypted"); 

		
	}
	
	void chooseEncAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 7)) {
			System.out.println("Choose your encryption algorithm from the following list"
					+  chooseAlgoFromListStringToPrint());
			algorithm = in.nextInt();
			if (!firstTry){
				System.out.println("Pick a number between 1 to 6 only");
			}
			firstTry = false;
		}
	}
	
	void encrypt(){
		chooseAlgoToActivate(algorithm);
	}
	

	@Override
	void Double() {
		int algofirst, algosec;

		algofirst = chooseAlgorithmRandomly();
		algosec = chooseAlgorithmRandomly();
		
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = algoPerByte(algofirst, fileArray[i], key);
			fileArray[i] = algoPerByte(algosec, fileArray[i], secKey);
		}
	}
	@Override
	void Reverse() {
		int algo;
		algo = chooseAlgorithmRandomly();
		for (int i = fileArray.length - 1; i > -1; i--) {
			fileArray[i] = algoPerByte(algo, fileArray[i], key);
		}
	}
	@Override
	void Split() {
		int algoOdd, algoEven;

		algoOdd = chooseAlgorithmRandomly();
		algoEven = chooseAlgorithmRandomly();

		for (int i = 0; i < fileArray.length; i++) {
			if (i % 2 != 0) {
				fileArray[i] = algoPerByte(algoOdd, fileArray[i], key);
			} 
			else {
				fileArray[i] = algoPerByte(algoEven, fileArray[i], secKey);
			}
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


	@Override
	byte caesarCipherPerByte(byte b, byte key){
		return (byte) (b + key);
	}
	
	@Override
	void Multiplication(){
		if (key % 2 == 0){
			key++;
			System.out.println("The chosen key was illegal for this algorithm."
					+ "\n The new key is: " + key);
		}
		
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = MultiPerByte(fileArray[i], key);
		}
	}
}
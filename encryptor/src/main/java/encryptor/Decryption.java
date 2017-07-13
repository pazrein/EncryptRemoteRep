package encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

public @Data class Decryption extends AbstractFileManipulation {
	private static InputReader in = new InputReader();
	private String[] pathArr;

	public Decryption(String path) throws IOException {
		super(path);
		this.pathArr = path.split("\\.");
		this.outputFile = Paths.get(pathArr[0] + "_decrypted." + pathArr[1]);
	}
	
	void chooseDecAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 4)) {
			System.out.println("Choose your decryption algorithm *YOU USED* from the following list" +
					 chooseAlgoFromListStringToPrint());
			
			algorithm = in.nextInt();
			if (!firstTry){
				System.out.println("Pick a number between 1 to 6 only");
			}
			firstTry = false;
		}
	}
	
	void inputKey(){
		System.out.println("Enter the key for decreption - should be the key you use for encryption");
		key = in.nextByte();
	}
	
	void decrypt(){
		chooseAlgoToActivate(algorithm);
	} 
	
	@Override
	byte caesarCipherPerByte(byte b, byte key){
		return (byte) (b - key);
	}
	
	@Override
	void Multiplication(){
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

	@Override
	void Double() {
		int algofirst, algosec;

		algofirst = 0; // TODO get them
		algosec = 0; // TODO get them
		
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = algoPerByte(algofirst, fileArray[i], key);
			fileArray[i] = algoPerByte(algosec, fileArray[i], secKey);
		}
	}

	@Override
	void Reverse() {
		int algo; // TODO get them
		algo = 0;
		for (int i = fileArray.length - 1; i > -1; i--) {
			fileArray[i] = algoPerByte(algo, fileArray[i], key);
		}
	}

	@Override
	void Split() {
		int algoOdd, algoEven;

		algoOdd = 0;// TODO get them
		algoEven = 0;// TODO get them

		for (int i = 0; i < fileArray.length; i++) {
			if (i % 2 != 0) {
				fileArray[i] = algoPerByte(algoOdd, fileArray[i], key);
			} 
			else {
				fileArray[i] = algoPerByte(algoEven, fileArray[i], secKey);
			}
		}
	}
}

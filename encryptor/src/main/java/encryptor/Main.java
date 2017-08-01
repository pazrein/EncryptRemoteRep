package encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import encryptor.InputReader;

public class Main {
	private static InputReader in = new InputReader();

	public static void main(String[] args) throws IOException {
		
		UserInput UI = new UserInput();
		if(!UI.isValidPath()){
			throw new IllegalArgumentException("Failed to enter path more than 3 times");
		}
		UI.printFilePath();
		
		Converter c = new Converter(UI);
		c.convert(UI);
	}


//	static void encryption(String path) throws IOException {
//		Encryption enc = new Encryption(path);
//		// System.out.println("The key that was chosen is: " + enc.getKey());
//		enc.chooseEncAlgo();
//		enc.encrypt();
//		enc.write();
//	}

//	static void decreption(String path) throws IOException {
//		Decryption dec = new Decryption(path);
//		// dec.inputKey();
//		dec.chooseDecAlgo();
//		dec.decrypt();
//		dec.write();
//	}
	


}

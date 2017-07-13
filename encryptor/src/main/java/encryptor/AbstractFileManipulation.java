package encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

public @Data abstract class AbstractFileManipulation {
	String path;
	byte key;
	byte secKey;
	Path inputFile;
	Path outputFile;
	byte[] fileArray;
	int algorithm;
	int MAX_VALUE = 127;
	int MIN_VALUE = -128;
	
	public AbstractFileManipulation(String path) throws IOException {
		this.path = path;
		this.inputFile = Paths.get(path);
		this.fileArray = Files.readAllBytes(inputFile);
	}
	
	void caesarCipher() {
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = caesarCipherPerByte(fileArray[i], key);
		}
	}
	abstract  byte caesarCipherPerByte(byte b, byte key);
	
	void XOR() {
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = XORPerByte(fileArray[i], key);
		}
	}
	
	byte XORPerByte(byte b, byte key){
		return (byte) (b ^ key);
	}
	
	abstract void Multiplication();
	
	byte MultiPerByte(byte b, byte key){
		return (byte) (b * key);
	}
	
	abstract void Double();
	
	abstract void Reverse();
	
	abstract void Split();
	
	String chooseAlgoFromListStringToPrint() {
		return  "\n 1 for caesarCipher" +
					"\n 2 for XOR " +
					"\n 3 for Multiplication" +
					 "\n 4 for Double" +
					 "\n 5 for Reverse" +
					 "\n 6 for Split";
	}
	
	byte algoPerByte(int algo, byte b , byte k){
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
	

	
	void chooseAlgoToActivate (int algorithm){
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
	

	
	
}

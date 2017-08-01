package encryptor;
import Algorithms.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;


public class Converter {
	private static InputReader in = new InputReader();
	private int algorithm;
	private Path inputFile ,outputFile;
	private byte[] fileArray;
	private UserInput UI;
	private AlgoFields AF;
		

	public Converter(UserInput UI) throws IOException {
		this.UI = UI;
		this.AF = new AlgoFields(UI.operation);
	}
	
	public void convert(UserInput UI) throws IOException{
		switch (UI.type) {
		case FILE:
			switch (UI.operation) {
			case encryption:
				encryptFile();
				break;
			case decryption:
				decryptFile();
				break;
			}
			break;
		case DIR:
			switch (UI.method) {
			case SYNC:
				switch (UI.operation) {
				case encryption:
					encryptDirSync(UI.path);
					break;
				case decryption:
					decryptDirSync(UI.path);
					break;
				}
				break;

			case ASYNC:
				switch (UI.operation) {
				case encryption:
					encryptDirASync(UI.path);

					break;
				case decryption:
					decryptDirASync(UI.path);
					break;
				}
				break;
			}
			break;
		}
	}
	
	
	private void decryptDirASync(String path) {
		// TODO Auto-generated method stub
		
	}

	private void encryptDirASync(String path) {
		// TODO Auto-generated method stub
		
	}

	private void decryptDirSync(String path) throws IOException {
		String newDirName = UI.path + "\\decrypted";
		File newDir = new File(newDirName);
		FileUtils.forceMkdir(newDir);
		FileUtils.cleanDirectory(newDir);
		File[] files = new File(UI.path + "\\encrypted").listFiles();
		
    	chooseAlgo();
    	System.out.println("Decrypting.. ");
    	
	    for (File file : files) {
	        if (!file.isDirectory()) {
	        	fileToByteArr(UI.path + "\\encrypted\\" + file.getName());
	        	activateAlgo(algorithm);
	        	this.outputFile = Paths.get(newDirName + "\\" + file.getName());
	        	write();
	        }
	    }
	    System.out.println("Done");
		
	}

	private void encryptDirSync(String path) throws IOException {
		String newDirName = UI.path + "\\encrypted";
		File newDir = new File(newDirName);
		FileUtils.forceMkdir(newDir);
		FileUtils.cleanDirectory(newDir);
		File[] files = new File(UI.path).listFiles();
		
    	chooseAlgo();
    	System.out.println("Encrypting.. ");
    	
	    for (File file : files) {
	        if (!file.isDirectory()) {
	        	fileToByteArr(UI.path + "\\" + file.getName());
	        	activateAlgo(algorithm);
	        	this.outputFile = Paths.get(newDirName + "\\" + file.getName());
	        	write();
	       }
	    }
	    System.out.println("Done");
		
	}
	

	private void decryptFile() throws IOException {
		fileToByteArr(UI.path);
		String[] pathArr;
		pathArr = UI.path.split("\\.");
		this.outputFile = Paths.get(pathArr[0] + "_decrypted." + pathArr[1]);
		chooseAlgo();
		System.out.println("Decrypting.. ");
		activateAlgo(algorithm);
		write();
		System.out.println("Done");
		
	}

	private void encryptFile() throws IOException {
		fileToByteArr(UI.path);
		this.outputFile = Paths.get(UI.path + ".encrypted");
		chooseAlgo();
		System.out.println("Encrypting.. ");
		activateAlgo(algorithm);
		write();
		System.out.println("Done");
		
	}
	
	private void write() throws IOException {
		Files.write(outputFile, fileArray);
	}
	
	private void fileToByteArr(String path){
		this.inputFile = Paths.get(path);
		try {
			fileArray = Files.readAllBytes(inputFile);
		} catch (IOException e) {
			System.out.println("Can't Read bytes");
			e.printStackTrace();
			System.exit(0);
		}
	} 
	
	
	private String chooseAlgoFromListStringToPrint() {
		return "\n 1 for caesarCipher" + "\n 2 for XOR " + "\n 3 for Multiplication" + "\n 4 for Double"
				+ "\n 5 for Reverse" + "\n 6 for Split";
	}
	
	private void chooseAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 7)) {
			System.out.println(
					"Choose your " + UI.operation.toString() +  " algorithm from the following list" + chooseAlgoFromListStringToPrint());
			algorithm = in.nextInt();
			if (!firstTry) {
				System.out.println("Pick a number between 1 to 6 only");
			}
			firstTry = false;
		}
	}
	
	private void activateAlgo(int algorithm) throws IOException {
		switch (algorithm) {
		case 1:
			CaesarCipher cc = new CaesarCipher(UI.operation, AF);
			fileArray = cc.doOperation(fileArray);
			break;
		case 2:
			XOR x = new XOR(UI.operation, AF);
			fileArray = x.doOperation(fileArray);
			break;
		case 3:
			Multiplication m = new Multiplication(UI.operation, AF);
			fileArray = m.doOperation(fileArray);
			break;
		case 4:
			DoubleAlgo d = new DoubleAlgo(UI.operation, AF);
			fileArray = d.doOperation(fileArray);
			break;
		case 5:
			Reverse r = new Reverse(UI.operation, AF);
			fileArray = r.doOperation(fileArray);
			break;
		case 6:
			Split s = new Split(UI.operation, AF);
			fileArray = s.doOperation(fileArray);
			break;
		default:
			throw new IllegalArgumentException("No algorithm with number " + algorithm + " exist");
		}
	}

	

}

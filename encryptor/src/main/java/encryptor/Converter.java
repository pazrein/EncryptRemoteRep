package encryptor;
import Algorithms.*;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.FileUtils;


public @Data class Converter {
	private static InputReader in = new InputReader();
	private int algorithm;
	private Path inputFile ,outputFile;
	private byte[] fileArray;
	private UserInput UI;
	private AlgoFields AF;
		

	public Converter(UserInput UI) throws IOException {
		this.UI = UI;
		this.AF = new AlgoFields(UI.getOperation());
	}
	public Converter(UserInput UI, AlgoFields AF) throws IOException {
		this.UI = UI;
		this.AF = AF;
	}
	
	
	public void convert(UserInput UI) throws IOException{
		switch (UI.getType()) {
		case FILE:
			switch (UI.getOperation()) {
			case encryption:
				encryptFile();
				break;
			case decryption:
				decryptFile();
				break;
			}
			break;
		case DIR:
			switch (UI.getMethod()) {
			case SYNC:
				switch (UI.getOperation()) {
				case encryption:
					encryptDirSync(UI.getPath());
					break;
				case decryption:
					decryptDirSync(UI.getPath());
					break;
				}
				break;

			case ASYNC:
				switch (UI.getOperation()) {
				case encryption:
					encryptDirASync(UI.getPath());

					break;
				case decryption:
					decryptDirASync(UI.getPath());
					break;
				}
				break;
			}
			break;
		}
		
		AF.writeToFile();
		System.out.println("Done");
	}
	
		
	private void encryptDirASync(String path) throws IOException {
		File[] files = prepareDirectory(path);
		System.out.println("Encrypting.. ");
    	
    	Thread [] threadArr = new Thread [files.length];
    	for (int i = 0; i < threadArr.length; i++) {
			threadArr[i] = new Thread(new Runner(UI, AF, algorithm, files[i]));
		}
    	for (int i = 0; i < threadArr.length; i++) {
			threadArr[i].start();
		}
    }
	
	private void decryptDirASync(String path) throws IOException {
		File[] files = prepareDirectory(path);
		System.out.println("Decrypting.. ");
    	
		Thread [] threadArr = new Thread [files.length];
    	for (int i = 0; i < threadArr.length; i++) {
			threadArr[i] = new Thread(new Runner(UI, AF, algorithm, files[i]));
		}
    	for (int i = 0; i < threadArr.length; i++) {
			threadArr[i].start();
		}
	 }

	private void decryptDirSync(String path) throws IOException {
		String newDirName = getNewDirName(UI.getOperation(), path);
		File[] files = prepareDirectory(path);
		System.out.println("Decrypting.. ");
    	
	    for (File file : files) {
	        if (!file.isDirectory()) {
	        	fileToByteArr(path + "\\encrypted\\" + file.getName());
	        	activateAlgo(algorithm);
	        	this.outputFile = Paths.get(newDirName + "\\" + file.getName());
	        	write();
	        }
	    }
	}

	private void encryptDirSync(String path) throws IOException {
		String newDirName = getNewDirName(UI.getOperation(), path);
		File[] files = prepareDirectory(path);
		System.out.println("Encrypting.. ");
    	
	    for (File file : files) {
	        if (!file.isDirectory()) {
	        	fileToByteArr(path + "\\" + file.getName());
	        	activateAlgo(algorithm);
	        	this.outputFile = Paths.get(newDirName + "\\" + file.getName());
	        	write();
	       }
	    }
	}
	
	private void decryptFile() throws IOException {
		fileToByteArr(UI.getPath());
		String[] pathArr;
		pathArr = UI.getPath().split("\\.");
		this.outputFile = Paths.get(pathArr[0] + "_decrypted." + pathArr[1]);
		chooseAlgo();
		System.out.println("Decrypting.. ");
		activateAlgo(algorithm);
		write();
	}

	private void encryptFile() throws IOException {
		fileToByteArr(UI.getPath());
		this.outputFile = Paths.get(UI.getPath() + ".encrypted");
		chooseAlgo();
		System.out.println("Encrypting.. ");
		activateAlgo(algorithm);
		write();
	}
	
	void write() throws IOException {
		Files.write(outputFile, fileArray);
	}
	
	void fileToByteArr(String path){
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
	
	private File [] prepareDirectory(String path) throws IOException{
		File[] files;
		String newDirName = getNewDirName(UI.getOperation(), path);
		File newDir = new File(newDirName);
		FileUtils.forceMkdir(newDir);
		FileUtils.cleanDirectory(newDir);
		if (UI.getOperation() == FileOperation.encryption) {
			files = new File(UI.getPath()).listFiles();
		}
		else{
			files = new File(UI.getPath() + "\\encrypted").listFiles();
		}
		chooseAlgo();
		return files;
	}
	
	static String getNewDirName(FileOperation operation, String path) {
		if (operation == FileOperation.encryption){
			return path + "\\encrypted";
		}
		else{
			return path + "\\decrypted";
		}
	}
	
	private void chooseAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 7)) {
			System.out.println(
					"Choose your " + UI.getOperation().toString() +  " algorithm from the following list" + chooseAlgoFromListStringToPrint());
			algorithm = in.nextInt();
			if (!firstTry) {
				System.out.println("---Pick a number between 1 to 6 only---");
			}
			firstTry = false;
		}
		if (algorithm == 4 || algorithm == 6) {
			AF.addFields(1, 2);
		}
		if (algorithm == 5){
			AF.addFields(0, 1);
		}
	}
	//TODO topping decorator on AlgoFields (Add more keys and algos) and also changed from array to list
	void activateAlgo(int algorithm) throws IOException {
		switch (algorithm) {
		case 1:
			CaesarCipher cc = new CaesarCipher( UI.getOperation(), AF);
			fileArray = cc.doOperation(fileArray);
			break;
		case 2:
			XOR x = new XOR( UI.getOperation(), AF);
			fileArray = x.doOperation(fileArray);
			break;
		case 3:
			Multiplication m = new Multiplication( UI.getOperation(), AF);
			fileArray = m.doOperation(fileArray);
			break;
		case 4:
			DoubleAlgo d = new DoubleAlgo( UI.getOperation(), AF);
			fileArray = d.doOperation(fileArray);
			break;
		case 5:
			Reverse r = new Reverse( UI.getOperation(), AF);
			fileArray = r.doOperation(fileArray);
			break;
		case 6:
			Split s = new Split( UI.getOperation(), AF);
			fileArray = s.doOperation(fileArray);
			break;
		default:
			throw new IllegalArgumentException("No algorithm with number " + algorithm + " exist");
		}
	}
}

class Runner implements Runnable {
	private int algorithm;
	private File file;
	private UserInput UI;
	private AlgoFields AF;
//	private Lock lock = new ReentrantLock();

	
	public Runner(UserInput UI, AlgoFields AF, int algorithm, File file) {
		this.UI = UI;
		this.AF = AF;
		this.algorithm = algorithm;
		this.file = file;
	}
	public void run() {
		String newDirName = Converter.getNewDirName( UI.getOperation(), UI.getPath());
		Converter c;
		try {
			c = new Converter(UI, AF);
			c.setAlgorithm(algorithm);
	        if (!file.isDirectory()) {
	    		if ( UI.getOperation() == FileOperation.encryption){
	    			c.fileToByteArr(UI.getPath() + "\\" + file.getName());
	    		}
	    		else{
	    			c.fileToByteArr(UI.getPath() + "\\encrypted\\" + file.getName());
	    		}
	  
		        c.activateAlgo(algorithm);
	        	c.setOutputFile(Paths.get(newDirName + "\\" + file.getName()));
	        	c.write();
	       }
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}



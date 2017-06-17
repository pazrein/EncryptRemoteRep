package encryptor;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.Random;


public class Encryptor {
	private static InputReader in = new InputReader();
	
	public static void main(String[] args) throws IOException {
		int mode;
		String[] pathPointer = new String[1];

		mode = getInput(pathPointer);
		switch (mode) {
		case 1:
			encryption(pathPointer[0]);

			break;
		case 0:
			decreption(pathPointer[0]);

			break;
		default: System.out.println("Not a valid mode - only 1 for encryption / 0 for decryption");
			return;
		}

	}
	
	 static void encryption(String path) throws IOException {
		byte key;
		int mode = 1;
		byte[] fileArray;
		Path inputFile, outputFile;
		
		inputFile = Paths.get(path);
		fileArray = Files.readAllBytes(inputFile);
		
		key = generateRandomByte();
		System.out.println("the key for encryption is: " + key);
		
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = caesarCipherOnBytes(fileArray[i], key, mode);
		}
		
		outputFile = Paths.get(path + ".encrypted");
		Files.write(outputFile, fileArray);
	}
	
	static byte caesarCipherOnBytes(byte b, byte key,int mode){
		//encryption
		if (mode == 1){
			return (byte) (b + key);
		}
		//decryption
		else{
			return (byte) (b - key);
		}
	}

	 static void decreption(String path) throws IOException {
		System.out.println(path);
		byte key; 
		byte[] fileArray;
		int mode = 0;
		String[] pathArr;
		Path inputFile,outputFile;
		
		pathArr = path.split("\\.");
		
		System.out.println("Enter the key for decreption - should be the key you use for encryption");
		key = in.nextByte();
		
		inputFile = Paths.get(path);
		fileArray = Files.readAllBytes(inputFile);
		
		for (int i = 0; i < fileArray.length; i++) {
			fileArray[i] = caesarCipherOnBytes(fileArray[i], key,mode);
		}
		
		outputFile = Paths.get(pathArr[0] + "_decrypted." + pathArr[1]);
		Files.write(outputFile, fileArray);
	 }



	 static int getInput(String[] pathPointer) throws IOException{
		int input;
		String path, action = "";
				
		System.out.println("For encryption press 1");
		System.out.println("For decryption press 0");
		input = in.nextInt();
		
		System.out.println("Enter file path ");
		path = in.nextLine();
		path = isValidFilePath(path);
				
		if(input == 1){
			action = "encryption";
			printFilePath(action,path);
			pathPointer[0] = path;
			return 1;
		}
		else if(input == 0){
			action = "decryption";
			printFilePath(action,path);
			pathPointer[0] = path;
			return 0;
		}
		return -1;
	}
	
	 static void printFilePath(String action, String path){
		assert(action.equals(null) || path.equals(null));
		
		System.out.println(action + " simulation of file " + path);
	}
	 static String isValidFilePath (String path) throws IOException{
		File f;
		boolean isValid = false;
		
		while(!isValid){
			f = new File(path);
			if(f.exists() && !f.isDirectory()) { 
				isValid = true;
			}
			else{
				System.out.println("Not a valid file path - try again");
				System.out.println("Enter file path ");
				path = in.nextLine();
			}
		}
		return path;
	}
	
	 static byte generateRandomByte(){
		int max,min;
		Random rnd;
		max = 127;
		min = -128;
		rnd = new Random();
		return (byte) (rnd.nextInt(max - min + 1) + min);
	}
	
	
	
	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader() {
			reader = new BufferedReader(new InputStreamReader(System.in));
			tokenizer = null;
		}
		public String nextLine() throws IOException {
			return reader.readLine();
		}
		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
		public byte nextByte() {
			return (byte) Integer.parseInt(next());
		}
	}
}

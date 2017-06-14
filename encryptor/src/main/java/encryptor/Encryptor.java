package encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Encryptor {
	private static InputReader in = new InputReader();
	
	public static void main(String[] args) throws IOException{
		
		getInput();
	}

	private static void getInput() throws IOException{
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
		}
		else if(input == 0){
			action = "decryption";
			printFilePath(action,path);
		}
		else{
			System.out.println("Not a valid mode - only 1 for encryption / 0 for decryption");
		}
	}
	
	private static void printFilePath(String action, String path){
		System.out.println(action + " simulation of file " + path);
	}
	private static String isValidFilePath (String path) throws IOException{
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
	}
}

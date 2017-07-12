package encryptor;



import java.io.File;
import java.io.IOException;
import encryptor.InputReader;

public class Main {
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
		Encryption enc = new Encryption(path);
		System.out.println("The key that was chosen is: " + enc.getKey());
		enc.chooseEncAlgo();
		enc.encrypt();
		enc.write();
	}

	 static void decreption(String path) throws IOException {
		 Decryption dec = new Decryption(path);
		 dec.inputKey();
		 dec.chooseEncAlgo();
		 dec.decrypt();
		 dec.write();
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
	

	

}

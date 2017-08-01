package encryptor;

import java.io.File;
import java.io.IOException;

public class UserInput {
	FileType type;
	FileOperation operation;
	SynchronizationMethod method;
	String path;
	private static InputReader in = new InputReader();
	
	public UserInput() {
		System.out.println("For encryption press 1");
		System.out.println("For decryption press 0");
		operation = FileOperation.fromInt(in.nextInt());
				
		System.out.println("For a file choose 1");
		System.out.println("For an entire directory choose 0");
		type = FileType.fromInt(in.nextInt());
		
		if(type == FileType.DIR){
			System.out.println("For sync press 1");
			System.out.println("For async press 0");
			method = SynchronizationMethod.fromInt(in.nextInt());
		}
		
		 getPath();
	}
	
	public boolean isValidPath(){
		File f;
		// You have 2 more tries to enter a correct input;
		for (int i = 0; i < 2; i++) {
			f = new File(path);
			if (f.exists()){
				switch (type) {
				case FILE:
					if (!f.isDirectory()){
						return true;
					}
					else{
						System.out.println("The file: " + path + " is not a file");
					}
					break;

				case DIR:
					if (f.isDirectory()){
						return true;
					}
					else{
						System.out.println("The file: " + path + " is not a directory");	
					}
					break;
				}
			}
			else{
				System.out.println("The file: " + path + " does not exists");
			}
			
			getPath();
		}
		return false;
	}
	
	public void printFilePath(){
		switch (type) {
		case FILE:
			System.out.println(operation.toString() + " simulation of file " + path);
			break;
		case DIR:
			System.out.println(operation.toString() + " " + method.toString() + " " +  " simulation of directory " + path);
			break;
		}
	}
	
	//Private-Methods
	
	private void getPath(){
		System.out.println("Enter path ");
		try {
			path = in.nextLine();
		} catch (IOException e) {
			System.out.println("Failed to read path");
			e.printStackTrace();
		}
	}
	
}

package encryptor;

import java.io.File;
import java.io.IOException;
import encryptor.InputReader;

public class Main {
	private static InputReader in = new InputReader();
	
	public static void main(String[] args) throws IOException {
		String path, action;
		FileType type;
		FileOperation operation;
		SynchronizationMethod method;
		
		System.out.println("For encryption press 1");
		System.out.println("For decryption press 0");
		operation = FileOperation.fromInt(in.nextInt());
				
		System.out.println("For a file choose 1");
		System.out.println("For an entire directory choose 0");
		type = FileType.fromInt(in.nextInt());
		
		System.out.println("Enter path ");
		path = in.nextLine();
		
		switch (type) {
		case FILE:
			path = isValidPath(path, 1);
			switch (operation) {
			case ENCRYPT:
				action = "encryption";
				printFilePath(action, path,1);
				encryption(path);
				break;
			case DECRYPT:
				action = "decryption";
				printFilePath(action, path,1);
				decreption(path);
				break;
			}
			break;
		case DIR:
			path = isValidPath(path, 0);
			System.out.println("For sync press 1");
			System.out.println("For async press 0");
			method = SynchronizationMethod.fromInt(in.nextInt());
			switch (method) {
			case SYNC:
				switch (operation) {
				case ENCRYPT:
					action = "encryption";
					printFilePath(action, path,0);
					System.out.println("DIR - SYNC - ENCRYPT"); //TODO
					break;
				case DECRYPT:
					action = "decryption";
					printFilePath(action, path,0);
					System.out.println("DIR - SYNC - DECRYPT"); //TODO
					break;
				}
				break;

			case ASYNC:
				switch (operation) {
				case ENCRYPT:
					action = "encryption";
					printFilePath(action, path,0);
					System.out.println("DIR - ASYNC - ENCRYPT"); //TODO
					break;
				case DECRYPT:
					action = "decryption";
					printFilePath(action, path,0);
					System.out.println("DIR - ASYNC - DECRYPT"); //TODO
					break;
				}
				break;
			}
			break;
		}
	}

	static void encryption(String path) throws IOException {
		Encryption enc = new Encryption(path);
		// System.out.println("The key that was chosen is: " + enc.getKey());
		enc.chooseEncAlgo();
		enc.encrypt();
		enc.write();
	}

	static void decreption(String path) throws IOException {
		Decryption dec = new Decryption(path);
		// dec.inputKey();
		dec.chooseDecAlgo();
		dec.decrypt();
		dec.write();
	}

	static void printFilePath(String action, String path,int mode) {
		assert (action.equals(null) || path.equals(null) || (mode != 1 && mode != 0));
		switch (mode) {
		case 0:
			System.out.println(action + " simulation of an entire directory " + path);
			break;
		case 1:
			System.out.println(action + " simulation of file " + path);
			break;

		default:
			break;
		}
		
	}

	static String isValidPath(String path, int mode) throws IOException {
		File f;
		boolean isValid = false;

		while (!isValid) {
			f = new File(path);
			if (f.exists() && ((mode == 0 && f.isDirectory()) || (mode == 1 && !f.isDirectory()))) {
				isValid = true;

			}
			else {
				System.out.println("Not a valid path - try again");
				System.out.println("Enter path ");
				path = in.nextLine();
			}
		}
		return path;
	}

}

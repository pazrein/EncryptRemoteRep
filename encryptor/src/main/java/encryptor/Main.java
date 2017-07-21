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
			path = isValidPath(path, FileType.FILE);
			switch (operation) {
			case ENCRYPT:
				action = "encryption";
				printFilePath(action, path,FileType.FILE);
				encryption(path);
				break;
			case DECRYPT:
				action = "decryption";
				printFilePath(action, path,FileType.FILE);
				decreption(path);
				break;
			}
			break;
		case DIR:
			path = isValidPath(path, FileType.DIR);
			System.out.println("For sync press 1");
			System.out.println("For async press 0");
			method = SynchronizationMethod.fromInt(in.nextInt());
			switch (method) {
			case SYNC:
				switch (operation) {
				case ENCRYPT:
					action = "encryption";
					printFilePath(action, path,FileType.DIR);
					System.out.println("DIR - SYNC - ENCRYPT"); //TODO
					break;
				case DECRYPT:
					action = "decryption";
					printFilePath(action, path,FileType.DIR);
					System.out.println("DIR - SYNC - DECRYPT"); //TODO
					break;
				}
				break;

			case ASYNC:
				switch (operation) {
				case ENCRYPT:
					action = "encryption";
					printFilePath(action, path,FileType.DIR);
					System.out.println("DIR - ASYNC - ENCRYPT"); //TODO
					break;
				case DECRYPT:
					action = "decryption";
					printFilePath(action, path,FileType.DIR);
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

	static void printFilePath(String action, String path,FileType mode) {
		assert (action.equals(null) || path.equals(null));
		switch (mode) {
		case DIR:
			System.out.println(action + " simulation of an entire directory " + path);
			break;
		case FILE:
			System.out.println(action + " simulation of file " + path);
			break;

		default:
			throw new IllegalArgumentException("In printFilePath " + mode + " is not a valid file type");
		}
		
	}

	static String isValidPath(String path, FileType mode) throws IOException {
		File f;
		boolean isValid = false;

		while (!isValid) {
			f = new File(path);
			if (f.exists() && ((mode == FileType.DIR && f.isDirectory()) || (mode == FileType.FILE && !f.isDirectory()))) {
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

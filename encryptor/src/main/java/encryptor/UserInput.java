package encryptor;

import java.io.File;
import java.io.IOException;

import lombok.Getter;

public @Getter class UserInput {
	private FileType type;
	private FileOperation operation;
	private SynchronizationMethod method;
	private String path;
	static InputReader in = new InputReader();

	public UserInput() {
		// Dont need to do anything
	}

	public void getInput() {
		getDataFeatures();
		if (!isValidPath()) {
			throw new IllegalArgumentException("Failed to enter path more than 3 times");
		}
		printFilePath();
	}

	// Private-Methods

	boolean isValidPath() {
		File f;
		// You have 3 tries to enter a correct input (first on
		for (int i = 0; i < 3; i++) {
			getPathFromUser();
			f = new File(path);
			if (f.exists()) {
				switch (type) {
				case FILE:
					if (!f.isDirectory()) {
						return true;
					} else {
						System.out.println("The file: " + path + " is not a file");
					}
					break;

				case DIR:
					if (f.isDirectory()) {
						return true;
					} else {
						System.out.println("The file: " + path + " is not a directory");
					}
					break;
				}
			} else {
				System.out.println("The file: " + path + " does not exists");
			}
		}
		return false;
	}

	private void printFilePath() {
		switch (type) {
		case FILE:
			System.out.println(operation.toString() + " simulation of file " + path);
			break;
		case DIR:
			System.out
					.println(operation.toString() + " " + method.toString() + " " + " simulation of directory " + path);
			break;
		}
	}

	void getPathFromUser() {
		System.out.println("Enter path ");
		try {
			path = in.nextLine();
		} catch (IOException e) {
			System.out.println("Failed to read path");
			e.printStackTrace();
			path = "";
		}
	}

	void getDataFeatures() {
		System.out.println("For encryption press -enc");
		System.out.println("For decryption press -dec");
		operation = FileOperation.fromString(in.next());

		System.out.println("For a file choose -f");
		System.out.println("For an entire directory choose -dir");
		type = FileType.fromString(in.next());

		if (type == FileType.DIR) {
			System.out.println("For sync enter -s");
			System.out.println("For async press -as");
			method = SynchronizationMethod.fromFlag(in.next());
		}
		// Default SynchronizationMethod for file
		else {
			method = SynchronizationMethod.SYNC;
		}
	}
}

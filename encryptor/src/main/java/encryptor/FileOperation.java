package encryptor;

import lombok.Getter;

public @Getter enum FileOperation {
	ENCRYPT(1), DECRYPT(0);

	private int operation;

	private FileOperation(int op) {
		this.operation = op;
	}

	public static FileOperation fromInt(int op) {
		for (FileOperation FO : FileOperation.values()) {
			if (FO.operation == op) {
				return FO;
			}
		}
		throw new IllegalArgumentException("No constant with operation " + op + " found");
	}

}

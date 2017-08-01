package encryptor;

import lombok.Getter;

public @Getter enum FileOperation {
	encryption(1), decryption(0);

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
	
	public String toString(){
		if (operation == 1){
			return "encryption";
		}
		else if(operation == 0){
			return "decryption";
		}
		else{
			throw new IllegalArgumentException("No constant with operation " + operation + " found in toString");
		}
	}
}

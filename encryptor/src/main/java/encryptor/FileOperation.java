package encryptor;

import lombok.Getter;

public @Getter enum FileOperation {
	encryption("-enc"), decryption("-dec");

	private String operation;

	private FileOperation(String op) {
		this.operation = op;
	}

	public static FileOperation fromString(String op) {
		for (FileOperation FO : FileOperation.values()) {
			if (FO.operation.equals(op)) {
				return FO;
			}
		}
		throw new IllegalArgumentException("No constant with operation " + op + " found");
	}
	
	public String toString(){
		if (operation.equals("-enc")){
			return "encryption";
		}
		else if(operation.equals("-dec")){
			return "decryption";
		}
		else{
			throw new IllegalArgumentException("No constant with operation " + operation + " found in toString");
		}
	}
}

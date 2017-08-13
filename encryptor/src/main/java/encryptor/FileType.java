package encryptor;

import lombok.Getter;

public @Getter enum FileType {
	FILE("-f"), DIR("-dir");

	private String type;
	

	private FileType(String ft) {
		this.type = ft;
		
	}

	public static FileType fromString(String ft) {
		for (FileType FT : FileType.values()) {
			if (FT.type.equals(ft)) {
				return FT;
			}
		}
		throw new IllegalArgumentException("No constant with type " + ft + " found");
	}
}

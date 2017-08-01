package encryptor;

import lombok.Getter;

public @Getter enum FileType {
	FILE(1), DIR(0);

	private int type;
	

	private FileType(int ft) {
		this.type = ft;
		
	}

	public static FileType fromInt(int ft) {
		for (FileType FT : FileType.values()) {
			if (FT.type == ft) {
				return FT;
			}
		}
		throw new IllegalArgumentException("No constant with type " + ft + " found");
	}
}

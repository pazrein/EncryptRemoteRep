package Algorithms;

import java.io.IOException;

public class XOR extends AlgorithmAbstract {
	@Override
	public byte[] encrypt(byte key, byte[] array) throws IOException {
		writeKeysToFile(key, (byte) 0, 0, 0);
		return super.encrypt(key, array);
	}

	@Override
	public byte encryptPerByte(byte b, byte key) {
		return (byte) (b ^ key);
	}

	@Override
	public byte decryptPerByte(byte b, byte key) {
		return encryptPerByte(b, key);
	}

}

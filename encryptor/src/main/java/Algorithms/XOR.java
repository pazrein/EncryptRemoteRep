package Algorithms;

import java.io.IOException;

import encryptor.FileOperation;

public class XOR extends AlgorithmAbstract {
	public XOR(FileOperation operation, encryptor.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	
	public XOR (FileOperation operation, byte key, byte secKey,int algo1,int algo2) {
		super(operation, key, secKey, algo1, algo2);
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

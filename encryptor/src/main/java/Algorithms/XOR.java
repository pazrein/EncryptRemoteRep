package Algorithms;

import java.io.IOException;
import java.util.List;

import encryptor.FileOperation;

public class XOR extends AlgorithmAbstract {
	public XOR(FileOperation operation, Algorithms.AlgoFields AF) throws IOException {
		super(operation, AF);
	}
	

	public XOR(FileOperation operation,List <Byte> keys, List<Integer> algos){
		super(operation, keys, algos);
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

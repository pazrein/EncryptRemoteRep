package encryptor;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Paths;

import Algorithms.CaesarCipher;
import Algorithms.DoubleAlgo;
import Algorithms.Multiplication;
import Algorithms.Reverse;
import Algorithms.Split;
import Algorithms.XOR;
import lombok.Data;

public @Data class Decryption extends AbstractFileManipulation {
	private static InputReader in = new InputReader();
	private String[] pathArr;

	public Decryption(String path) throws IOException {
		super(path);
		this.pathArr = path.split("\\.");
		this.outputFile = Paths.get(pathArr[0] + "_decrypted." + pathArr[1]);

		FileInputStream fos = new FileInputStream("key.bin");
		DataInputStream dos = new DataInputStream(fos);
		key = dos.readByte();
		secKey = dos.readByte();
		algoRand1 = dos.readInt();
		algoRand2 = dos.readInt();
		dos.close();
		fos.close();
	}

	void chooseDecAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 7)) {
			System.out.println("Choose your decryption algorithm *YOU USED* from the following list"
					+ chooseAlgoFromListStringToPrint());

			algorithm = in.nextInt();
			if (!firstTry) {
				System.out.println("Pick a number between 1 to 6 only");
			}
			firstTry = false;
		}
	}

	void chooseAlgoToActivate(int algorithm) {
		switch (algorithm) {
		case 1:
			CaesarCipher cc = new CaesarCipher();
			fileArray = cc.decrypt(key, fileArray);
			break;
		case 2:
			XOR x = new XOR();
			fileArray = x.decrypt(key, fileArray);
			break;
		case 3:
			Multiplication m = new Multiplication();
			fileArray = m.decrypt(key, fileArray);
			break;
		case 4:
			DoubleAlgo d = new DoubleAlgo();
			fileArray = d.decrypt(key, secKey, algoRand1, algoRand2, fileArray);
			break;
		case 5:
			Reverse r = new Reverse();
			fileArray = r.decrypt(key, algoRand1, fileArray);
			break;
		case 6:
			Split s = new Split();
			fileArray = s.decrypt(key, secKey, algoRand1, algoRand2, fileArray);
			break;

		default:
			break;
		}
	}

	void decrypt() {
		chooseAlgoToActivate(algorithm);
		System.out.println("Decrypting.. ");
	}

}

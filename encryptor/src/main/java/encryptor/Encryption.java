package encryptor;

import java.io.IOException;
import java.nio.file.Paths;
import Algorithms.CaesarCipher;
import Algorithms.DoubleAlgo;
import Algorithms.Multiplication;
import Algorithms.Reverse;
import Algorithms.Split;
import Algorithms.XOR;
import encryptor.InputReader;
import lombok.Data;

public @Data class Encryption extends AbstractFileManipulation {
	private static InputReader in = new InputReader();

	public Encryption(String path) throws IOException {
		super(path);
		this.outputFile = Paths.get(path + ".encrypted");
	}

	void chooseEncAlgo() {
		boolean firstTry = true;
		while (!(algorithm > 0 && algorithm < 7)) {
			System.out.println(
					"Choose your encryption algorithm from the following list" + chooseAlgoFromListStringToPrint());
			algorithm = in.nextInt();
			if (!firstTry) {
				System.out.println("Pick a number between 1 to 6 only");
			}
			firstTry = false;
		}
	}

	void chooseAlgoToActivate(int algorithm) throws IOException {
		switch (algorithm) {
		case 1:
			CaesarCipher cc = new CaesarCipher();
			fileArray = cc.encrypt(key, fileArray);
			break;
		case 2:
			XOR x = new XOR();
			fileArray = x.encrypt(key, fileArray);
			break;
		case 3:
			Multiplication m = new Multiplication();
			fileArray = m.encrypt(key, fileArray);
			break;
		case 4:
			DoubleAlgo d = new DoubleAlgo();
			fileArray = d.encrypt(key, fileArray);
			break;
		case 5:
			Reverse r = new Reverse();
			fileArray = r.encrypt(key, fileArray);
			break;
		case 6:
			Split s = new Split();
			fileArray = s.encrypt(key, fileArray);
			break;

		default:
			break;
		}
	}

	void encrypt() throws IOException {
		chooseAlgoToActivate(algorithm);
		System.out.println("Encrypting.. ");
	}

}
package encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Algorithms.CaesarCipher;
import Algorithms.DoubleAlgo;
import Algorithms.Multiplication;
import Algorithms.Reverse;
import Algorithms.Split;
import Algorithms.XOR;
import lombok.Data;

public @Data abstract class AbstractFileManipulation {
	String path;
	byte key;
	byte secKey;
	Path inputFile;
	Path outputFile;
	byte[] fileArray;
	int algorithm;
	int algoRand1;
	int algoRand2;
	int MAX_VALUE = 127;
	int MIN_VALUE = -128;

	public AbstractFileManipulation(String path) throws IOException {
		this.path = path;
		this.inputFile = Paths.get(path);
		this.fileArray = Files.readAllBytes(inputFile);
	}

	String chooseAlgoFromListStringToPrint() {
		return "\n 1 for caesarCipher" + "\n 2 for XOR " + "\n 3 for Multiplication" + "\n 4 for Double"
				+ "\n 5 for Reverse" + "\n 6 for Split";
	}

	void write() throws IOException {
		Files.write(outputFile, fileArray);
		System.out.println("Done");
	}

}

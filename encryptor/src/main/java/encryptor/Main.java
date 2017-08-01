package encryptor;


import java.io.IOException;


import encryptor.InputReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		UserInput UI = new UserInput();
		if(!UI.isValidPath()){
			throw new IllegalArgumentException("Failed to enter path more than 3 times");
		}
		UI.printFilePath();
		
		Converter c = new Converter(UI);
		c.convert(UI);
	}


	


}

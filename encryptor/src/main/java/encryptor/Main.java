package encryptor;


import java.io.IOException;


import encryptor.InputReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		UserInput UI = new UserInput();
		UI.getInput();
		
		Converter c = new Converter(UI);
		c.convert(UI);
	}


	


}

package encryptor;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class isValidFilePathTest {

	@Test
	public void test() throws IOException {
		Encryptor enc = new Encryptor();
		String path = "C:\\Users\\Paz\\git\\LocalEncryptorRepository\\encryptor\\pom.xml";
		assertTrue(Encryptor.isValidFilePath(path).equals(path));
	}

}

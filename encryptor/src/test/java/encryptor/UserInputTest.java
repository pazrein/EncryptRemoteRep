package encryptor;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserInputTest {
	
	UserInput UI;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream oldStdOut = System.out;
	private final PrintStream oldStdErr = System.err;
	private final InputStream oldStdIn = System.in;
		
	@Before
	public void initlize(){
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
		UI = new UserInput();
	}

	@Test
	public void getDataFeaturesTest() {
		String data = "-enc" + "\n-f" + "\n-s";
		UserInput.in.SetReader(new ByteArrayInputStream(data.getBytes()));
		UI.getDataFeatures();

		assertThat(UI.getOperation(), is(equalTo(FileOperation.encryption)));
		assertThat(UI.getType(), is(equalTo(FileType.FILE)));
		assertThat(UI.getMethod(), is(equalTo(SynchronizationMethod.SYNC)));
	}
	
	@Test
	public void getPathFromUserTest(){
		String data = "filePath";
		UserInput.in.SetReader(new ByteArrayInputStream(data.getBytes()));
		UI.getPathFromUser();
		assertThat(UI.getPath(), is(equalTo(data)));
		assertThat(UI.getPath(), is (not(equalTo("MESSI"))));
	}
	
	@Test
	public void isValidPathTest(){
		boolean isValid;
		
		String data = "-enc" + "\n-f" + "\n-s";
		UserInput.in.SetReader(new ByteArrayInputStream(data.getBytes()));
		UI.getDataFeatures();
		
		data = "thierry henry" + "\ntheo walcott" + "\naaron ramsey";
		UserInput.in.SetReader(new ByteArrayInputStream(data.getBytes()));
		isValid = UI.isValidPath();
		assertThat(isValid, is(equalTo(false)));
		
		data = "Obama.jpg";
		UserInput.in.SetReader(new ByteArrayInputStream(data.getBytes()));
		isValid = UI.isValidPath();
		assertThat(isValid, is(equalTo(true)));
		
		
	}
	
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();
	
	@Test
	public void getInputTest(){
		exceptionGrabber.expect(IllegalArgumentException.class);
		exceptionGrabber.expectMessage("Failed to enter path more than 3 times");
		
		String data = "-enc" + "\n-f" + "\n-s" + "thierry henry" + "\ntheo walcott" + "\naaron ramsey";
		UserInput.in.SetReader(new ByteArrayInputStream(data.getBytes()));
		
		UI.getInput();

	}

	
	@After
	public void cleanUpStreams() {
	    System.setOut(oldStdOut);
	    System.setErr(oldStdErr);
	    System.setIn(oldStdIn);
	}
}

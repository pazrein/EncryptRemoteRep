package Algorithms;



import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import encryptor.FileOperation;


public class AlgoFieldsTest {
	
	AlgoFields AF;
	
	@Before
	public void initlize() throws IOException{
		AF = new AlgoFields(FileOperation.encryption);
	}

	@Test
	public void generateRandomByteInRange() {
		int res;
		int minVal,maxVal;
		
		minVal = 1;
		maxVal = 3;
		for (int i = 0; i < 100; i++) {
			res = AF.generateRandomByteInRange(minVal, maxVal);
			assertThat(res, is(both(greaterThanOrEqualTo(minVal)).and(lessThanOrEqualTo(maxVal))));
		}
		
		minVal = -128;
		maxVal = 127;
		for (int i = 0; i < 1000; i++) {
			res = AF.generateRandomByteInRange(minVal, maxVal);
			assertThat(res, is(both(greaterThanOrEqualTo(minVal)).and(lessThanOrEqualTo(maxVal))));
			
		}
	}
	

	
	

}

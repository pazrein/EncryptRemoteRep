package Algorithms;



import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;


import encryptor.FileOperation;
	

public class AlgoFieldsTest {
	
	AlgoFields AF;
	final int i1 = 15;
	final byte b1 = 69, b2 = 96;
	
	private void createFile_keybin(boolean hasAlgos) throws IOException{

		FileOutputStream fos = new FileOutputStream("key.bin");
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeInt(2);
		dos.writeByte(b1);
		dos.writeByte(b2);
		if(!hasAlgos){
			dos.writeInt(0);
		}
		else{
			dos.writeInt(1);
			dos.writeInt(i1);
		}
		
		dos.close();
		fos.close();
	}
	
	@Before
	public void initlize() throws IOException{
		AF = new AlgoFields(FileOperation.encryption);
	}
	
	@Test
	public void AlgoFieldsConstructorTest() throws IOException {
		AlgoFields AF_enc, AF_dec;

		
		 AF_enc =  new AlgoFields(FileOperation.encryption);
		 assertThat(AF_enc.getKeys().size(),is(equalTo(1)));
		 
		 createFile_keybin(false);
		 AF_dec =  new AlgoFields(FileOperation.decryption);
		 assertThat(AF_dec.getKeys(), contains(b1,b2));
		 
		 assertThat(AF_dec.getAlgos(), is(empty()));
		 
		 createFile_keybin(true);
		 AF_dec =  new AlgoFields(FileOperation.decryption);
		 assertThat(AF_dec.getAlgos(),  contains(i1));
		 
	}

	@Test
	public void generateRandomByteInRangeTest() {
		int res;
		int minVal,maxVal;
		
		minVal = 0;
		maxVal = 5;
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
	
	@Test
	public void chooseAlgorithmRandomlyTest(){
		int res;
		int minVal,maxVal;
		minVal = 1;
		maxVal = 3;
		res = AF. chooseAlgorithmRandomly();
		assertThat(res, is(both(greaterThanOrEqualTo(minVal)).and(lessThanOrEqualTo(maxVal))));
	}
	
	@Test
	public void generateRandomKeyTest(){
		int res;
		int minVal,maxVal;
		minVal = -128;
		maxVal = 127;
		res = AF. generateRandomKey();
		assertThat(res, is(both(greaterThanOrEqualTo(minVal)).and(lessThanOrEqualTo(maxVal))));
	}
	
	@Test
	public void addAlgosTest(){
		AF.addAlgos(4);
		assertThat(AF.getAlgos().size(),is(equalTo(4)));
	}
	
	@Test
	public void addKeysTest(){
		AF.addKeys(4);
		assertThat(AF.getKeys().size(),is(equalTo(5)));
	}
	
	@Test
	public void addFieldsTest(){
		AF.addFields(9, 9);
		assertThat(AF.getAlgos().size(),is(equalTo(9)));
		assertThat(AF.getKeys().size(),is(equalTo(10)));
		
	}
	
	@Test
	public void writeToFileTest() throws IOException{
		int listSize;
		FileInputStream fos = new FileInputStream("key.bin");
		DataInputStream dos = new DataInputStream(fos);
		 
		List<Byte> keys = new ArrayList<Byte>() {
			 {
			    add(b1);
			    add(b2);
			 }
			};
		
		AF.setKeys(keys);

		List<Integer> algos = new ArrayList<Integer>() {
			 {
			    add(i1);
			 }
			};
		
		AF.setAlgos(algos);
		
		//Function To Test
		AF.writeToFile();
		//
		
		listSize = dos.readInt();
		assertThat(listSize, is(equalTo(2)));	
		assertThat(dos.readByte(), is(equalTo(b1)));
		assertThat(dos.readByte(), is(equalTo(b2)));
		listSize = dos.readInt();
		assertThat(listSize, is(equalTo(1)));	
		assertThat(dos.readInt(), is(equalTo(i1)));	


		dos.close();
		fos.close();
	}
	
	
	
	

}

package encryptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		tokenizer = null;
	}

	public String nextLine() throws IOException {
		return reader.readLine();
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public byte nextByte() {
		return (byte) Integer.parseInt(next());
	}
}

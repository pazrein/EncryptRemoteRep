package encryptor;

public enum SynchronizationMethod {
	SYNC(1), ASYNC(0);

	private int method;

	private SynchronizationMethod(int meth) {
		this.method = meth;
	}

	public static SynchronizationMethod fromInt(int meth) {
		for (SynchronizationMethod SM : SynchronizationMethod.values()) {
			if (SM.method == meth) {
				return SM;
			}
		}
		throw new IllegalArgumentException("No constant with method " + meth + " found");
	}
	
	public String toString(){
		if (method == 1){
			return "Sync";
		}
		else if(method == 0){
			return "ASync";
		}
		else{
			throw new IllegalArgumentException("No constant with method " + method + " found in toString");
		}
	}

}

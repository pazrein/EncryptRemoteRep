package encryptor;

public enum SynchronizationMethod {
	SYNC("-s"), ASYNC("-as");

	private String method;

	private SynchronizationMethod(String meth) {
		this.method = meth;
	}

	public static SynchronizationMethod fromFlag(String meth) {
		for (SynchronizationMethod SM : SynchronizationMethod.values()) {
			if (SM.method.equals(meth)) {
				return SM;
			}
		}
		throw new IllegalArgumentException("No constant with method " + meth + " found");
	}
	
	public String toString(){
		if (method.equals("-s")){
			return "Sync";
		}
		else if(method.equals("-as")){
			return "ASync";
		}
		else{
			throw new IllegalArgumentException("No constant with method " + method + " found in toString");
		}
	}

}

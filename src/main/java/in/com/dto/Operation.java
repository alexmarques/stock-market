package in.com.dto;

public enum Operation {
	
	BUY("B"), SELL("S");
	
	private String operation;
	
	private Operation(String operation) {
		this.operation = operation;
	}
	
	public String getOperationChar() {
		return operation;
	}
	
	public static Operation getByOperationChar(String c) {
		if(c == null || c.equals("")) {
			return null;
		}
		for(Operation o : Operation.values()) {
			if(c.equals(o.getOperationChar())) {
				return o;
			}
		}
		
		return null;
	}
	
	

}

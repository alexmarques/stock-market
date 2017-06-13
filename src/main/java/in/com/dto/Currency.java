package in.com.dto;

import java.io.Serializable;

public class Currency implements Serializable {
	
	private static final long serialVersionUID = -4148125271138827333L;
	
	private Long id;
	private String code;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Currency [id=" + id + ", code=" + code + "]";
	}
}
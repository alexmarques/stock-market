package in.com.dto;

import java.io.Serializable;

public class Entity implements Serializable {
	
	private static final long serialVersionUID = -3372496646700640906L;
	
	private Long id;
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + "]";
	}
}
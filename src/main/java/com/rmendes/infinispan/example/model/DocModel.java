package com.rmendes.infinispan.example.model;

import java.io.Serializable;

public class DocModel implements Serializable{
	
	private static final long serialVersionUID = -6396670807587326800L;

	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

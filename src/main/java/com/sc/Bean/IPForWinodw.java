package com.sc.Bean;

import java.io.Serializable;

public class IPForWinodw implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String ip;
	private String name;
	private String password;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

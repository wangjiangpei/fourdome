package com.fourdome.bean;

import cn.bmob.v3.BmobObject;

public class Btype extends BmobObject{
	private String bType;

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public Btype(String bType) {
		super();
		this.bType = bType;
	}

	@Override
	public String toString() {
		return "Btype [bType=" + bType + "]";
	}
}

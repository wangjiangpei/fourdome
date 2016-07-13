package com.fourdome.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Ltype extends BmobObject{
	private String Btype;
	private String pyteName;
	private BmobFile pytePicture;
	public String getBtype() {
		return Btype;
	}
	public void setBtype(String btype) {
		Btype = btype;
	}
	public String getPyteName() {
		return pyteName;
	}
	public void setPyteName(String pyteName) {
		this.pyteName = pyteName;
	}
	public BmobFile getPytePicture() {
		return pytePicture;
	}
	public void setPytePicture(BmobFile pytePicture) {
		this.pytePicture = pytePicture;
	}
	public Ltype(String btype, String pyteName, BmobFile pytePicture) {
		super();
		Btype = btype;
		this.pyteName = pyteName;
		this.pytePicture = pytePicture;
	}
	@Override
	public String toString() {
		return "Lbyte [Btype=" + Btype + ", pyteName=" + pyteName
				+ ", pytePicture=" + pytePicture + "]";
	}
	
}

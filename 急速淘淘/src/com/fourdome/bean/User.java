package com.fourdome.bean;


import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Mr.ек on 2016/6/23.
 */
public class User extends BmobUser {
    private BmobFile myHead;
    private ArrayList<String> collects;
    private String type;
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getCollects() {
        return collects;
    }

    public void setCollects(ArrayList<String> collects) {
        this.collects = collects;
    }

    public User() {
        super();
    }

	public BmobFile getMyHead() {
		return myHead;
	}

	public void setMyHead(BmobFile myHead) {
		this.myHead = myHead;
	}

  
  
}

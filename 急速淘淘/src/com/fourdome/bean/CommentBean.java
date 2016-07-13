package com.fourdome.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
/**
 * 评论bean对象
 * @author Administrator
 *
 */
public class CommentBean extends BmobObject implements Serializable{
	//评论者name
	private String name;
	//评论者头像
	private BmobFile head;
	//评论内容
	private String text;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public BmobFile getHead() {
		return head;
	}
	public void setHead(BmobFile head) {
		this.head = head;
	}
	@Override
	public String toString() {
		return "CommentBean [name=" + name + ", text=" + text + ", head="
				+ head + "]";
	}
	public CommentBean(String name, String text, BmobFile head) {
		super();
		this.name = name;
		this.text = text;
		this.head = head;
	}
	
}

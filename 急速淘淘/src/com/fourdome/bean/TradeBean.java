package com.fourdome.bean;

import java.io.Serializable;

/**
 * 商品信息Bean对象
 * @author Administrator
 *
 */
public class TradeBean implements Serializable{
	//商品名
	private String tradeName;
	//商品信息
	private String tradeShow;
	//商品价格
	private String tradeMoney;
	//大类型
	private String tradeTypeB;
	//小类型
	private TypeL tradeTypeL;
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeShow() {
		return tradeShow;
	}
	public void setTradeShow(String tradeShow) {
		this.tradeShow = tradeShow;
	}
	public String getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getTradeTypeB() {
		return tradeTypeB;
	}
	public void setTradeTypeB(String tradeTypeB) {
		this.tradeTypeB = tradeTypeB;
	}
	public TypeL getTradeTypeL() {
		return tradeTypeL;
	}
	public void setTradeTypeL(TypeL tradeTypeL) {
		this.tradeTypeL = tradeTypeL;
	}
	@Override
	public String toString() {
		return "TradeBean [tradeName=" + tradeName + ", tradeShow=" + tradeShow
				+ ", tradeMoney=" + tradeMoney + ", tradeTypeB=" + tradeTypeB
				+ ", tradeTypeL=" + tradeTypeL + "]";
	}
	
}

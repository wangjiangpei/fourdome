package com.fourdome.bean;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
/**
 * 商店Bean对象
 * @author Administrator
 *
 */
public class ShopBean extends BmobObject implements Serializable{
	//商店名
	private String shopName;
	//商店图片
	private BmobFile shopPicture;
	//商店介绍
	private String shopIntro;
	//商店信息
	private String shopShow;
	//商店实际位置
	private String shopAddress;
	//商店地理位置
	private BmobGeoPoint shopDistance;
	//派送范围(位置)
	private String sendRange;
	//派送范围(价格区间)
	private Integer sendMonney;
	//好评人数 
	private Integer shopHotCount;
	//好评度
	private Integer shopHot;
	//评论
	
	private List<CommentBean> commentBean;
	public String getShopIntro() {
		return shopIntro;
	}
	public void setShopIntro(String shopIntro) {
		this.shopIntro = shopIntro;
	}
	public String getShopShow() {
		return shopShow;
	}
	public void setShopShow(String shopShow) {
		this.shopShow = shopShow;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public BmobFile getShopPicture() {
		return shopPicture;
	}
	public void setShopPicture(BmobFile shopPicture) {
		this.shopPicture = shopPicture;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public BmobGeoPoint getShopDistance() {
		return shopDistance;
	}
	public void setShopDistance(BmobGeoPoint shopDistance) {
		this.shopDistance = shopDistance;
	}
	public String getSendRange() {
		return sendRange;
	}
	public void setSendRange(String sendRange) {
		this.sendRange = sendRange;
	}
	public Integer getSendMonney() {
		return sendMonney;
	}
	public void setSendMonney(Integer sendMonney) {
		this.sendMonney = sendMonney;
	}
	public Integer getShopHotCount() {
		return shopHotCount;
	}
	public void setShopHotCount(Integer shopHotCount) {
		this.shopHotCount = shopHotCount;
	}
	public Integer getShopHot() {
		return shopHot;
	}
	public void setShopHot(Integer shopHot) {
		this.shopHot = shopHot;
	}
	public List<CommentBean> getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(List<CommentBean> commentBean) {
		this.commentBean = commentBean;
	}
	public ShopBean(String shopName, BmobFile shopPicture, String shopAddress,
			BmobGeoPoint shopDistance, String sendRange, Integer sendMonney,
			Integer shopHotCount, Integer shopHot, List<CommentBean> commentBean) {
		super();
		this.shopName = shopName;
		this.shopPicture = shopPicture;
		this.shopAddress = shopAddress;
		this.shopDistance = shopDistance;
		this.sendRange = sendRange;
		this.sendMonney = sendMonney;
		this.shopHotCount = shopHotCount;
		this.shopHot = shopHot;
		this.commentBean = commentBean;
	}
	@Override
	public String toString() {
		return "ShopBean [shopName=" + shopName + ", shopPicture="
				+ shopPicture + ", shopAddress=" + shopAddress
				+ ", shopDistance=" + shopDistance + ", sendRange=" + sendRange
				+ ", sendMonney=" + sendMonney + ", shopHotCount="
				+ shopHotCount + ", shopHot=" + shopHot + ", commentBean="
				+ commentBean + "]";
	}
	
	
}

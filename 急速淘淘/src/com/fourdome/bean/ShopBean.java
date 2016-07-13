package com.fourdome.bean;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
/**
 * �̵�Bean����
 * @author Administrator
 *
 */
public class ShopBean extends BmobObject implements Serializable{
	//�̵���
	private String shopName;
	//�̵�ͼƬ
	private BmobFile shopPicture;
	//�̵����
	private String shopIntro;
	//�̵���Ϣ
	private String shopShow;
	//�̵�ʵ��λ��
	private String shopAddress;
	//�̵����λ��
	private BmobGeoPoint shopDistance;
	//���ͷ�Χ(λ��)
	private String sendRange;
	//���ͷ�Χ(�۸�����)
	private Integer sendMonney;
	//�������� 
	private Integer shopHotCount;
	//������
	private Integer shopHot;
	//����
	
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

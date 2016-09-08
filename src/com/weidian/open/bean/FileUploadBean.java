package com.weidian.open.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="WIKI_ITEM_IMG_MAPPER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FileUploadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String shop_item_code;
	private String item_img_url;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "identity")  
	@Column(name = "id")  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "shop_item_code")  
	public String getShop_item_code() {
		return shop_item_code;
	}
	public void setShop_item_code(String shop_item_code) {
		this.shop_item_code = shop_item_code;
	}
	@Column(name = "item_img_url") 
	public String getItem_img_url() {
		return item_img_url;
	}
	public void setItem_img_url(String item_img_url) {
		this.item_img_url = item_img_url;
	}
	
}

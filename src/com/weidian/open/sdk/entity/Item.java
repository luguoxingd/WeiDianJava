package com.weidian.open.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

  private int stock;//库存
  private int istop;//是否制定
  private int sold;//未知字段

  private String price;//商品价格
  private String status;//状态

  private String[] imgs;//图片
  private String[] thumb_imgs;//图片描述=titles

  private Sku[] skus;//型号
  private Cate[] cates;

  @JsonProperty("itemid")
  private String itemId;

  @JsonProperty("item_name")
  private String itemName;//商品名称

  @JsonProperty("seller_id")
  private String sellerId;//未知字段，非必须

  @JsonProperty("merchant_code")
  private String merchantCode;//商品编号

  @JsonProperty("fx_fee_rate")
  private String fxFeeRate;

  @JsonProperty("item_desc")
  private String itemDesc;//商品描述
  

  public Cate[] getCates() {
    return cates;
  }

  public void setCates(Cate[] cates) {
    this.cates = cates;
  }

  public String getItemDesc() {
    return itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getIstop() {
    return istop;
  }

  public void setIstop(int istop) {
    this.istop = istop;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public int getSold() {
    return sold;
  }

  public void setSold(int sold) {
    this.sold = sold;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String[] getImgs() {
    return imgs;
  }

  public void setImgs(String[] imgs) {
    this.imgs = imgs;
  }

  public String[] getThumb_imgs() {
    return thumb_imgs;
  }

  public void setThumb_imgs(String[] thumb_imgs) {
    this.thumb_imgs = thumb_imgs;
  }

  public Sku[] getSkus() {
    return skus;
  }

  public void setSkus(Sku[] skus) {
    this.skus = skus;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getMerchantCode() {
    return merchantCode;
  }

  public void setMerchantCode(String merchantCode) {
    this.merchantCode = merchantCode;
  }

  public String getFxFeeRate() {
    return fxFeeRate;
  }

  public void setFxFeeRate(String fxFeeRate) {
    this.fxFeeRate = fxFeeRate;
  }

}

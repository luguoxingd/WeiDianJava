package com.weidian.open.service;

import com.weidian.open.bean.FileUploadBean;

public interface FileUploadService {
	public void saveFileUrl(FileUploadBean bean);
	
	public FileUploadBean getFileImgByCode(String shop_item_code);
	
	public void updateFileUrl(FileUploadBean bean);
	
	public void addFileUrl(String sql);
	
	public void updateFileUrls(String sql);
}

package com.weidian.open.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.BaseDaoI;
import com.weidian.open.bean.FileUploadBean;
import com.weidian.open.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private BaseDaoI<FileUploadBean> fileDao ;
	
	@Override
	public void saveFileUrl(FileUploadBean bean)  {
		Serializable s =fileDao.save(bean);
		System.out.println(s);
	}
	
	@Override
	public FileUploadBean getFileImgByCode(String shop_item_code) {
		String hql="from FileUploadBean where shop_item_code=:shop_item_code";
		Map<String,Object> params= new HashMap<String, Object>();
		params.put("shop_item_code", shop_item_code);
		FileUploadBean bean = fileDao.get(hql, params);
		return bean;
		
	}

	@Override
	public void updateFileUrl(FileUploadBean bean) {
		fileDao.update(bean);
	}

	@Override
	public void addFileUrl(String sql) {
	}

	@Override
	public void updateFileUrls(String sql) {
		// TODO Auto-generated method stub
		
	}

}

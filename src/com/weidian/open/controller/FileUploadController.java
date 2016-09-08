package com.weidian.open.controller;

import com.db.CommonJdbc;
import com.weidian.open.bean.FileUploadBean;
import com.weidian.open.sdk.AbstractWeidianClient;
import com.weidian.open.sdk.DefaultWeidianClient;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.product.MediaUploadRequest;
import com.weidian.open.sdk.response.AbstractResponse;
import com.weidian.open.sdk.util.SystemConfig;
import com.weidian.open.sdk.util.SystemPropertyUtils;
import com.weidian.open.sdk.util.TokenTask;
import com.weidian.open.service.FileUploadService;
import com.widget.utils.EasyUIHelper;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FileUploadController {

	public static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	protected AbstractWeidianClient client = DefaultWeidianClient.getInstance();
	
	@Autowired
	private FileUploadService service;
	 
	@RequestMapping("/file/upload.do")
	public void fileMethod(@RequestParam(value = "media", required = false) MultipartFile[] files, HttpServletRequest request,HttpServletResponse res) throws IOException{
        JSONObject json =new JSONObject();
        String shop_item_code=request.getParameter("shop_item_code");//唯一item_code

		String path = request.getSession().getServletContext().getRealPath("upload");//获取服务器路径,临时保存数据

        List<String> urlList = new ArrayList<>();
        for (MultipartFile file : files) {
            JSONObject jsonFile = uploadFile(file, path);

            if (jsonFile.getJSONObject("status").getInt("status_code") == 0)
            urlList.add(jsonFile.getString("result"));
        }

        //JDBC
        CommonJdbc jdbc=new CommonJdbc();
		if (urlList.size()>0) {
            String sql;
            String urls;
			FileUploadBean fileBean=service.getFileImgByCode(shop_item_code);
			if (fileBean==null) {
				fileBean=new FileUploadBean();
                urls=StringUtils.join(urlList.toArray(),",") ;

				fileBean.setItem_img_url(urls);
				fileBean.setShop_item_code(shop_item_code);
//					service.saveFileUrl(fileBean);
				sql="insert into WIKI_ITEM_IMG_MAPPER(item_img_url,shop_item_code) values('"+urls+"','"+shop_item_code+"')";
			}else{
				String item_img_urls = fileBean.getItem_img_url();
                urlList.add(item_img_urls);
                urls = StringUtils.join(urlList.toArray(),",");
                fileBean.setItem_img_url(urls);
//				System.out.println(fileBean.getItem_img_url());
//					service.updateFileUrl(fileBean);
				sql="update WIKI_ITEM_IMG_MAPPER set item_img_url='"+urls+"' where id="+fileBean.getId();
			}
			LOGGER.info("执行SQL:"+sql);
            int i = jdbc.updateBySql(sql);

            if (i>0){
                LOGGER.debug("数据更新成功");
                json.put("result","success");
                //返回url
                json.put("urls","["+urls+"]");
            }else{
               json.put("result","fail");
            }

            //JSON返回
            EasyUIHelper.writeResponse(res, json.toString(),"UTF-8");
        }

	}

	private JSONObject uploadFile(MultipartFile file,String path){
		String fileName = file.getOriginalFilename();//获取文件名
		File inFile = new File(path, fileName);//创建文件对象

		try {
			file.transferTo(inFile);    //保存临时文件
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        byte[] data = null;
		try {
			FileInputStream inStream = new FileInputStream(inFile);
			data = new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
        String token = SystemPropertyUtils.getKeyValue(SystemConfig.TOKEN);
        MediaUploadRequest mRequest = new MediaUploadRequest(token, data);
        AbstractResponse response;
        try {
            response = client.multipart(mRequest);

            JSONObject json = JSONObject.fromObject(response.toString());

            Boolean fileDeleteFlag = inFile.delete();
            LOGGER.debug("文件删除:" + fileDeleteFlag);
            return json;

        } catch (OpenException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	
	@RequestMapping("/file/test.do")
	public ModelAndView testGetToken(){
		LOGGER.debug("获取token开始=====================");
		new TokenTask().setTokenStringTask();
		return new ModelAndView("/uploadImg/uploadFiles.jsp");
	}

	@RequestMapping("/file/index.do")
	public ModelAndView index(){
		LOGGER.info("Go Index:info");
		LOGGER.error("error");
		LOGGER.debug("debug");
		LOGGER.warn("warn");
		return new ModelAndView("/uploadImg/uploadFiles.jsp");
	}

}

package com.weidian.open.test;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestForControllor {

	public static final Logger LOGGER = LoggerFactory.getLogger(TestForControllor.class);
	public static void main(String[] args) {
		String jsonString="{'status':{'status_code':0,'status_reason':''},'result':'http://wd.geilicdn.com/vshop1470915983693-39005388.jpg?w=320&h=200'}'";
		JSONObject json =JSONObject.fromObject(jsonString);
	}
	
}


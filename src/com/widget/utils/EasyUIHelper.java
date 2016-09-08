/**
 * Project: PrototypeFrame
 * Source file: EasyUIHelper.java
 * Create At 2012-7-27 下午04:56:03
 * Create By 龚云
 */
package com.widget.utils;

import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EasyUIHelper {

	public static void writeResponse(HttpServletResponse res, String result)
			throws IOException {
		writeResponse(res, result, SystemConstant.DEFAULT_CHARSET);
	}

	public static void writeFormResponse(HttpServletResponse res,
			String result, String charset) throws IOException {
		result = StringEscapeUtils.escapeHtml(result);
		res.setContentType("text/html;charset="
				+ SystemConstant.DEFAULT_CHARSET);
		res.setCharacterEncoding(charset);
		res.getWriter().write(result);
	}

	/**
	 * 向response输出结果，若前台提交形式为IFrame而非ajax，则需要转义html代码
	 * 
	 * @param res
	 * @param result
	 *            结果
	 * @throws IOException
	 */
	public static void writeFormResponse(HttpServletResponse res, String result)
			throws IOException {
		writeFormResponse(res, result, SystemConstant.DEFAULT_CHARSET);
	}

	/**
	 * 向response输出结果
	 * 
	 * @param res
	 * @param result
	 *            结果
	 * @param charset
	 *            编码
	 * @throws IOException
	 */
	public static void writeResponse(HttpServletResponse res, String result,
			String charset) throws IOException {
		res.setContentType("text/plain;charset=" + charset);
		res.setCharacterEncoding(charset);
		res.getWriter().write(result);
	}

	
}

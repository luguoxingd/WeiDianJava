package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



//JDBC链接
public class CommonJdbc {
	private String driverName="com.mysql.jdbc.Driver";
	private String url="jdbc:mysql://blog.sptty.com:3306/ordering";
	private String  userId="ordering";
	private String  userPass="0rdering@Tianma!123";
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	// 获取链接
	public java.sql.Connection GetConnect() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, userId, userPass);
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		}
		return con;
	}
	
	//关闭链接
	public void CloseDB() {
		try {
			if (!con.isClosed()) {
				// 关闭RS
				try {
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// 关闭ST
				if (st != null)
					st.close();
				// 关闭con
				if (con != null)
					con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultSet selectBySql(String sql){
		con=this.GetConnect();
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	//根据sql查询信息并返回ArrayList
	public ArrayList<Object> queryBySql(String sql) {
		ArrayList<Object> list = new ArrayList<Object>();
		con = this.GetConnect();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData rsm = rs.getMetaData();
				int cols = rsm.getColumnCount();
				ArrayList<Object> l = new ArrayList<Object>();
				for (int i = 1; i <= cols; i++) {
					l.add(rs.getObject(i));
				}
				list.add(l);
			}
			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.CloseDB();
		}
		return null;
	}
	
	//根据SQL查询返回XML字符串
	public String queryXMLBySql(String sql){
		con = this.GetConnect();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			String xml=generateXML(rs);
			if (xml != null && xml!="")
				return xml;
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.CloseDB();
		}
		return null;
	}
	
	//update,insert,delete
	public int updateBySql(String sql){
		con=this.GetConnect();
		try{
			st=con.createStatement();
			int result =st.executeUpdate(sql);
			return result;
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			this.CloseDB();
		}
		return 0;
	}
	
	public String generateXML(final ResultSet rs) throws SQLException {
	   final StringBuffer buffer = new StringBuffer(1024 * 4);
	   if (rs == null)
	    return "";

	   buffer.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>\n"); 
	   buffer.append("<ResultSet>\n");

	   ResultSetMetaData rsmd = rs.getMetaData(); 
	   int colCount = rsmd.getColumnCount(); 
	   for (int id = 1; rs.next(); id++) {
	    buffer.append("\t<row id=\"").append(id).append("\">\n");
	    for (int i = 1; i <= colCount; i++) {
	     String type = rsmd.getColumnTypeName(i); 
	     buffer.append("\t\t<col name=\"" + rsmd.getColumnName(i)
	       + "\">");
	     buffer.append(getValue(rs, i, type));
	     buffer.append("</col>\n");
	    }
	    buffer.append("\t</row>\n");
	   }
	   buffer.append("</RowSet>");
	   rs.close();
	   return buffer.toString();
	}

	private String getValue(final ResultSet rs, int colNum, String type)
	    throws SQLException {
	   Object value = null;

	   if (type.equals("nchar") || type.equals("nvarchar"))
	    value = rs.getString(colNum);
	   else
	    value = rs.getObject(colNum);

	   if (value != null)
	    return value.toString().trim();
	   return "null";
	}
}

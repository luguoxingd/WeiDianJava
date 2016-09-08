<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${ctx}/resources/bootstrap-3.3.5/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

<script src="${ctx}/resources/jquery/jquery-3.1.0.js" type="text/javascript"></script>

<link rel="stylesheet" href="${ctx}/resources/bootstrap-3.3.5/css/bootstrap.min.css" type="text/css"/>


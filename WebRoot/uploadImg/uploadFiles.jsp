<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@	page import="com.weidian.open.sdk.util.SystemPropertyUtils,com.weidian.open.sdk.util.SystemConfig"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=basePath%>resources/bootstrap-3.3.5/js/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>

<script src="<%=basePath%>resources/jquery/jquery-3.1.0.js"
	type="text/javascript"></script>

<link rel="stylesheet"
	href="<%=basePath%>/resources/bootstrap-3.3.5/css/bootstrap.min.css"
	type="text/css" />

<title>图片上传添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="<%=basePath%>resources/bootstrap-file/fileinput.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>resources/bootstrap-file/zh.js"
	type="text/javascript"></script>
<script src="<%=basePath%>resources/bootstrap-table/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/bootstrap-table/bootstrap-table-zh-CN.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-file/fileinput.min.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>resources/bootstrap-table/bootstrap-table.css" type="text/css" />

</head>

<body>
	<form class="form-horizontal" id="uploadForm" name="uploadForm" enctype="multipart/form-data" >
		<fieldset>
			<div id="legend" class="">
				<legend class="">图片上传</legend>
			</div>
			<div class="form-group">
				<label for="token" class="col-sm-2 control-label">token</label>
				<div class="col-sm-10">
					<input type="text" name="token" id="token" style="width:50%"
						class="form-control" placeholder="token" value="<%=SystemPropertyUtils.getKeyValue(SystemConfig.TOKEN)%>">
				</div>
			</div>
			<div class="form-group">
				<label for="shop_item_code" class="col-sm-2 control-label">商品编码</label>
				<div class="col-sm-10">
					<input type="text" name="shop_item_code" id="shop_item_code" style="width:50%"
						class="form-control" placeholder="商品编号" >
				</div>
			</div>
			<div class="form-group" >
				<label for="media" class="col-sm-2 control-label">图片</label>
				<div class="col-sm-10">
					<div class="control-group" >
						<!-- File Upload -->
						<div class="controls">
							<input type="file" id="media" name="media" multiple=true >
							<div id="fileError" class="help-block"></div>
						</div>
						
					</div>
				</div>
			</div>
			
			<div class="panel-body" style="padding-bottom:0px;">
				<div id="toolbar" class="btn-group" style="display:none;">
					<button id="btn_add" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
					</button>
					<button id="btn_edit" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
					</button>
					<button id="btn_delete" type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
				</div>
				<table id="tb_departments"></table>
			</div>
			<script type="text/javascript">
			
				  $("#media").fileinput({
					language : 'zh', //设置语言
					allowedFileExtensions : [ 'jpg', 'png', 'gif' ],//接收的文件后缀
					previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
					elErrorContainer : "#fileError",
					browseClass : "btn btn-success",
					browseLabel : "查找文件",
					browseIcon : '<i ></i>',
					removeClass : "btn btn-danger",
					removeLabel : "删除",
			    	removeIcon : '<i ></i>',
				    uploadClass : "btn btn-info",
				    uploadLabel : "部署",
				    uploadIcon : '<i ></i>'
				});
			
				$("#uploadForm").submit(function(event) {
					var formData = new FormData(this); //这里用的是this，如果是Form的话需要Form[0]
					event.preventDefault(); //阻止当前提交事件，自行实现，否则会跳转
					//var grid = $('[data-role="pdGrid"]');
					$.ajax({
						 url :"<%=basePath%>file/upload.do",
						 type : 'POST',
						 data : formData,
						 contentType : false, //这两个参数需要被定义，否则报错
						 processData : false,
						 success : function(data) {
						 	alert(data);
                      	},
                      	error : function() {
								alert("summit error");
	                    }
					});
                });
                
				$(function() {
					//1.初始化Table
//					var oTable = new TableInit();
//					oTable.Init();
					//2.初始化Button的点击事件
//					var oButtonInit = new ButtonInit();
//					oButtonInit.Init();
					  
				});
				var TableInit = function() {
					var oTableInit = new Object();
					//初始化Table
					oTableInit.Init = function() {
						$('#tb_departments').bootstrapTable({
							url : '<%=basePath%>json/imgUploadHistory.json', //请求后台的URL（*）
							method : 'get', //请求方式（*）
							//toolbar : '#toolbar', //工具按钮用哪个容器
							striped : true, //是否显示行间隔色
							cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
							pagination : false, //是否显示分页（*）
							sortable : false, //是否启用排序
							sortOrder : "asc", //排序方式
							queryParams : "",//传递参数（*）
							sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
							pageNumber : 1, //初始化加载第一页，默认第一页
							pageSize : 10, //每页的记录行数（*）
							pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
							search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
							strictSearch : true,
							showColumns : false, //是否显示所有的列
							showRefresh : false, //是否显示刷新按钮
							minimumCountColumns : 2, //最少允许的列数
							clickToSelect : true, //是否启用点击选中行
							height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
							uniqueId : "ID", //每一行的唯一标识，一般为主键列
							showToggle : false, //是否显示详细视图和列表视图的切换按钮
							cardView : false, //是否显示详细视图
							detailView : false, //是否显示父子表
							columns : [ {
								checkbox : true
							}, {
								field : 'Name',
								title : '序列'
							}, {
								field : 'ParentName',
								title : '文件名称'
							}, {
								field : 'Level',
								title : '链接'
							}, {
								field : 'Desc',
								title : '上传日期'
							} ]
						});
					};

					//得到查询的参数
					oTableInit.queryParams = function(params) {
						var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
							limit : params.limit, //页面大小
							offset : params.offset, //页码
							departmentname : $("#txt_search_departmentname")
									.val(),
							statu : $("#txt_search_statu").val()
						};
						return temp;
					};
					return oTableInit;
				};
				var ButtonInit = function() {
					var oInit = new Object();
					var postdata = {};
					oInit.Init = function() {
						
					};
					return oInit;
				};
			</script>

		</fieldset>
	</form>
</body>
</html>

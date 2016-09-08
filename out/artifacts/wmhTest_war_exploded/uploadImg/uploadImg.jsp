<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>微店商品添加</title>

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
	<form class="form-horizontal" id="ffAdd" name="ffAdd"
		action="http://api.vdian.com/media/upload"
		enctype="multipart/form-data" method="post">
		<fieldset>
			<div id="legend" class="">
				<legend class="">商品添加</legend>
			</div>
			<div class="form-group" style="width:50%">
				<label for="itemName" class="col-sm-2 control-label">商品名称</label>
				<div class="col-sm-10">
					<input type="text" name="itemName" id="itemName"
						class="form-control" placeholder="商品名称" placeholder="商品名称">
				</div>
			</div>
			<div class="form-group">
				<div class="form-inline">
					<div class="form-group" style="width:50%;padding-left: 15px;">
						<label for="price" class="col-sm-2 control-label">价格</label>
						<div class="input-group" style="padding-left: 15px">
							<div class="input-group-addon">RMB</div>
							<input type="number" class="form-control" id="price" name="price"
								placeholder="价格">
							<div class="input-group-addon">.00</div>
						</div>
					</div>
					<div class="form-group" style="width:50%">
						<label for="stock" class="col-sm-2 control-label">库存</label>
						<div class="input-group" style="">
							<input type="number" class="form-control" id="stock" name="stock"
								placeholder="库存">
						</div>
					</div>
				</div>
			</div>
			<div class="form-group" style="width:50%">
				<label for="item_desc" class="col-sm-2 control-label">商品描述</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="5" name="item_desc"
						id="item_desc"></textarea>
				</div>
			</div>

			<div class="form-group" style="width:50%">
				<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
				<div class="col-sm-10">
					<div class="control-group" style="width:75%;">
						<!-- File Upload -->
						<div class="controls">
							<input type="file" id="media" name="media">
						</div>
					</div>
				</div>
			</div>
			<div class="panel-body" style="padding-bottom:0px;">
				<div id="toolbar" class="btn-group">
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
			<div class="form-group" style="width:50%">
				<label for="inputEmail3" class="col-sm-2 control-label"></label>
				<div class="col-sm-10">
					<button type="submit" class="btn btn-default" id='submit'>Submit</button>
				</div>
			</div>
			<script type="text/javascript">
			//初始化fileinput控件（第一次初始化）
			function initFileInput(ctrlName, uploadUrl) {    
			    var control = $('#' + ctrlName); 
				control.fileinput({
			        language: 'zh', //设置语言
			        uploadUrl: uploadUrl, //上传的地址
			        allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
			        showUpload: false, //是否显示上传按钮
			        showCaption: false,//是否显示标题
			        browseClass: "btn btn-primary", //按钮样式             
			        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
			    });
			    
			  // control.parent().append($('#submit'))
			}
			
			initFileInput("media", "<%=basePath%>/file/upload.do");

			// 文件上传框
			$('file-Portrait1').each(
				function() {
					var imageurl = $(this).attr("value");
					if (imageurl) {
						var op = $
								.extend(
										{
											initialPreview : [ // 预览图片的设置
													"<img src='" + imageurl + "' class='file-preview-image'>", ]
										}, projectfileoptions);

						$(this).fileinput(op);
					} else {
						$(this).fileinput(projectfileoptions);
					}
				});

				$(function() {
					//1.初始化Table
					var oTable = new TableInit();
					oTable.Init();
					//2.初始化Button的点击事件
					var oButtonInit = new ButtonInit();
					oButtonInit.Init();
				});
				var TableInit = function() {
					var oTableInit = new Object();
					//初始化Table
					oTableInit.Init = function() {
						$('#tb_departments').bootstrapTable({
							url : '', //请求后台的URL（*）
							method : 'get', //请求方式（*）
							toolbar : '#toolbar', //工具按钮用哪个容器
							striped : true, //是否显示行间隔色
							cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
							pagination : false, //是否显示分页（*）
							sortable : false, //是否启用排序
							sortOrder : "asc", //排序方式
							queryParams : oTableInit.queryParams,//传递参数（*）
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
								title : '部门名称'
							}, {
								field : 'ParentName',
								title : '上级部门'
							}, {
								field : 'Level',
								title : '部门级别'
							}, {
								field : 'Desc',
								title : '描述'
							}, ]
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
						$('#btn_add').click(
								function() {
									var selectContent = $tableLeft
											.bootstrapTable('getSelections');
									$tableRight.bootstrapTable("append",
											selectContent);
									var selects = $tableLeft
											.bootstrapTable('getSelections');
									SKUNo = $.map(selects, function(row) {
										return row.SKUNo;
									});

									$tableLeft.bootstrapTable('remove', {
										field : 'SKUNo',
										values : SKUNo
									});
								});
						$('#btn_edit').click(
								function() {
									var selectContent = $tableLeft
											.bootstrapTable('getSelections');
									$tableRight.bootstrapTable("append",
											selectContent);
									var selects = $tableLeft
											.bootstrapTable('getSelections');
									SKUNo = $.map(selects, function(row) {
										return row.SKUNo;
									});

									$tableLeft.bootstrapTable('remove', {
										field : 'SKUNo',
										values : SKUNo
									});
								});
						$('#btn_delete').click(
								function() {
									var selectContent = $tableLeft
											.bootstrapTable('getSelections');
									$tableRight.bootstrapTable("append",
											selectContent);
									var selects = $tableLeft
											.bootstrapTable('getSelections');
									SKUNo = $.map(selects, function(row) {
										return row.SKUNo;
									});

									$tableLeft.bootstrapTable('remove', {
										field : 'SKUNo',
										values : SKUNo
									});
								});
					};
					return oInit;
				};
			</script>

		</fieldset>
	</form>
</body>
</html>

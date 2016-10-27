<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/24
  Time: 15:32
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<%--模板的头--%>
<%@ include file="./../../admin/common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../../admin/common/left.jsp" %>
<div class="maincontent row">
  <ul class="breadcrumb">
    <li class="active">设备信息管理</li>
  </ul>
  <div class="col-sm-12">
  <div id="toolbar" class="btn-group">
    <button type="button" class="btn btn-default eq_add" title="添加">
      <i class="glyphicon glyphicon-plus"></i>
    </button>
    <button type="button" class="btn btn-default eq_delete" title="删除选中">
      <i class="glyphicon glyphicon-trash"></i>
    </button>
    <button type="button" class="btn btn-default eq_export" title="导出数据信息">
      <i class="glyphicon glyphicon-export"></i>
    </button>
  </div>
  <table id="table_equipment">
  </table>
    </div>
</div>
<script type="text/javascript">
  seajs.use(basePath +"/views/equipmentmanage/index/equipment_index");
</script>
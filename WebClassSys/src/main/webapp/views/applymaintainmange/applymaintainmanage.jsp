<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/15
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<style>
  .modal-body{
    padding: 10px;
  }
  .modal-footer{
    margin-top: 0px;
    padding: 10px;
  }
  .modal-header{
    padding: 10px;
  }
</style>

<%--模板的头--%>
<%@ include file="./../../admin/common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../../admin/common/left.jsp" %>
<div class="maincontent row">
  <ul class="breadcrumb">
    <li class="active">报修信息管理</li>
  </ul>
  <div class="col-sm-12">
    <form class="form-inline"  style="margin-bottom: 10px" role="form">
      <div class="form-group">
        <label class="control-label">维修类型:</label>
        <select name="type" class="form-control ">
          <option value="-1">全部</option>
        </select>
      </div>
      &nbsp;&nbsp;&nbsp;
      <div class="form-group">
        <label class="control-label">紧急程度:</label>
        <select name="uergencyState" class="form-control">
          <option value="-1">全部</option>
        </select>
      </div>
      &nbsp;&nbsp;&nbsp;
      <div class="form-group">
        <label class="control-label">当前状态:</label>
        <select name="state" class="form-control">
          <option value="-1">全部</option>
          <option value="1">申请中</option>
          <option value="2">处理中</option>
          <option value="3">处理完成</option>
        </select>
      </div>
      &nbsp;&nbsp;&nbsp;
      <div class="form-group">
        <input class="form-control search_text" title="搜索..." placeholder="搜索..." >
      </div>
      &nbsp;&nbsp;&nbsp;
      <button type="button" title="搜搜.." class="btn btn-default btn-primary applymaintain_btn_search">搜索</button>
      <button type="button" title="导出excel" class="btn btn-default btn-primary applymaintain_btn_export">导出excel</button>
    </form>
    <table id="table_apply">
    </table>
  </div>
</div>
<%@ include file="./../../admin/common/footter.jsp" %>
<script type="text/javascript">
  seajs.use(basePath + "/views/applymaintainmange/applymaintainindex/applymaintainindex");
</script>

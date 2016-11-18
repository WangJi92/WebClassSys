<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/18
  Time: 13:30
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
    <li class="active">教室申请</li>
  </ul>
  <div class="col-sm-12">
    <form id="dialog_form" class="form-inline"  style="margin-bottom: 10px" role="form">
      <div class="form-body">
        <div class="form-group">
          <label class="control-label">当前状态:</label>
          <select name="state" class="form-control">
            <option value="-1">全部</option>
          </select>
        </div>
        &nbsp;&nbsp;&nbsp;
        <div class="form-group">
          <input class="form-control search_text" title="搜索..." placeholder="搜索..." >
        </div>
        &nbsp;&nbsp;&nbsp;
        <button type="button" title="搜搜.." class="btn btn-default btn-primary applyclassroom_btn_search">搜索</button>
        &nbsp;&nbsp;&nbsp;
        <button type="button" title="导出excel" class="btn btn-default btn-primary apply_export">导出excel</button>
      </div>
    </form>
    <table id="table_apply">
    </table>
  </div>
</div>
<%@ include file="./../../admin/common/footter.jsp" %>
<script type="text/javascript">
  seajs.use(basePath + "/views/applyclassroommanage/applyclassroomindex/applyclassroomindex",function(data){
    data.initEvent();
  });
</script>
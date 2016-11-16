<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/16
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../../common/common.jsp"%>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<%--模板的头--%>
<%@ include file="./../common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../common/left.jsp" %>
<div class="maincontent row">
  <ul class="breadcrumb">
    <li class="active">报修申请</li>
  </ul>
  <div class="col-sm-12">
    <form id="dialog_form" class="form-inline" role="form">
      <div class="form-body">
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
          <label class="control-label">报修人:</label>
          <select name="applyPeople" class="form-control">
            <option value="-1">全部</option>
            <option value="1">我的</option>
            <option value="2">其他</option>
          </select>
        </div>
        &nbsp;&nbsp;&nbsp;
        <div class="form-group">
          <input class="form-control search_text" title="搜索..." placeholder="教室名称 报修详情..." >
        </div>
        &nbsp;&nbsp;&nbsp;
        <button type="button" title="搜搜.." class="btn btn-default btn-primary applymaintain_btn_search">搜索</button>
     </div>
    </form>
    <table id="table_apply">
    </table>
  </div>
</div>
<%@ include file="./../common/footter.jsp" %>

<script type="text/javascript">
  seajs.use(basePath + "/user/applymainList/js/applymainList",function(data){
    data.initEvent();
  });
</script>


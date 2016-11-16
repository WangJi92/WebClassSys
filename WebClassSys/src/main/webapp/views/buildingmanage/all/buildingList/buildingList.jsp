<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/2

--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./../../../../common/common.jsp" %>
<%--模板的头--%>
<%@ include file="./../../../../admin/common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../../../../admin/common/left.jsp" %>
<script src="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.css" type="text/css">
<script src="<%=baseUrl%>/libs/bootstrap-paginator/bootstrap-paginator.js"></script>
<div class="maincontent row">
  <ul class="breadcrumb">
    <li class="active">楼宇信息列表</li>
  </ul>
  <div class="col-sm-12">
  <form class="form-inline" style="margin-bottom: 20px" role="form">
    <div class="form-group">
      <input type="text" class="form-control" id="buildingsearch" placeholder="请输入楼宇名称...">
    </div>
    <button type="button" class="btn btn-default btn-primary btn_search">搜索</button>
  </form>
  </div>

  <div class="col-sm-12">
    <ul class="list-group buildingList">

    </ul>
    <ul id="pagination"></ul>
  </div>

 </div>
<%@ include file="./../../../../admin/common/footter.jsp" %>
<script type="text/javascript">
  seajs.use(basePath + "/views/buildingmanage/all/buildingList/js/buildingList");
</script>

<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/17
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<script src="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<%--<script src="<%=baseUrl%>/libs/fancybox/source/helpers/jquery.fancybox-thumbs.js" type="text/javascript"></script>--%>
<script src="<%=baseUrl%>/libs/fancybox/source/helpers/jquery.fancybox-buttons.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.css" type="text/css">
<%--<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/helpers/jquery.fancybox-thumbs.css">--%>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/helpers/jquery.fancybox-buttons.css">

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
        <li class="active">教学楼信息</li>
    </ul>
    <div class="col-sm-12">
        <form class="form-inline" style="margin-bottom: 10px" role="form">
            <div class="form-group">
                <input class="form-control" id="buildingName" name="buildingName" title="教学楼名称" placeholder="教学楼名称">
            </div>
            &nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-default btn-primary building_btn_search">搜索</button>
        </form>
        <table id="buildingInfoTable">
        </table>
    </div>
</div>
<%@ include file="./../common/footter.jsp" %>
<script>
    seajs.use(basePath + "/user/buildingInfo/buildingInfoIndex/buildingInfoIndex", function (data) {
        data.initEvent();
    });
</script>

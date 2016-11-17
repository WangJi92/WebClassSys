<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/17
  Time: 14:49
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
<%
    /*这里是通过课程表信息点击教室名称进行查看当前教室的具体信息*/
    String classroomName = request.getParameter("classRoomName");
    if(StringUtils.isEmpty(classroomName)){
        classroomName="";
    }
%>
<div class="maincontent row">
    <ul class="breadcrumb">
        <li class="active">教室信息</li>
    </ul>
    <div class="col-sm-12">
        <form class="form-inline" style="margin-bottom: 10px" role="form">
            &nbsp;&nbsp;&nbsp;
            <div class="form-group">
                <label class="control-label">教室内型:</label>
                <select name="classType" class="form-control">
                    <option value="-1">全部</option>
                </select>
            </div>
            &nbsp;&nbsp;&nbsp;
            <div class="form-group">
                <input class="form-control"value="<%=classroomName%>" id="classRoomName" name="classRoomName" title="教室名称..." placeholder="教室名称">
            </div>
            &nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-default btn-primary classroom_btn_search">搜索</button>
        </form>
        <input type="hidden" class="buildingIndexcode" value="<%=request.getParameter("buildingIndexcode")%>">

        <table id="classroomtable">
        </table>
    </div>
</div>
<%@ include file="./../common/footter.jsp" %>
<script>
    seajs.use(basePath + "/user/classroomtableInfo/classroomtableInfoIndex/classroomtableInfoIndex", function (data) {
        data.initEvent();
    });
</script>

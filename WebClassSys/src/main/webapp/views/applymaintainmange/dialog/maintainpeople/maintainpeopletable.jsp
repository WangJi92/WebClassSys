<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/16
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../../../../common/common.jsp" %>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<table id="table_user">
</table>
<script>
  seajs.use(basePath+ "/views/applymaintainmange/dialog/maintainpeople/js/peopletable",function(table){
    table.initEvent();
  });
</script>
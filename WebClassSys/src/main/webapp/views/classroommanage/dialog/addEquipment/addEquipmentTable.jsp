<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/10
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="./../../../../common/common.jsp" %>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript"></script>
<div>

  <table id="table_equipment">
  </table>
</div>
<script>
  seajs.use(basePath+"/views/classroommanage/dialog/addEquipment/js/addEquipment",function(addEquipment){
    addEquipment.initEvent();
  });
</script>
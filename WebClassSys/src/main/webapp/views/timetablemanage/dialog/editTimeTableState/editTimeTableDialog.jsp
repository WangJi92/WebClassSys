<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/18
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../../../../common/common.jsp" %>
<div class="col-sm-12 col-xs-12">
  <form id="dialog_form" class="form-horizontal" role="form">
    <div class="form-body">
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>教室名称
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="classRoomNameEdit" class="form-control " type="text" placeholder="教室名称">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>课程时间
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="whichLesson" class="form-control " type="text" placeholder="课程时间">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>使用类型
        </div>
        <div class="col-md-8  col-xs-9">
          <select name="useType" class="form-control">
          </select>
        </div>
      </div>
    </div>
  </form>
</div>
<script>
  seajs.use(basePath + "/views/timetablemanage/dialog/editTimeTableState/js/editTimeTableDialog",function(data){
    data.initEvent();
  });
</script>

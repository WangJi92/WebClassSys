<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/9
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="./../../../../common/common.jsp" %>
<script id="timetable_templage" type="text/html">
  <ul class="list-group">
    <li class="list-group-item">教室名称：{{classRoomName}}</li>
    <li class="list-group-item">周次：{{weekStr}}</li>
    <li class="list-group-item">星期：{{dayStr}}</li>
    <li class="list-group-item">课程时间：{{lessonStr}}</li>
  </ul>
  <div class="col-md-12">
    教室状态:<select  name="type" class="from-control-inline" style="width:50%">
    <option value="{{type}}" selected>{{typeStr}}</option>
  </select>
  </div>
</script>
<div class="edittimetable">

</div>


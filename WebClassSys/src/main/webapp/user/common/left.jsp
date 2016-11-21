
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<div class="container-fluid all">
  <div class="sidebar">
    <ul class="nav">
      <li><a href="#1">首页</a></li>
      <li><a href="<%=baseUrl%>/user/buildingInfo/buidlingInfo.jsp">教学楼信息</a></li>
      <li><a href="<%=baseUrl%>/user/classroomtableInfo/classroomtableInfo.jsp">教室信息</a></li>
      <li><a href="<%=baseUrl%>/user/selfstudyclassroom/slefstudyclassroom.jsp">自习教室查询</a></li>
      <li class="has-sub"><a href="javascript:void(0);">报修信息</a>
        <ul class="sub-menu">
          <li>
            <a href="<%=baseUrl%>/user/applymaintain/applymaintain.jsp">报修申请</a>
          </li>
          <li>
            <a href="<%=baseUrl%>/user/applymainList/applymianList.jsp">报修列表</a>
          </li>
        </ul>
      </li>
      <li class="has-sub"><a href="javascript:void(0);">借用教室</a>
        <ul class="sub-menu">
          <li>
            <a href="<%=baseUrl%>/user/applyclassroom/applyclassroom.jsp">教室申请</a>
          </li>
          <li>
            <a href="<%=baseUrl%>/user/myapplyclassroomtable/myapplyclassroomtable.jsp">我的申请</a>
          </li>
        </ul>
      </li>
    </ul>
  </div>

<%--</div> 少了一个div--%>

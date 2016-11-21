<%--
  User: JetWang
  Date: 2016/10/27
  Time: 18:04
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<div class="container-fluid all">
  <div class="sidebar">

    <ul class="nav">
      <li><a href="#1">首页</a></li>
      <li><a href="<%=baseUrl%>/views/applymaintainmange/applymaintainmanage.jsp">报修管理</a></li>
      <li class="has-sub"><a href="javascript:void(0);">借用教室管理</a>
        <ul class="sub-menu">
          <li>
            <a href="<%=baseUrl%>/views/applyclassroommanage/applyclassroommanage.jsp">借用申请管理</a>
          </li>
          <li>
            <a href="<%=baseUrl%>/views/timetablemanage/timetablestatemanage.jsp">教室状态管理</a>
          </li>
        </ul>
      </li>
      <li class="has-sub"><a href="javascript:void(0);">楼宇管理</a>
       <ul class="sub-menu">
         <li>
           <a href="<%=baseUrl%>/views/buildingmanage/all/buildingList/buildingList.jsp">查看</a>
         </li>
         <li>
           <a href="<%=baseUrl%>/views/buildingmanage/addBuilding/addbuilding.jsp">添加</a>
         </li>
       </ul>
      </li>
      <li><a href="<%=baseUrl%>/views/classroommanage/classroommanage.jsp">教室信息管理</a></li>
      <li><a href="<%=baseUrl%>/views/usermanage/usermanage.jsp">用户管理</a></li>
      <li><a href="<%=baseUrl%>/views/equipmentmanage/equipmentmanage.jsp">设备信息管理</a></li>
      <li><a href="<%=baseUrl%>/views/dictionary/dictionary.jsp">数据字典管理</a></li>
    </ul>
  </div>

<%--</div> 少了一个div--%>

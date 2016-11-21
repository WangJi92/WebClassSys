<%@ page import="com.hdu.cms.modules.UserInfo.entity.UserInfo" %>
<%@ page import="com.hdu.cms.common.ConstantParam.ConstantParam" %>
<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/21
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../../../../common/common.jsp" %>
<%
  UserInfo userInfo = (UserInfo) request.getSession().getAttribute(ConstantParam.SESSION_USERNAME);
%>
<div class="col-sm-12 col-xs-12">
  <form id="dialog_form" class="form-horizontal" role="form">
    <div class="form-body">
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>姓名
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="applicant" class="form-control" readonly  type="text" placeholder="姓名" value="<%=userInfo.getUserName()%>">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>电话
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="phone" class="form-control " readonly type="text" placeholder="电话" value="<%=userInfo.getPhone()%>">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>登录名
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="whichLesson" class="form-control " readonly type="text" placeholder="登录名" value="<%=userInfo.getLoginAccount()%>">
        </div>
      </div>
    </div>
  </form>
</div>


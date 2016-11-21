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
         姓名
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="UserName" class="form-control" readonly type="text" placeholder="姓名" value="<%=userInfo.getUserName()%>">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          登录名
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="loginAccount" class="form-control "  readonly type="text" placeholder="登录名" value="<%=userInfo.getLoginAccount()%>">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>电话
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="phone" class="form-control "   type="text" placeholder="电话" value="<%=userInfo.getPhone()%>">
        </div>
      </div>

      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>修改密码
        </div>
        <div class="col-md-8 col-xs-9 edit_password_radio">
          <label class="checkbox-inline">
            <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="1" checked>是
          </label>
          <label class="checkbox-inline">
            <input type="radio" name="optionsRadiosinline" id="optionsRadios2" value="2">否
          </label>
        </div>
      </div>
      <div class="edit_password_content">
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>原密码
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="password" class="form-control "   type="password" placeholder="原密码" >
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>现密码
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="newpassword" class="form-control "   type="password" placeholder="现密码">
          </div>
        </div>
      </div>
    </div>
  </form>
</div>
<script>
  seajs.use(basePath + "/common/userinfo/dialog/userInfoPasswordEdit/js/userInfoPasswordEdit",function(data){
    data.initEvent();
  });
</script>
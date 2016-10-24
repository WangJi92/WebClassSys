
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<form id="dialog_form" class="form-horizontal" role="form">
  <div class="form-body">
    <div class="form-group">
      <input name="id" hidden>
      <div class="control-label col-md-3">
        <font color="red">*</font>
         登陆名
      </div>
      <div class="col-md-8">
        <input name="userName" class="form-control" type="text" placeholder="登录名.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>密码
      </div>
      <div class="col-md-8">
        <input name="password"  class="form-control" type="text" placeholder="密码.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>手机号码
      </div>
      <div class="col-md-8">
        <input name="phone"  class="form-control" type="text" placeholder="手机号码.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>
        用户类型</div>
      <div class="col-md-8">
        <select  name="userType" class="form-control"></select>
      </div>
    </div>
  </div>
</form>
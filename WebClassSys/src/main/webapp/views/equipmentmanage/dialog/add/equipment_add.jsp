<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/24
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<form id="dialog_form_eq" class="form-horizontal" role="form">
  <div class="form-body">
    <div class="form-group">
      <input name="id" hidden>
      <div class="control-label col-md-3">
        <font color="red">*</font>
       设备名称
      </div>
      <div class="col-md-8">
        <input name="name" class="form-control" type="text" placeholder="设备名称.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>品牌名称
      </div>
      <div class="col-md-8">
        <input name="brandName"  class="form-control" type="text" placeholder="品牌名称.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>设备简介
      </div>
      <div class="col-md-8">
        <textarea rows="4" style="resize: none"  maxlength="300" name="introduce"  class="form-control" type="text" placeholder="设备简介."></textarea>
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>
        用户类型</div>
      <div class="col-md-8">
        <select  name="type" class="form-control"></select>
      </div>
    </div>
  </div>
</form>

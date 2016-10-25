
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<form id="dicDialog" class="form-horizontal" role="form">
  <div class="form-body">
    <div class="form-group">

      <div class="control-label col-md-3">
        <font color="red">*</font>
        字典名称
      </div>
      <div class="col-md-8">
        <input name="name" class="form-control" type="text" placeholder="字典名称.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>字典值
      </div>
      <div class="col-md-8">
        <input name="value" data-validation-error-msg="请输入具体的数字" data-validation="number" class="form-control" type="text" placeholder="字典值.">
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>
        字典类型</div>
      <div class="col-md-8">
        <select  name="classfiyType" class="form-control"></select>
      </div>
    </div>
  </div>
</form>
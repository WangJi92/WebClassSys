
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<style>
  @media screen and (max-width: 768px) {
    .edit-dialog .modal-dialog {
      width: 80%;
    }
  }
  @media screen and (min-width:992px){
    .edit-dialog .modal-dialog {
      width: 400px;
    }
  }

</style>
<form  id="dicDialog" class="form-horizontal" role="form">
  <div class="form-body">
    <div class="form-group">
      <input  name="id" type="hidden">
      <div class="control-label col-md-3">
        <font color="red">*</font>
        字典名称
      </div>
      <div class="col-md-8">
        <input name="name" class="form-control" type="text" >
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>字典值
      </div>
      <div class="col-md-8">
        <input name="value" class="form-control" type="text"  >
      </div>
    </div>
    <div class="form-group">
      <div class="control-label col-md-3">
        <font color="red">*</font>
        字典类型</div>
      <div class="col-md-8">
        <select  name="classfiyType" class="form-control">
        </select>
      </div>
    </div>
  </div>
</form>
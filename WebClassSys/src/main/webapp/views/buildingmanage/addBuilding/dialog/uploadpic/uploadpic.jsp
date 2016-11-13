<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/1
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../../../../common/common.jsp" %>
<script src="<%=baseUrl%>/libs/ajaxfileupload.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<script src="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.css" type="text/css">
<form id="dialog_form" class="form-horizontal" role="form">
  <div class="form-body">
    <div class="form-group">
      <div class="col-md-8">
        <input name="file" id="upload_file" class="form-control upload_file" type="file" accept="image/png,img/jpg">
      </div>
      <div class="col-md-3">
        <button class="btn btn-primary save_pic" type="button">上传图片</button>
      </div>
      <div class="col-md-4 look_pic" style="margin-left: 10px;margin-top: 10px; display: none">
       <img class="fancybox upload_pic" style="width: 300px"></img>
      </div>
    </div>
  </div>
</form>

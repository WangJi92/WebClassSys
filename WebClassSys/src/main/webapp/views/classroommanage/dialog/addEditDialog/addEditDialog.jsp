<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/7
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="./../../../../common/common.jsp" %>
<script src="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.css" type="text/css">
<script src="<%=baseUrl%>/libs/ajaxfileupload.js" type="text/javascript"></script>
<script id="item_picture" type="text/xml">
  <li class="list-group-item ">
          <a class="fancybox classroomfancybox" href="{{path}}">查看图片</a>
          <button type="button" class="btn btn-default btn_picture_delete"
           style="position: absolute;right: 10px;top:2px;" title="删除">
          <i class="glyphicon glyphicon-trash"></i></button>
  </li>
</script>
<div>
  <div class="row clearfix">
    <div class="col-md-6 column">
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <label for="name" class="col-sm-4 control-label"><font color="red">*</font>教室名称</label>

          <div class="col-sm-8">
            <input type="text" name="name" class="form-control" id="name" placeholder="请输入教室名称">
          </div>
        </div>
        <div class="form-group">
          <label for="seatNo" class="col-sm-4 control-label"><font color="red">*</font>座位数</label>

          <div class="col-sm-8">
            <input type="text" name="seatNo" class="form-control" id="seatNo" placeholder="座位数">
          </div>
        </div>
        <div class="form-group">
          <label for="floorNo" class="col-sm-4 control-label"><font color="red">*</font>所在楼层</label>

          <div class="col-sm-8">
            <input type="text" name="floorNo" class="form-control" id="floorNo" placeholder="输入所在楼层">
          </div>
        </div>
        <div class="form-group">
          <label  class="col-sm-4 control-label"><font color="red">*</font>教室类型</label>
          <div class="col-sm-8">
            <select name="type" class="form-control"></select>
          </div>
        </div>
        <div class="form-group">
          <label for="introduction" class="col-sm-4 control-label"><font color="red">*</font>教室简介</label>

          <div class="col-sm-8">
           <textarea rows="4" id="introduction" style="resize: none" maxlength="300" name="introduction"
                     class="form-control" type="text" placeholder="教室简介.."></textarea>
          </div>
        </div>
      </form>
    </div>
    <div class="col-md-6 column">
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <div class="col-md-8">
            <input name="file" id="upload_file" class="form-control upload_file" type="file"
                   accept="image/png,img/jpg">
          </div>
          <div class="col-md-4">
            <button class="btn btn-primary save_pic" type="button">上传图片</button>
          </div>
        </div>
      </form>
      <ul class="list-group  list_picture_group_dialog">
        <li class="list-group-item active ">
          图片列表
        </li>
      </ul>
    </div>
  </div>
</div>
<script>
  seajs.use(basePath + "/views/classroommanage/dialog/addEditDialog/js/addEditDialog",function(data){
    data.initEvent();
  })
</script>


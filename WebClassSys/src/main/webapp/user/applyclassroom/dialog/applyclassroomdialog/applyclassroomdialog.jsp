<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/17
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../../../../common/common.jsp" %>
  <div class="col-sm-12 col-xs-12">
    <form id="dialog_form" class="form-horizontal" role="form">
      <div class="form-body">
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>姓名
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="applicant" class="form-control " type="text" placeholder="姓名">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>电话
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="phone" class="form-control " type="text" placeholder="电话">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>占用时间
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="whichLesson" class="form-control " type="text" placeholder="占用时间">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>用途
          </div>
          <div class="col-md-8  col-xs-9">
            <select name="purpose" class="form-control">
            </select>
          </div>
        </div>
        <div class="form-group">
          <div class="control-label col-md-3 col-xs-3">
            <font color="red">*</font>申请原因及备注信息
          </div>
          <div class="col-md-8 col-xs-9">
        <textarea rows="5" style="resize: none" maxlength="300" name="applyReason"
                  class="form-control" type="text" placeholder="申请原因.."></textarea>
          </div>
        </div>
      </div>
    </form>
  </div>
<script>
  seajs.use(basePath + "/user/applyclassroom/dialog/applyclassroomdialog/js/applyclassroomdialog",function(data){
    data.initEvent();
  });
</script>

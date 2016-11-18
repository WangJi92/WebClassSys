<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/18
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="../../../../common/common.jsp" %>
<div class="col-sm-12 col-xs-12">
  <form id="dialog_form" class="form-horizontal" role="form">
    <div class="form-body">
      <div class="form-group">
      <div class="col-md-3 col-xs-3 control-label">
        <font color="red">*</font>申请时间
      </div>
      <div class="col-md-8 col-xs-9">
        <input name="whichLesson"  class="form-control" type="text" placeholder="申请时间.">
      </div>
    </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>实际时间
        </div>
        <div class="col-md-8 col-xs-9">
          <input name="realLessonTime"  class="form-control" type="text" placeholder="实际时间.">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>申请状态
        </div>
        <div class="col-md-8  col-xs-9">
          <select name="applystate" class="form-control">
            <option value="0">申请中</option>
            <option value="1" selected>申请成功</option>
            <option value="2">申请失败</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="control-label col-md-3 col-xs-3">
          <font color="red">*</font>备注信息
        </div>
        <div class="col-md-8 col-xs-9">
        <textarea rows="5" style="resize: none" maxlength="300" name="handleAddvice"
                  class="form-control" type="text" placeholder="同意申请.."></textarea>
        </div>
      </div>
    </div>
  </form>
</div>
<script>
  seajs.use(basePath + "/views/applyclassroommanage/dialog/editApplyInfo/js/editApplyDialog",function(data){
    data.initEvent();
  });
</script>

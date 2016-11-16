<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/16
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<%--模板的头--%>
<%@ include file="./../common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../common/left.jsp" %>
<div class="maincontent row">
  <ul class="breadcrumb">
    <li class="active">报修申请</li>
  </ul>
  <div class="col-sm-12">
    <form id="dialog_form" class="form-horizontal" role="form">
      <div class="form-body">
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>姓名
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="applyName" class="form-control " type="text" placeholder="姓名">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>电话
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="applyPhone" class="form-control " type="text" placeholder="电话">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>报修教室
          </div>
          <div class="col-md-8  col-xs-9">
            <input name="classRoomName" class="form-control " type="text" placeholder="报修教室">
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>报修类型
          </div>
          <div class="col-md-8  col-xs-9">
            <select name="type" class="form-control">
            </select>
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>紧急程度
          </div>
          <div class="col-md-8  col-xs-9">
            <select name="uergencyState" class="form-control">
            </select>
          </div>
        </div>
        <div class="form-group">
          <div class="control-label col-md-3 col-xs-3">
            <font color="red">*</font>详细描述
          </div>
          <div class="col-md-8 col-xs-9">
        <textarea rows="5" style="resize: none" maxlength="300" name="applyDetail"
                  class="form-control" type="text" placeholder="详细描述."></textarea>
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
            <button type="button" class="btn btn-primary btn-block hidden-lg btn_summit">提交</button>
            <button type="button" class="center-block btn btn-primary  hidden-xs hidden-sm hidden-md btn_summit">提交</button>
            </div>
        </div>
      </div>
    </form>
  </div>
</div>
<%@ include file="./../common/footter.jsp" %>
<script type="text/javascript">
seajs.use(basePath + "/user/applymaintain/js/applymaintain",function(data){
  data.initEvent();
})
</script>

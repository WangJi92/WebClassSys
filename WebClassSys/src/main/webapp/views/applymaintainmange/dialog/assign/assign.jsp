<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/16
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
  <form id="dialog_form" class="form-horizontal" role="form">
    <div class="form-body">
      <div class="form-group">
        <div class="col-md-3  col-xs-3">
          <button class="btn btn-primary select_maintain_people" type="button">选择人员</button>
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="maintainPeople" class="form-control " type="text" placeholder="人员姓名">
          <input name="maintainIndexCode" class="form-control " type="hidden">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>维修电话
        </div>
        <div class="col-md-8 col-xs-9">
          <input name="maintainPeoplePhone"  class="form-control" type="text" placeholder="维修电话.">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>发送短信
        </div>
        <div class="col-md-8 col-xs-9 send_message_radio">
          <label class="checkbox-inline">
            <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="1" checked>是
          </label>
          <label class="checkbox-inline">
            <input type="radio" name="optionsRadiosinline" id="optionsRadios2" value="2">否
          </label>
        </div>
      </div>
      <div class="form-group send_message_text">
        <div class="control-label col-md-3 col-xs-3">
          <font color="red">*</font>短信内容
        </div>
        <div class="col-md-8 col-xs-9">
        <textarea rows="5" style="resize: none" maxlength="300" name="message"
                  class="form-control" type="text" placeholder="短信内容."></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="control-label col-md-3 col-xs-3">
          <font color="red">*</font>备注信息
        </div>
        <div class="col-md-8 col-xs-9">
        <textarea rows="5" style="resize: none" maxlength="300" name="adminAddrice"
                  class="form-control" type="text" placeholder="备注信息."></textarea>
        </div>
      </div>
    </div>
  </form>
<script>
  seajs.use(basePath + "/views/applymaintainmange/dialog/assign/js/assign",function(dialogassign){
    dialogassign.initEvent();
  })
</script>

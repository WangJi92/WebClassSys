<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/16
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<form id="dialog_form" class="form-horizontal" role="form">
  <div class="form-body">
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
  seajs.use(basePath + "/views/applymaintainmange/dialog/dealsuccess/js/dealsuccess",function(data){
    data.initData();
  });
</script>
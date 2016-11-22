<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/18
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<style>
  .spinner {
    width: 100%;
  }
  .spinner input {
    text-align: left;
  }
  .input-group-btn-vertical {
    position: relative;
    white-space: nowrap;
    width: 2%;
    vertical-align: middle;
    display: table-cell;
  }
  .input-group-btn-vertical > .btn {
    display: block;
    float: none;
    width: 100%;
    max-width: 100%;
    padding: 8px;
    margin-left: -1px;
    position: relative;
    border-radius: 0;
  }
  .input-group-btn-vertical > .btn:first-child {
    border-top-right-radius: 4px;
  }
  .input-group-btn-vertical > .btn:last-child {
    margin-top: -2px;
    border-bottom-right-radius: 4px;
  }
  .input-group-btn-vertical i{
    position: absolute;
    top: 0;
    left: 4px;
  }
</style>
<%@ include file="../../../../common/common.jsp" %>
<div class="col-sm-12 col-xs-12">
  <form id="dialog_form" class="form-horizontal" role="form">
    <div class="form-body">
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>教室名称
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="classRoomNameEdit" class="form-control " type="text" placeholder="教室名称">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>课程时间
        </div>
        <div class="col-md-8  col-xs-9">
          <input name="whichLesson" class="form-control " type="text" placeholder="课程时间">
        </div>
      </div>
      <div class="form-group">
        <div class="col-md-3 col-xs-3 control-label">
          <font color="red">*</font>使用类型
        </div>
        <div class="col-md-8  col-xs-9">
          <select name="useType" class="form-control">
          </select>
        </div>
        <div class="form-group">
          <div class="col-md-3 col-xs-3 control-label">
            <font color="red">*</font>批处理
          </div>
          <div class="col-md-8 col-xs-9 bath-process_radio">
            <label class="checkbox-inline">
              <input type="radio" name="bath_process" id="optionsRadios1" value="2" checked>否
            </label>
            <label class="checkbox-inline">
            <input type="radio" name="bath_process" id="optionsRadios2" value="1">当前时间
          </label>
            <label class="checkbox-inline">
              <input type="radio" name="bath_process" id="optionsRadios3" value="4">教室所有时间
            </label>
          </div>
        </div>
        <div class="bath-process" style="display: none">
          <div class="form-group">
            <div class="col-md-3 col-xs-3 control-label">
              <font color="red">*</font>开始结束周
            </div>
            <div class="col-md-8  col-xs-9">
              <div class="row">
                <div class="col-md-5 col-xs-5 ">
                  <div class="input-group spinner beginWeek">
                    <input type="text" class="form-control beginWeek_input" value="1">
                    <div class="input-group-btn-vertical">
                      <button class="btn btn-default" type="button"><i class="fa fa-caret-up"></i></button>
                      <button class="btn btn-default" type="button"><i class="fa fa-caret-down"></i></button>
                    </div>
                  </div>
                </div>
                <div class="col-md-1 col-xs-1 control-label">
                  到
                </div>
                <div class="col-md-5 col-xs-5">
                  <div class="input-group spinner endWeek">
                    <input type="text" class="form-control endWeek_input"  value="20">
                    <div class="input-group-btn-vertical">
                      <button class="btn btn-default" type="button"><i class="fa fa-caret-up"></i></button>
                      <button class="btn btn-default" type="button"><i class="fa fa-caret-down"></i></button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-3 col-xs-3 control-label">
              <font color="red">*</font>选项
            </div>
            <div class="col-md-8 col-xs-9 ">
              <label class="checkbox-inline">
                <input type="radio" name="handlesetting" id="all" value="4" checked>全部
              </label>
              <label class="checkbox-inline">
                <input type="radio" name="handlesetting" id="one" value="1">单周
              </label>
              <label class="checkbox-inline">
                <input type="radio" name="handlesetting"  id="two" value="2">双周
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </form>
</div>
<script>
  seajs.use(basePath + "/views/timetablemanage/dialog/editTimeTableState/js/editTimeTableDialog",function(data){
    data.initEvent();
  });
</script>
<script>
  jQuery(document).ready(function() {

    $(".bath-process_radio").on("click",":radio",function(){
      if($(this).val()==1 ){
        $(".bath-process").css('display','block');
      }else{
        $(".bath-process").css('display','none');
      }
    });
    $('.endWeek .btn:first-of-type').on('click', function() {
      var re = /^[0-9]+.?[0-9]*$/;
      if(!re.test($('.endWeek_input').val())){
        $('.endWeek_input').val(1)
      }else{
        $('.endWeek_input').val( parseInt($('.endWeek_input').val(), 10) + 1);
      }
    });
    $('.endWeek .btn:last-of-type').on('click', function() {
      var re = /^[0-9]+.?[0-9]*$/;
      if(!re.test($('.endWeek_input').val())){
        $('.endWeek_input').val(1)
      }else{
        $('.endWeek_input').val( parseInt($('.endWeek_input').val(), 10) + 1);
      }
    });
    $('.beginWeek .btn:first-of-type').on('click', function() {
      var re = /^[0-9]+.?[0-9]*$/;
      if(!re.test($('.beginWeek_input').val())){
        $('.beginWeek_input').val(1)
      }else{
        $('.beginWeek_input').val( parseInt($('.beginWeek_input').val(), 10) + 1);
      }
    });
    $('.beginWeek .btn:last-of-type').on('click', function() {
      var re = /^[0-9]+.?[0-9]*$/;
      if(!re.test($('.beginWeek_input').val())){
        $('.beginWeek_input').val(1)
      }else{
        $('.beginWeek_input').val( parseInt($('.beginWeek_input').val(), 10) + 1);
      }
    });
  });
</script>


<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/16
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<%--模板的头--%>
<%@ include file="./../common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../common/left.jsp" %>
<div class="maincontent row">
    <ul class="breadcrumb">
        <li class="active">借用教室申请</li>
    </ul>
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-12">
                <form class="form-inline" style="margin-bottom: 10px" role="form">
                    <div class="form-group">
                        <label class="control-label">周次:</label>
                        <select name="whichWeek" class="form-control">
                            <option value="-1">全部</option>
                            <option value="1">第1周</option>
                            <option value="2">第2周</option>
                            <option value="3">第3周</option>
                            <option value="4">第4周</option>
                            <option value="5">第5周</option>
                            <option value="6">第6周</option>
                            <option value="7">第7周</option>
                            <option value="8">第8周</option>
                            <option value="9">第9周</option>
                            <option value="10">第10周</option>
                            <option value="11">第11周</option>
                            <option value="12">第12周</option>
                            <option value="13">第13周</option>
                            <option value="14">第14周</option>
                            <option value="15">第15周</option>
                            <option value="16">第16周</option>
                            <option value="17">第17周</option>
                            <option value="18">第18周</option>
                            <option value="19">第19周</option>
                            <option value="20">第20周</option>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <label class="control-label">星期:</label>
                        <select name="whichDay" class="form-control">
                            <option value="-1">全部</option>
                            <option value="1">星期一</option>
                            <option value="2">星期二</option>
                            <option value="3">星期三</option>
                            <option value="4">星期四</option>
                            <option value="5">星期五</option>
                            <option value="6">星期六</option>
                            <option value="7">星期天</option>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <label class="control-label">课程时间:</label>
                        <select name="whichLesson" class="form-control">
                            <option value="-1">全部</option>
                            <option value="1">第一二节</option>
                            <option value="2">第三四五节</option>
                            <option value="4">第六七节</option>
                            <option value="8">第八九节</option>
                            <option value="16">第十到十二节</option>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <label class="control-label">状态:</label>
                        <select name="timetableType" class="form-control">
                            <option value="-1">全部</option>
                        </select>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <form class="form-inline" style="margin-bottom: 10px" role="form">
                    <div class="form-group">
                        <label class="control-label">教室类型:</label>
                        <select name="classType" class="form-control">
                            <option value="-1">全部</option>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <label class="control-label">座位数大于:</label>
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="seatNo" title="座位数大于..." placeholder="座位数大于..如35" >
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        <input class="form-control" name="classRoomName" title="教室名称..." placeholder="教室名称" >
                    </div>
                    &nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default btn-primary timetable_btn_search">搜索</button>
                </form>
            </div>
        </div>

        <table id="timetable">
        </table>
    </div>
</div>
<%@ include file="./../common/footter.jsp" %>
<script>
    seajs.use(basePath + "/user/applyclassroom/applyclassroomindex/applyclassroom", function (data) {
        data.initEvent();
    });
</script>

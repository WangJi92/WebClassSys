<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/7
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="./../../common/common.jsp" %>
<%--模板的头--%>
<%@ include file="./../../admin/common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../../admin/common/left.jsp" %>

<script src="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.css" type="text/css">
<script src="<%=baseUrl%>/libs/bootstrap-paginator/bootstrap-paginator.js"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>


<script id="template" type="text/xml">
    <li class="list-group-item">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <div class="btn-group btn-group-md">
                            <button class="btn btn-default classroom_edit" type="button">
                                <i class="fa fa-pencil fa-fw"></i>编辑
                            </button>
                            <button class="btn btn-default classroom_delete" type="button">
                                <i class="fa fa-trash-o fa-fw"></i>删除
                            </button>
                        </div>
                        <h3>
                            教室名称:{{name}}
                        </h3>
                        <input type="hidden" name="id" value="{{id}}"/>
                        <input type="hidden" name="indexCode" value="{{indexCode}}"/>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-6 column">
                        <ul class="list-group  list_picture_group">
                            <li class="list-group-item ">
                                教室类型:{{typeName}}
                            </li>
                            <li class="list-group-item ">
                                座位数：{{seatNo}}座位
                            </li>
                            <li class="list-group-item ">
                                所在楼层:{{floorNo}}楼
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-6 column">

                        <ul class="list-group  list_picture_group">
                            {{#pictrues}}
                            <li class="list-group-item ">
                                <a class="fancybox" href="{{path}}">查看图片</a>
                            </li>
                            {{/pictrues}}
                        </ul>

                    </div>
                </div>
                <div class="row clearfix" style="margin-bottom: 30px;">
                    <div class="col-md-12 column">
                        <h4>简介</h4>
                        <p>
                            {{introduction}}
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row clearfix table_classroom" style="margin-bottom: 30px">
            <div class="col-md-12 column">
                <h4><b>教室课表管理</b></h4>
            </div>
            <div class="col-sm-12">
                <div id="toolbar_timetable" style="margin-bottom: 10px" class="btn-group pull-right">
                    <!--<button type="button" class="btn btn-default timetable_add" title="添加">
                        <i class="glyphicon glyphicon-plus"></i>
                    </button>-->
                   <!-- <button type="button" class="btn btn-default timetable_delete" title="删除所选数据">
                        <i class="glyphicon glyphicon-trash"></i>
                    </button>
                    <button type="button" class="btn btn-default timetable_export" title="导出数据信息">
                        <i class="glyphicon glyphicon-export"></i>-->
                    </button>
                </div>
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
                    <button type="button" class="btn btn-default btn-primary timetable_btn_search">搜索</button>
                </form>
                <table id="timetable">
                </table>
            </div>
        </div>
        <div class="row clearfix table_classroom">
            <div class="col-md-12 column">
                <h4>设备管理</h4>
            </div>
            <div class="col-sm-12">
                <ul class="list-group equipmentList">
                    <li class="list-group-item">教室设备信息列表：
                        <button type="button" class="btn btn-default equipment_add" title="添加">
                            <i class="glyphicon glyphicon-plus"></i>
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    </li>
</script>
<script id="equipment_list_template" type="text/html">
    <li class="list-group-item equipmentItem">设备名称:{{name}} &nbsp;&nbsp;&nbsp;设备类型:{{typeStr}}&nbsp;&nbsp;&nbsp;品牌名称:{{brandName}}
        <input type="hidden" name="id" value="{{id}}"/>
        <input type="hidden" name="classRoomIndexCode" value="{{classRoomIndexCode}}"/>
        <input type="hidden" name="equipmentIndexCode" value="{{equipmentIndexCode}}"/>
        <button type="button" class="btn btn-default btn_equipment_delete" style="position: absolute;right: 10px;top:2px;" title="删除">
            <i class="glyphicon glyphicon-trash"></i>
        </button>
    </li>
</script>
<div class="maincontent row">
    <ul class="breadcrumb">
        <li class="active">教室信息列表
        </li>
    </ul>
      <input type="hidden" class="buildingIndexcode" value="<%=request.getParameter("buildingIndexcode")%>">
   <%-- <input type="hidden" name="buildingIndexcode" class="buildingIndexcode" value="nhocnlprjrtfozsztsfkillkcwydvhgx">--%>

    <div class="col-sm-12">
        <form class="form-inline" role="form">
            <button type="button" style="margin-bottom: 20px" class="btn btn-default btn_add_calssroom"><i
                    class="glyphicon glyphicon-plus"></i>添加教室
            </button>
            <div class="pull-right" style="margin-bottom: 20px">
                <div class="form-group">
                    <input type="text" class="form-control" id="classRoom_search_text" placeholder="教室名称...">
                </div>
                <button type="button" class="btn btn-default btn-primary btn_search">搜索</button>
            </div>
        </form>
    </div>
    <div class="col-sm-12" >
        <ul  class="list-group classroomList">

        </ul>
        <ul id="pagination" class="pagination"></ul>
    </div>
</div>
<%@ include file="./../../admin/common/footter.jsp" %>
<script type="text/javascript">
    /* seajs.use(basePath + "/views/buildingmanage/all/buildingList/js/buildingList");*/
    seajs.use(basePath + "/views/classroommanage/classroomindex/classroomindex");
</script>


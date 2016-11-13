<%--
  User: JetWang
  Date: 2016/10/30
  Time: 17:54
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="./../../../common/common.jsp" %>
<%--模板的头--%>
<%@ include file="./../../../admin/common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../../../admin/common/left.jsp" %>
<script src="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/fancybox/source/jquery.fancybox.css" type="text/css">
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<style>
    .modal-body{
        padding: 10px;
    }
    .modal-footer{
        margin-top: 0px;
        padding: 10px;
    }
    .modal-header{
        padding: 10px;
    }
</style>
<input type="hidden" class="building_indexcode_edit" value="<%=request.getParameter("indexcode")%>">
<div class="maincontent row">
    <ul class="breadcrumb">
        <li class="active">添加楼宇信息</li>
    </ul>
    <div class="col-sm-12">
        <div class="row">
            <form id="building_form" class="form-horizontal" role="form">
                <div class="col-sm-6">
                    <div class="form-body">
                        <div class="form-group">
                            <input name="id" type="hidden" value="">
                            <div class="control-label col-sm-3">
                                <font color="red">*</font>
                                楼宇名称
                            </div>
                            <div class="col-sm-8">
                                <input name="name" class="form-control" type="text" placeholder="楼宇名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label col-md-3">
                                <font color="red">*</font>楼层数
                            </div>
                            <div class="col-md-8">
                                <input name="floorTotal" class="form-control" type="text" placeholder="楼层数">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label col-md-3">
                                <font color="red">*</font>楼宇简介
                            </div>
                            <div class="col-md-8">
                                <textarea rows="4" style="resize: none" maxlength="300" name="introduceInfo"
                                          class="form-control" type="text" placeholder="楼宇简介."></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label col-md-3">
                                <font color="red">*</font>
                                值班人员
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <input type="input" class="form-control input_duty_name" readonly>
                                        <input name="dutyRoomPeopleIndexcode" type="hidden" class="input_duty_indexcode">
                                        <input type="hidden" class="input_duty_phone">
                                    </div>
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-default btn_add_duty " title="添加值班人员">
                                            <i class="glyphicon glyphicon-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label col-md-3">
                                <font color="red">*</font>
                                维修人员
                            </div>
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <input type="input" class="form-control input_maintain_name" readonly>
                                        <input name="maintancePeopleIndexcode" type="hidden" class="input_maintain_indexcode">
                                        <input type="hidden" class="input_maintain_phone">

                                    </div>
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-default btn_add_maintain " title="添加维护人员">
                                            <i class="glyphicon glyphicon-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="control-label col-md-3">
                                <font color="red">*</font>
                                楼宇图片
                            </div>
                            <div class="col-md-8">
                                <button type="button" class="btn btn-default btn_add_picture" title="添加楼宇图片">
                                    <i class="glyphicon glyphicon-plus"></i>
                                </button>
                            </div>
                        </div>
                        <ul class="list-group  list_picture_group">
                            <li class="list-group-item active ">
                                图片列表
                            </li>
                        </ul>
                        <div style="margin-top:20px;margin-bottom: 30px;" class="pull-right">
                            <button type="button" class="btn btn-primary btn_building_sava">保存</button>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        seajs.use(basePath + "/views/buildingmanage/addBuilding/index/addbuilding_index");
    </script>

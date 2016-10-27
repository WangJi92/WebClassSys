<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<%--模板的头--%>
<%@ include file="./../../admin/common/header.jsp" %>
<%--模板的left--%>
<%@ include file="./../../admin/common/left.jsp" %>
<div class="maincontent row">
    <ul class="breadcrumb active">
        <li class="active">用户管理</li>
    </ul>
    <div class="col-sm-12">
        <div id="toolbar" class="btn-group">
            <button type="button" class="btn btn-default dic_add" title="添加">
                <i class="glyphicon glyphicon-plus"></i>
            </button>
            <button type="button" class="btn btn-default dic_delete" title="删除选中">
                <i class="glyphicon glyphicon-trash"></i>
            </button>
            <button type="button" class="btn btn-default dic_export" title="导出数据信息">
                <i class="glyphicon glyphicon-export"></i>
            </button>
        </div>
        <table id="table">
        </table>
    </div>
</div>
<script type="text/javascript">
  seajs.use(basePath + "/views/usermanage/index/index");
</script>
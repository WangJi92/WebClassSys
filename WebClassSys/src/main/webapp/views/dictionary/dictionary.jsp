<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/12
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/7
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
<script src="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript">
</script>
<link rel="stylesheet" href="<%=baseUrl%>/libs/bootstrap-table/dist/bootstrap-table.css" type="text/css">
<div class="container">
  <div id="toolbar" class="btn-group">
    <button type="button" class="btn btn-default dic_add" title="添加">
      <i class="glyphicon glyphicon-plus"></i>
    </button>
    <button type="button" class="btn btn-default dic_delete" title="删除所选数据">
      <i class="glyphicon glyphicon-trash"></i>
    </button>
    <button type="button" class="btn btn-default dic_export" title="导出数据信息">
      <i class="glyphicon glyphicon-export"></i>
    </button>
  </div>
  <table id="table"
         data-url="/cms/UserInfo/userInfoPage">
  </table>
</div>
<script type="text/javascript">
  seajs.use(basePath+"/views/dictionary/index/dictionary_index");
</script>







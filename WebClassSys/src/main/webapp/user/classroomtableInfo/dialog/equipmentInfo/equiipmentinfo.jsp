<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/17
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<script id="equipment_list_template" type="text/html">
  <li class="list-group-item equipmentItem">设备名称:{{name}} &nbsp;&nbsp;&nbsp;设备类型:{{typeStr}}&nbsp;&nbsp;&nbsp;品牌名称:{{brandName}}
  </li>
</script>

  <ul class="list-group equipmentList">
    <li class="list-group-item">教室设备信息列表：</li>
  </ul>

<script>
  seajs.use(basePath + "/user/classroomtableInfo/dialog/equipmentInfo/js/equipmentInfodialog",function(data){
    data.initEvent();
  });

</script>

<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/2
  Time: 14:46
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
  Integer port = request.getServerPort();
  String servesName = request.getServerName();
  String url = "http://"+servesName;
  String contextpath = request.getContextPath();
  if(port !=80){
    url=url+":"+port.toString()+contextpath;
  }else{
    url=url+contextpath;
  }
  final String baseUrl = url;
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%--  bootstrap不支持IE兼容模式，为了让IE浏览器运行最新的渲染模式，将添加以下标签在页面中--%>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta name="MobileOptimized" content="320">
    <link rel="stylesheet" type="text/css" href="<%=baseUrl%>/libs/font-awesome/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="<%=baseUrl%>/libs/bootstrap/css/bootstrap.css">
<%--  <link rel="stylesheet" type="text/css" href="<%=baseUrl%>/libs/formValidator/style/style.css">--%>
<%--  <link rel="stylesheet" type="text/css" href="<%=baseUrl%>/libs/artDialog/css/ui-dialog.css">--%>
<%--  <link href="<%=baseUrl%>/libs/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>--%>
  <link href="<%=baseUrl%>/libs/bootstrap3-dialog/dist/css/bootstrap-dialog.css" rel="stylesheet" type="text/css"/>

  <!--[if lte IE 7]><link href="<%=baseUrl%>/libs/font-awesome/css/font-awesome-ie7.css" type="text/css" rel="stylesheet" /><![endif]-->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js" type="text/javascript"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js" type="text/javascript"></script>
  <script src="../libs/excanvas/excanvas.min.js" type="text/javascript"></script>
  <![endif]-->
</head>
<script type="text/javascript" src="<%=baseUrl %>/libs/jquery/jquery-1.10.2.js"></script>
<script src="<%=baseUrl%>/libs/jquery/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<%--<script src="<%=baseUrl%>/libs/jquery-ui/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>--%>
<script type="text/javascript" src="<%=baseUrl%>/libs/bootstrap/js/bootstrap.js"></script>
<%-- 表单美化插件--%>
<%--<link href="<%=baseUrl%>/libs/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>--%>
<%--默认的下拉才当的操作是点击，这个悬浮就可以操作了--%>
<%--<script src="<%=baseUrl%>/libs/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.js" type="text/javascript"></script>--%>
<%--请求响应的时候锁定页面--%>
<%--<script type="text/javascript" src="<%=baseUrl%>/libs/jquery-blockui/jquery.blockui.js"></script>--%>
<%--滚动界面的时候使用这个插件--%>
<%--<script type="text/javascript" src="<%=baseUrl%>/libs/jquery-slimscroll/jquery.slimscroll.js"></script>--%>
<%--处理cookie--%>
<%--<script src="<%=baseUrl%>/libs/jquery-cookie/jquery.cookie.js" type="text/javascript"></script>--%>
<%--处理好看的表单出来--%>
<%--<script src="<%=baseUrl%>/libs/uniform/jquery.uniform.js" type="text/javascript"></script>--%>
<script src="<%=baseUrl%>/libs/bootstrap3-dialog/dist/js/bootstrap-dialog.js"></script>

<script type="text/javascript" src="<%=baseUrl%>/libs/seajs/sea.js"></script>
<script type="text/javascript">
  var basePath = "<%=baseUrl %>";
</script>
<%--这里是加载seajs文件的目录--%>
<script type="text/javascript" src="<%=baseUrl%>/common/load.js"></script>

<style>
  @media screen and (max-width: 768px) {
    .add-dialog .modal-dialog {
      width: 80%;
    }
  }
  @media screen and (min-width:992px){
    .width200-dialog .modal-dialog {
      width: 200px;
    }
    .width400-dialog .modal-dialog {
      width: 400px;
    }
    .width300-dialog .modal-dialog {
       width: 300px;
     }
    .width500-dialog .modal-dialog {
      width: 500px;
    }
    .width600-dialog .modal-dialog {
      width: 600px;
    }
    .width700-dialog .modal-dialog {
      width: 700px;
    }
  }
</style>

<link rel="shortcut icon" href="<%=baseUrl%>/image/building.icon"/>
<title>HDU 教学楼管理系统</title>



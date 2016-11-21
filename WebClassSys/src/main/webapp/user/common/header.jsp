<%@ page import="com.hdu.cms.common.ConstantParam.ConstantParam" %>
<%@ page import="com.hdu.cms.modules.UserInfo.entity.UserInfo" %>
<%--
  User: JetWang
  Date: 2016/10/27
  Time: 18:03
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%--框架使用需要的--%>
<link href="<%=baseUrl%>/css/default.css" rel="stylesheet" type="text/css"/>
<!--页面加载进度条-->
<link href="<%=baseUrl%>/libs/pace-1.1/dataurl.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=baseUrl%>/libs/pace-1.1/pace.js"></script>
<!--页面加载进度条-->
<%--让页面返回顶部时平滑滚动--%>
<script src="<%=baseUrl%>/libs/jquery.scrolltopcontrol/scrolltopcontrol.js" type="text/javascript"></script>
<%--让页面返回顶部时平滑滚动--%>
<script src="<%=baseUrl%>/js/default.js" type="text/javascript"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <%-- 控制开关 左侧--%>
      <button type="button" class="navbar-toggle show pull-left" data-target="sidebar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <%--控制开关 顶部--%>
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
              aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">CMS系统</a>
    </div>
    <%-- 顶部控制的信息接顶端--%>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="<%=baseUrl%>/user/applymaintain/applymaintain.jsp">报修申请</a></li>
        <li><a href="<%=baseUrl%>/user/applyclassroom/applyclassroom.jsp">借用教室</a></li>
        <li><a href="<%=baseUrl%>/user/selfstudyclassroom/slefstudyclassroom.jsp">自习教室查询</a></li>
        <%--<li>
          <a href="#4" target="_blank">
            <i class="fa fa-download fa-fw"></i>&nbsp;第四个哦
          </a>
        </li>--%>
      </ul>
      <ul class="nav navbar-nav navbar-right" style="margin-right: 10px">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle"
             data-toggle="dropdown"
             role="button"
             aria-expanded="false">
            </i>&nbsp;欢迎【<%=((UserInfo)request.getSession().getAttribute(ConstantParam.SESSION_USERNAME)).getUserName()
          %>】&nbsp;
            <span class="caret"></span>
          </a>
          <ul class="dropdown-menu"  role="menu">
            <li><a class="personInfo" href="javascript:void(0)"><i class="fa fa-info fa-fw"></i>&nbsp;个人信息</a></li>
            <li class="divider"></li>
            <li><a class="editPersonInfo" href="javascript:void(0)"><i class="fa fa-edit fa-fw"></i>&nbsp;修改密码</a></li>
            <li class="divider"></li>
            <li><a href="<%=baseUrl%>/views/login/logout.jsp">
              <i class="fa fa-sign-out fa-fw"></i>&nbsp;退出登录</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

<script>
  seajs.use(basePath + "/common/userinfo/userinfoIndex/userInfoIndex",function(data){
    data.initEvent();
  })
</script>


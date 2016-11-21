<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/4
  Time: 20:05
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<link href="<%=baseUrl%>/views/login/css/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=baseUrl%>/views/login/css/login.css" rel="stylesheet" type="text/css">
<body class="login test" style="background-color: #189DE1">
<div class="logo">
</div>
<div class="content">
    <form class="login-form"  method="post">
        <h3 class="form-title">登录</h3>
        <div class="form-group">
            <div class="input-icon ">
                <i class="fa fa-user"></i>
                <input class="form-control" type="text"  placeholder="用户名" name="name"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control " type="password"  placeholder="密码" name="password"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="save" /> 记住我</label>
            <button type="button" class="btn blue pull-right btn-primary btn_login ">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
</div>
<script type="text/javascript">
    seajs.use(basePath+"/views/login/index/login_index",function(data){
        data.init();
    });
</script>
</body>



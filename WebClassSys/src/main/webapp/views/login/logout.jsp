<%@ page import="com.hdu.cms.common.ConstantParam.ConstantParam" %>
<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/11/21
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
   request.getSession().setAttribute(ConstantParam.SESSION_USERNAME,null);
   response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
%>
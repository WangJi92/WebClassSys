<%--
  Created by IntelliJ IDEA.
  User: JetWang
  Date: 2016/10/2
  Time: 15:12
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<div id="template" style="display:none">
  <h1>{{title}}</h1>
  {{#content}}
  {{#first}}
  <li><font style="color:red">{{name}}</font></li>
  {{/first}}
  {{#second}}
  <li><a href="{{url}}">{{name}}</a></li>
  {{/second}}
  {{/content}}
  <li><img src="{{img}}" border="0" alt=""></li>
</div>
<div id="content"></div>
<script type="text/javascript">
  var html = document.getElementById("template").innerHTML;
  var json = {
    "title":"The title",
    "content": [
      {"name": "lily", "first": true},
      {"name": "Lucy", "second": true, "url": "http://www.haohaoup.com"},
      {"name": "Andy", "second": true, "url": "http://www.haohaoup.com"}
    ],
    "img":"http://avatar.csdn.net/4/5/F/1_yiyaqin1990.jpg"
  };
  var _html = Mustache.to_html(html,json);
  document.getElementById("content").innerHTML = _html;
</script>

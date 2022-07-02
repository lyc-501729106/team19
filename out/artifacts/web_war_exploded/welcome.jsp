<%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/25
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #df1{
            width: 80px;
            height: 130px;
            border: #aaaca5 solid 1px;
            float: left;
        }
        #df1 a{
            color: #5E6974;
            text-decoration: none;
        }
        #dr{
            width: 1300px;
            height: 800px;
            border: #9d9f9e solid 1px;
            float: right;
        }
    </style>
</head>
<body>
<h1 style="text-align: center">公交查询系统</h1>
<div id="df1">
    <a href="index.jsp">首页</a><br/>
    <a href="StationServlet?opt=add-stationBeforeGetAll" target="mainIframe">增加站点</a><br/>
    <a href="StationServlet?opt=getAll" target="mainIframe">所有站点</a><br/>
    <a href="LineServlet?opt=addLineBefore" target="mainIframe">增加线路</a><br/>
    <a href="LineServlet?opt=allLine" target="mainIframe">所有线路</a><br/>
</div>
<div id="dr">
    <iframe name="mainIframe" width="1300" height="800"></iframe>
</div>
</body>
</html>

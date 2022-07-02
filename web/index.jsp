<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        #selectLine {
            position: absolute;
            left: 500px;
            width: 400px;
            height: 60px;
        }

        #nullmassage {
            width: 232px;
            height: 25px;
            position: absolute;
            left: 635px;
            top: 270px;
            display: block;
            color: red;
        }
        #homepagetable{
            display: block;
            position: absolute;
            left: 450px;
            top: 300px;
        }
    </style>
    <script src="js/jquery-3.6.0.js"></script>
    <script>
        function selectLine() {
            let val = $('#selectLine').val();
            $.ajax(
                {
                    url: "LineServlet",
                    type: "get",
                    dataType: "json",
                    data: {'lineName': val, 'opt': 'selectOneLine'},
                    success: function (rs) {
                        if (rs != "0") {
                            document.getElementById('nullmassage').innerText = "";
                            // rs[0].lineId;
                            document.getElementById('lineName').innerHTML = rs[0].lineName;
                            document.getElementById('cost').innerHTML =  rs[0].cost;
                            document.getElementById('lineInfo').innerHTML = rs[0].lineInfo;
                            document.getElementById('departureTime').innerHTML = rs[0].departureTime;
                            document.getElementById('collectionTime').innerHTML = rs[0].collectionTime;
                            document.getElementById('departureInterval').innerHTML = rs[0].departureInterval;
                            var str = "";
                            for (var i = 1; i < rs.length; i++) {
                                str +=" "+ rs[i];
                            }
                            document.getElementById('stations').innerHTML =str;
                        } else {
                            document.getElementById('lineName').innerHTML = "";
                            document.getElementById('cost').innerHTML = "";
                            document.getElementById('lineInfo').innerHTML ="";
                            document.getElementById('departureTime').innerHTML = "";
                            document.getElementById('collectionTime').innerHTML = "";
                            document.getElementById('departureInterval').innerHTML = "";
                            document.getElementById('stations').innerHTML ="";
                            document.getElementById('nullmassage').innerText = "抱歉没有找到！";
                        }
                    }
                }
            )
        }
        function clearnullmassage() {
            document.getElementById('nullmassage').innerText = "";
        }
    </script>
</head>
<body>
<a href="admin-login.jsp" style="float: right">管理员登录</a>
<h1 style="text-align: center">公交查询系统</h1>
<table>
    <thead>
    <tr>
        <td>已有的线路</td>
    </tr>
    </thead>
    <c:forEach items="${lineinfos}" var="lineinfo">
        <tr>
            <td>${lineinfo.lineName}</td>
        </tr>
    </c:forEach>
</table>
<input type="text" id="selectLine" onblur="clearnullmassage()" onkeyup="selectLine()" placeholder="请输入路线名查询">
<span id="nullmassage"></span>
<table id="homepagetable">
    <thead>
    <tr>
        <th>线路名</th>
        <th>费用</th>
        <th>线路描述</th>
        <th>发车间隔</th>
        <th>发车时间</th>
        <th>收车时间</th>
        <th>所经过的站点</th>
    </tr>
    </thead>
    <tr>
            <td id="lineName"></td>
            <td id="cost"></td>
            <td id="lineInfo"></td>
            <td id="departureTime"></td>
            <td id="collectionTime"></td>
            <td id="departureInterval"></td>
        <%--                站点--%>
            <td id="stations"></td>
    </tr>
</table>
</body>
</html>
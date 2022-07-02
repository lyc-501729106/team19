<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/25
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.js"></script>
    <script>
        function tijiao(name, opt) {
            $.ajax(
                {
                    url: "StationServlet",
                    type: "get",
                    dataType: "json",
                    data: {'station-name': name, 'opt': opt},
                    success: function (result) {
                        if (result != '0') {
                            location.href = "StationServlet?opt=add-stationBeforeGetAll";
                            alert("添加成功");
                        } else {
                            alert("添加失败");
                        }
                    }
                }
            )
        }

        function repeatjudge() {
            let station_name = $('#station-name').val();
            if (station_name != "") {
                $.ajax(
                    {
                        url: "StationServlet",
                        type: "get",
                        dataType: "json",
                        data: {'stationName': station_name, 'opt': "repeatjudge"},
                        success: function (result) {
                            if (result == '0') {
                                $('#station-name').val('');
                                document.getElementById('myspan').innerText = "";
                                alert("站点重复，请重新输入！");
                            } else {
                                document.getElementById('myspan').innerText = "可以使用";
                            }
                        }
                    }
                )
            }else {
                alert("值不能为空！")
            }
        }
    </script>
    <style>
        #myspan{
            position: absolute;
            left: 350px;
            top: 15px;
        }
    </style>
</head>
<body>
<table border="2">
    <tr>
        <td>站点名称</td>
        <td><input type="text" id="station-name" name="station-name" placeholder="站点不能重复"></td>

        <td><input type="submit" value="提交" onmouseenter="repeatjudge()"
                   onclick="tijiao($('#station-name').val(),'insertStation')"></td>
    </tr>
</table>
<span id="myspan" style="color: green"></span>
<table>
    <thead>
    <tr>
        <th>已有的站点：</th>
    </tr>
    </thead>

    <c:forEach items="${list}" var="station">
        <tr>
            <td>${station.stationName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

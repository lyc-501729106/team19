<%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/26
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.js"></script>
    <script>
        function tijiao(id, name,opt) {
            // var name = $('#stationName').val();
            $.ajax(
                {
                    url: "StationServlet",
                    type: "get",
                    dataType: "json",
                    data: {'stationId': id, 'stationName': name, 'opt': opt},
                    success: function (result) {
                        if (result != '0') {
                            alert("修改成功")
                        } else {
                            alert("修改失败")
                        }
                    }
                }
            )
        }
        function repeatjudge() {
            let station_name = $('#stationName').val();
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
<%
    String stationId = request.getParameter("stationId");
    String stationName = request.getParameter("stationName");
%>
<table border="2">
    <input type="hidden" name="stationId" value="<%=stationId%>">
    <tr>
        <td>站点名字</td>
        <td><input type="text" id="stationName" name="stationName" value="<%=stationName%>"></td>
        <td><input type="submit" value="提交" onmouseenter="repeatjudge()" onclick="tijiao(<%=stationId%>,$('#stationName').val(),'update')"></td>
    </tr>
</table>
<span id="myspan" style="color: green"></span>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.js"></script>
    <script>
        //睡眠函数
        function sleep(numberMillis){
            var now = new Date();
            var exitTime = now.getTime() + numberMillis;
            while (true) {
                now = new Date();
                if (now.getTime() > exitTime){
                    return;
                }
            }
        }
        function tijiao(stationid,opt) {
            $.ajax(
                {
                    url: "StationServlet",
                    type: "get",
                    dataType: "json",
                    data: {'stationId': stationid,'opt': opt},
                    success: function (result) {
                        if (result != '0') {
                            $('#massage'+stationid).html("删除成功");
                            sleep(400);
                            $('#tr' + stationid).remove();
                        } else {
                            alert("删除失败!请先删除使用该站点的线路！")
                        }
                    }
                }
            )
        }
    </script>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>station-name</th>
            <th id="test"></th>
        </tr>
    </thead>
    <c:forEach items="${list}" var="station">
        <input type="hidden"  name="stationId" value="${station.stationId}">
        <tr id="tr${station.stationId}">
            <td>${station.stationName}</td>
            <td><a href="update-station.jsp?stationId=${station.stationId}&stationName=${station.stationName}">修改</a></td>
            <td><input type="button" value="删除" onclick="tijiao('${station.stationId}','del')"></td>
            <td><span id="massage${station.stationId}" style="color: red"></span></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

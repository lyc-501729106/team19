<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/28
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.js"></script>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>线路名</th>
        <th>费用</th>
        <th>线路描述</th>
        <th>发车间隔</th>
        <th>发车时间</th>
        <th>收车时间</th>
        <th> 所经过的站点</th>
        <th>修改</th>
        <th>删除</th>
    </tr>
    </thead>

<%--   allline 是一个 集合数组     line 就是一个集合 代表每个线路  --%>
        <c:forEach items="${allline}" var="line">

                <c:forEach items="${line}" var="a" begin="0" end="0">
              <tr id="tr${a.lineId}">
                    <td>${a.lineName}</td>
                    <td>${a.cost} ￥</td>
                    <td>${a.lineInfo}</td>
                    <td>${a.departureInterval}min</td>
                    <td>${a.departureTime}</td>
                    <td>${a.collectionTime}</td>
                </c:forEach>
<%--                站点--%>
                    <td>
                        <c:forEach items="${line}" var="a" begin="1">
                            ${a}
                        </c:forEach>
                    </td>
                <c:forEach items="${line}" var="a" begin="0" end="0">
<%--                    修改线路--%>
                    <td><button onclick="updateLine('${a.lineName}')">update</button></td>
<%--                    删除一条线路--%>
                    <td><button onclick="delLine(${a.lineId})">delete</button></td>
                </c:forEach>
            </tr>
        </c:forEach>
    <script>
        function updateLine(lineName) {
            location.href = "update-line.jsp?lineName="+lineName;
        }
        function delLine(lineId) {
            $.ajax({
                url :"LineServlet",
                dataType:"json",
                type:"post",
                data:{'opt':'del','lineId':lineId},
                success: function(rs){
                    if (rs == "1") {
                        $('#tr'+lineId).remove();
                    } else {
                        alert("删除失败！");
                    }
                }
            })
        }
    </script>
</table>
</body>
</html>

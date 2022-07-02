<%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/26
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        function test() {
            alert(1);
            sleep(1000);
            alert(2);
        }
    </script>
</head>
<body>
<button onclick="test()">test</button><span id="myspan"></span>
</body>
</html>

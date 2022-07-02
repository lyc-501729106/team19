<%@ page import="service.LineService" %>
<%@ page import="service.Impl.LineServiceImpl" %>
<%@ page import="com.sun.corba.se.spi.ior.ObjectKey" %>
<%@ page import="java.util.List" %>
<%@ page import="pojo.LineInfo" %><%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/30
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String lineName = request.getParameter("lineName");
    Object[] objects = {lineName};
    LineService ls = new LineServiceImpl();
    List[] lists = ls.oneOrAllLine(objects);
    LineInfo lineInfo = null;

    if (lists != null) {
        for (Object o : lists[0]) {
            if (o instanceof LineInfo) {
                lineInfo = (LineInfo) o;
            } else {
%>
<%
                System.out.println(o);
            }
        }
    }
%>

<%=lineInfo.getLineId()%>
<%=lineInfo.getLineName()%>
<%=lineInfo.getCost()%>
<%=lineInfo.getLineInfo()%>
<%=lineInfo.getDepartureTime()%>
<%=lineInfo.getCollectionTime()%>


</body>
</html>

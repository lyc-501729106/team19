<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 50172
  Date: 2022/6/26
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.6.0.js"></script>
    <script>
        function tijiao() {
            let lineName = $('#lineName').val();
            let cost = $('#cost').val();
            let lineDescription = $('#lineDescription').val();
            let departure_time = $('#departure_time').val();
            let departure_interval = $('#departure_interval').val();
            let collection_time = $('#collection_time').val();
            //需要将下面这个数组转换为json格式！
            let btnValues = document.getElementsByName('btnname');
            var json = {};
            for (var i = 0; i < btnValues.length; i++) {
                json[i] = btnValues[i].value; //将数组的值赋值给json对象
            }
            $.ajax(
                {
                    url: "LineServlet",
                    type: "post",
                    dataType: "json",
                    data: {
                        'opt': 'add',
                        'lineName': lineName,
                        'cost': cost,
                        'lineDescription': lineDescription,
                        'departure_time': departure_time,
                        'departure_interval': departure_interval,
                        'collection_time': collection_time,
                        'btnValues': JSON.stringify(json) //用stringify()方法将数组转换为json对象格式
                    },
                    success: function (result) {
                        if (result != '0') {
                            // location.reload();//刷新页面
                            location.href = "LineServlet?opt=allLine";
                            alert("成功！");
                        } else {
                            alert("添加线路失败！");
                        }
                    }
                }
            )
        }

        function repeatjudge() {
            let line_name = $('#lineName').val();
            $.ajax(
                {
                    url: "LineServlet",
                    type: "get",
                    dataType: "json",
                    data: {'lineName': line_name, 'opt': "repeatjudge"},
                    success: function (result) {
                        if (result == '0') {
                            $('#lineName').val('');
                            alert("线路重复，请重新输入！");
                        } else {
                            // document.getElementById('myspan').innerHTML = "可以使用";
                        }
                    }
                }
            )
        }
    </script>
    <style>
        #baseInfo {
            border: 1px solid black;
            width: 300px;
            height: 175px;
            position: relative;
            left: 40%;
        }

        #stationCheck {
            border: 1px solid black;
            position: relative;
            width: 1200px;
            height: 500px;
        }

        .in-box {
            /*position: absolute;*/
            width: 578px;
            height: 400px;
            border-left: 1px solid red;
            float: left;
        }

        #buttondiv {
            margin-top: 406px;
            margin-left: 555px;
            width: 44px;
            height: 25px;
        }

        .btnclass {

        }
        #div1{
            width: 400px;
        }
        .inline{
            display: inline;
        }
        .relative{
            position: relative;
        }
        .absolute{
            position: absolute;
        }
    </style>
</head>
<body>
<div id="div1" class="inline absolute">
    <table class="inline">
        <thead>
        <tr>
            <th>线路名</th>
            <th>费用</th>
            <th>线路描述</th>
            <th>发车间隔</th>
            <th>发车时间</th>
            <th>收车时间</th>
        </tr>
        </thead>
        <c:forEach items="${allline}" var="line">
            <tr>
                <c:forEach items="${line}" var="a" begin="0" end="0">
                    <td>${a.lineName}</td>
                    <td>${a.cost} ￥</td>
                    <td>${a.lineInfo}</td>
                    <td>${a.departureInterval}min</td>
                    <td>${a.departureTime}</td>
                    <td>${a.collectionTime}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="baseInfo">
    <table border="1">
        <tr>
            <td>线路名:</td>
            <td>
                <input type="text" onblur="repeatjudge()" id="lineName" placeholder="线路名不能重复">
            </td>
        </tr>
        <tr>
            <td>费用:</td>
            <td><select id="cost">
                <option value="1">1￥</option>
                <option value="2">2￥</option>
                <option value="3">3￥</option>
            </select></td>
        </tr>
        <tr>
            <td>线路描述:</td>
            <td><input type="text" id="lineDescription"></td>
        </tr>
        <tr>
            <td>发车时间:</td>
            <td><input type="time" id="departure_time"></td>
        </tr>
        <tr>
            <td>收车时间:</td>
            <td><input type="time" id="collection_time"></td>
        </tr>
        <tr>
            <td>发车间隔:</td>
            <td><input type="number" min="0" max="30" id="departure_interval">min</td>
        </tr>
    </table>
</div>
<div id="stationCheck">
    <div class="in-box" style="border-left: black">
        <table>
            <thead>
            <tr>
                <th>站点名称：</th>
            </tr>
            </thead>
            <script>
                function myhidden(stationid, stationName) {
                    // document.getElementById(stationid).setAttribute('hidden','hidden');
                    $('#trhidden' + stationid).attr('hidden', 'hidden');//等价 给标签添加 hidden 属性 值为 hidden
                    //往另一个盒子添加标签tr ！！
                    var tableObj = document.getElementById('tableObj');//获取添加标签的父标签id
                    var trObj = document.createElement('tr');//创建tr元素
                    trObj.setAttribute('id', "tr" + stationid);//设置标签样式，当然也可以设置其他的属性值
                    tableObj.appendChild(trObj);// 把js新建的tr放到table下
                    //再往上面新添加的tr里添加td！
                    let newTrObj = document.getElementById('tr' + stationid);
                    //第一个td
                    let tdEle1 = document.createElement('td');
                    //tdEle1.setAttribute('id',"td1"+stationid);//td1 的id
                    tdEle1.innerHTML = stationName;
                    newTrObj.appendChild(tdEle1);
                    //第二个td
                    let tdEle2 = document.createElement('td');
                    tdEle2.setAttribute('id', "td2" + stationid);//td2 的id
                    newTrObj.appendChild(tdEle2);
                    //第二个td内的 按钮
                    let td2Obj = document.getElementById('td2' + stationid);
                    let btn = document.createElement('button');
                    btn.setAttribute('id', "btn" + stationid);
                    btn.setAttribute('class', 'btnclass');
                    btn.setAttribute('name', 'btnname');
                    btn.setAttribute('value', stationid);
                    btn.innerHTML = "删除";
                    btn.setAttribute('onclick', "appear(" + stationid + ")");
                    td2Obj.appendChild(btn);
                }

                function appear(stationid) {
                    $('#trhidden' + stationid).removeAttr('hidden'); //删除某标签的  hidden 属性
                    $('#tr' + stationid).remove(); //删除一个标签
                }
            </script>
            <c:forEach items="${stationInfos}" var="stationInfo">
                <tr id="trhidden${stationInfo.stationId}">
                    <td>${stationInfo.stationName}</td>
                    <td>
                        <button onclick="myhidden('${stationInfo.stationId}','${stationInfo.stationName}')">添加</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="in-box">
        <table id="tableObj">
        </table>
    </div>
    <div id="buttondiv">
        <script>
            function notnull() {
                let lineName = $('#lineName').val();
                let cost = $('#cost').val();
                let lineDescription = $('#lineDescription').val();
                let departure_time = $('#departure_time').val();
                let departure_interval = $('#departure_interval').val();
                let collection_time = $('#collection_time').val();
                //需要将下面这个数组转换为json格式！
                let btnValues = document.getElementsByName('btnname');
                if (lineName == "") {
                    alert("线路名不能为空");
                } else if (departure_time == "") {
                    alert("请选择发车时间");
                } else if (departure_interval == "") {
                    alert("请输入发车间隔");
                } else if (collection_time == "") {
                    alert("请选择收车时间");
                } else if (btnValues.length == 0) {
                    alert("途径的站点不能为空！");
                }
            }
        </script>
        <button onmouseenter="notnull()" onclick="tijiao()">提交</button>
    </div>

</div>
</body>
</html>

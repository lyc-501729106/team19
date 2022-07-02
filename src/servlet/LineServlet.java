package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.LineInfo;
import pojo.StationInfo;
import service.Impl.LineAndStationServiceImpl;
import service.Impl.LineServiceImpl;
import service.Impl.StationServiceImpl;
import service.LineAndStationService;
import service.LineService;
import service.StationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.util.List;
/**
 * @Author 吕译辰
 * @Date 2022/6/26 - 17:05
 */
@WebServlet("/LineServlet")
public class LineServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LineServlet!");
        PrintWriter writer = response.getWriter();
        String opt = request.getParameter("opt");
        StationService stationService = new StationServiceImpl();
        LineService lineService = new LineServiceImpl();
        LineAndStationService lass = new LineAndStationServiceImpl();
        String lineName = null;
        String lineId = null;
        if (opt.equals("addLineBefore")) { //添加新线路之前  需要知道目前有那些线路
            List<StationInfo> stationInfos = stationService.allStation();
            List[] lists = lineService.oneOrAllLine(null);//一个集合 就是一个线路（包括站点）
            request.setAttribute("allline",lists);
            request.setAttribute("stationInfos", stationInfos);
            request.getRequestDispatcher("add-line.jsp").forward(request, response);
        } else if (opt.equals("add")) {//添加一个新线路
            lineName = request.getParameter("lineName");
            String cost = request.getParameter("cost");
            String lineDescription = request.getParameter("lineDescription");
            String departure_time = request.getParameter("departure_time");
            String departure_interval = request.getParameter("departure_interval");
            String collection_time = request.getParameter("collection_time");
            Object[] objects = {lineName, cost, lineDescription, departure_time, collection_time, departure_interval};
            if (lineService.insertLine(objects) != 0) { //line_info 表插入成功才能插入line_and_station 表！
                /**
                 * 将前端Ajax传来的json 转为jsonObject！
                 * 再将jsonObject存进Object[] 中 ！
                 */
                String btnValues = request.getParameter("btnValues");
                JSONObject jsonObject = JSON.parseObject(btnValues);
                Object[] lineandstations = new Object[jsonObject.size()];
                for (int i = 0; i < jsonObject.size(); i++) {
                    lineandstations[i] = jsonObject.get(i);
                }
                Object[] lineNameObjArr = {lineName};
                LineInfo lineInfo = lineService.viewLine(lineNameObjArr);
                Object[] insertLineAndStationObjArr = new Object[2];
                insertLineAndStationObjArr[0] = lineInfo.getLineId();
                /**
                 * 循环插入数据（LineAndStation表）
                 */
                int judeg = 1;
                for (Object lineandstation : lineandstations) {
                    insertLineAndStationObjArr[1] = lineandstation;
                    if (lass.insertLineAndStation(insertLineAndStationObjArr) == 0) {
                        judeg = 0;
                    }
                }
                if (judeg == 1) {
                    writer.print("1");
                } else {
                    Object[] objects1 = {lineName};
                    lineService.deleteOneLineName(objects1);
                    writer.print("0");
                }
            } else {
                writer.print("0");
            }
        } else if (opt.equals("repeatjudge")) {//线路重复的Ajax校验
            lineName = request.getParameter("lineName");
            Object[] lineNameObjArr = {lineName};
            LineInfo lineInfo = lineService.viewLine(lineNameObjArr);
            if (lineInfo == null) {
                writer.print("1");
            } else {
                writer.print("0");
            }
        } else if (opt.equals("allLine")) {//查询所有线路
            List[] lists = lineService.oneOrAllLine(null);//一个集合 就是一个线路（包括站点）
            request.setAttribute("allline",lists);
            for (List list : lists) {
                System.out.println(list);
            }
            request.getRequestDispatcher("show-line.jsp").forward(request, response);
        } else if (opt.equals("selectOneLine")) {//首页查询一条线路 并用Ajax显示在页面上
            lineName = request.getParameter("lineName");
            Object[] objects = {lineName};
            List[] lists = lineService.oneOrAllLine(objects);//看似是 集合数组 其实就一个集合 length = 1

            if (lists != null) { //有一条线路
                Object o = JSON.toJSON(lists[0]);
                System.out.println(o);
                writer.print(o);
            } else {
                writer.print("0"); //没有线路 向Ajax发个 0
            }
        } else if (opt.equals("del")) {//用前端传过来的id 来删除这条线路！
            lineId = request.getParameter("lineId");
            Object[] objects = {lineId};
            if (lineService.deleteOneLineID(objects) != 0) {
                writer.print("1");
            } else {
                writer.print("0");
            }
        } else if (opt.equals("updateLine")) {//更新线路

        }
    }
}

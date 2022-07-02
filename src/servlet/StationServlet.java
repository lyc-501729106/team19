package servlet;

import pojo.StationInfo;
import service.Impl.StationServiceImpl;
import service.StationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author 吕译辰
 * @Date 2022/6/26 - 8:25
 */
@WebServlet("/StationServlet")
public class StationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        System.out.println("StationServlet!");
        String opt = request.getParameter("opt");
        StationService stationService = new StationServiceImpl();
//        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("utf-8");//处理前端乱码！
        PrintWriter writer = response.getWriter();
        String stationName;
        String stationId;
        if (opt.equals("insertStation")) {
            StationInfo stationInfo = null;
            stationName = request.getParameter("station-name");
            stationInfo = new StationInfo();
            stationInfo.setStationName(stationName);
            int i = stationService.insertStation(stationInfo);
            if (i != 0) {
                writer.print(i);
            } else {
                writer.print(i);
            }
        } else if (opt.equals("getAll")) {
            List<StationInfo> list = stationService.allStation();
            request.setAttribute("list", list);
            request.getRequestDispatcher("show-station.jsp").forward(request, response);
        } else if (opt.equals("add-stationBeforeGetAll")) {
            List<StationInfo> list = stationService.allStation();
            request.setAttribute("list", list);
            request.getRequestDispatcher("add-station.jsp").forward(request, response);
        } else if (opt.equals("update")) {
            stationName = request.getParameter("stationName");
            stationId = request.getParameter("stationId");
            Object[] objects = {stationName, stationId};
            int i = stationService.updateStation(objects);
            writer.print(i);
        } else if (opt.equals("del")) {
            stationId = request.getParameter("stationId");
            Object[] objects = {stationId};
            int i = stationService.delStation(objects);
            if (i != 0) {
                writer.print("1");
            } else {
                writer.print("0");
            }
        } else if (opt.equals("repeatjudge")) {
            stationName = request.getParameter("stationName");
            Object[] objects = {stationName};
            StationInfo stationInfo = stationService.oneStation(objects);
            if (stationInfo == null) {
                writer.print("1");
            } else {
                writer.print("0");
            }
        }
    }
}

package servlet;

import com.alibaba.fastjson.JSON;
import pojo.AdminInfo;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 吕译辰
 * @Date 2022/6/25 - 15:41
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        UserService userService = new UserServiceImpl();
        Object[] objects = {user, pwd};
        AdminInfo adminInfo = userService.Login(objects);
//        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (adminInfo != null) {
            Object jsonObject = JSON.toJSON(adminInfo);
            out.print(jsonObject);
        }else {
            out.write("0");
        }
    }
}

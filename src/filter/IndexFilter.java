package filter;

import dao.Impl.LineDaoImpl;
import dao.LineDao;
import pojo.LineInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 在在首页  显示已经有的 线路！
 * @Author 吕译辰
 * @Date 2022/6/29 - 14:35
 */
@WebFilter(filterName = "IndexFilter",urlPatterns = "/index.jsp")
public class IndexFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LineDao lineDao = new LineDaoImpl();
        LineInfo[] lineInfos = lineDao.allLine();//所有的线路
        req.setAttribute("lineinfos",lineInfos);
        chain.doFilter(req, resp);//放行
    }
    public void init(FilterConfig config) throws ServletException {

    }

}

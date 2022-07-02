package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 设置编码的过滤器
 * @Author 吕译辰
 * @Date 2022/6/29 - 15:01
 */
@WebFilter(filterName = "CharacterEncodingFilter",urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("CharacterEncodingFilter /*");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

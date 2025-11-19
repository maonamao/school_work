import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/OperatingStudentTable")
public class MyFilter implements Filter {
    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        System.out.printf("拦截");
        try {
            // 传递请求给下一个组件
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // 捕获后续处理的异常，避免过滤器链中断无日志
            System.err.println("后续处理发生异常：" + e.getMessage());
            e.printStackTrace();
        }
        System.out.printf("放行");
    }
}

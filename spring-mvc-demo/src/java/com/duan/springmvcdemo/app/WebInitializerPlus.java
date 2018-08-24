package com.duan.springmvcdemo.app;

import com.duan.springmvcdemo.spittle.listener.MyListener;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * Created on 2018/8/22.
 *
 * @author DuanJiaNing
 */
public class WebInitializerPlus implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {

        // 添加 servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("my servlet", new HttpServlet() {
                    @Override
                    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        super.doGet(req, resp);
                    }
                });

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/my");

        // 添加 filter
        FilterRegistration.Dynamic myFilter = container.addFilter("my filter", new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            }

            @Override
            public void destroy() {

            }
        });

        // 添加监听器
        HttpSessionListener listener = new MyListener();
        container.addListener(listener.getClass());


    }

}

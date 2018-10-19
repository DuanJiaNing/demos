package com.duan.springmvcdemo.app.mvc;

import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created on 2018/10/19.
 *
 * @author DuanJiaNing
 */
public class SpecView implements View {

    // 5 DispatcherServlet#1325
    // render -> render
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream stream = response.getOutputStream();
        stream.write(request.getRequestURI().getBytes());
        stream.flush();
        response.flushBuffer();
    }

}

package com.ruochen.web.servlet.odd;

import com.alibaba.fastjson.JSON;
import com.ruochen.pojo.Brand;
import com.ruochen.service.BrandService;
import com.ruochen.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();  // json 字符串

        // JSON 字符串转为 brand 对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2. 调用 service 添加
        brandService.add(brand);

        // 3. 响应成功标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

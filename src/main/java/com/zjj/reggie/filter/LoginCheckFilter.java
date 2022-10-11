package com.zjj.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.zjj.reggie.common.BaseContext;
import com.zjj.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//检查用户是否已完成登录

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER= new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
         //1、获取本次请求的URI
        String requestURI=request.getRequestURI();

        log.info("拦截到的请求{}",requestURI);
        String [] urls=new String []{//定义不需要拦截的请求
                "/employee/login",
                "/employee/logout",
                "/backend/**",//这里希望用到通配符但这里的”**“仅仅是字符串中的”**“
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
         //2、判断本次请求是否需要处理
        boolean checkURL=checkURL(urls,requestURI);

         //3、如果不需要处理，则直接放行
        if (checkURL){
            log.info("本次请求不需要处理{}",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
         //4-1、判断登陆状态，如果以登录，则直接放行
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录，用户id为{}",request.getSession().getAttribute("employee"));
            Long empId=(Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //4-2、移动端 判断登陆状态，如果以登录，则直接放行
        if (request.getSession().getAttribute("user")!=null){
            log.info("用户已登录，用户id为{}",request.getSession().getAttribute("user"));
            Long userId=(Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }
         //5、如果未登录则返回未登录结果,通过输出流向客户端页面来响应数据
        log.info("用户未登录");
        //这里页面的跳转是通过request.js中的相应拦截器来实现的
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean checkURL(String [] urls,String requestURI){
        for (String url : urls) {
            boolean match=PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}

package com.JieCheng.filter;

import com.JieCheng.dao.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhang on 2017/5/16.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查）。
     * 第三个参数为响应的处理器，即controller。
     * 返回true，表示继续流程，调用下一个拦截器或者处理器。
     * 返回false，表示流程中断，通过response产生响应。
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object handler) throws Exception {
        String requestUrl = httpServletRequest.getRequestURI();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        //若用户输入不合法路径则跳转到错误界面
        if (requestUrl.equals("/login") || requestUrl.equals("/error")) {
            return true;
        }
        //若用户已登入，则判断权限
        if (user != null) {
            int roleId = user.getRoleId();
            //若登入者为学员，则无法请求管理界面
            if (roleId <= 1) {
                if (requestUrl.indexOf("/admin") == -1) {
                    return true;
                } else {
                    return false;
                }
                //若为管理员，则可以通过任一请求
            } else {
                return true;
            }
            //若没有用户信息，则跳转登入界面
        } else {
            //以下为四个没有用户信息时执行的方法，其余方法皆需要用户信息存在
            if (requestUrl.indexOf("/user/changeOnline") == 0 || requestUrl.indexOf("/user/findPassWord") == 0 ||
                    requestUrl.equals("/admin") || requestUrl.equals("/index")) {
                return true;
            }
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return false;
        }
        /*if (requestUrl.equals("/login") || requestUrl.equals("/error")) {
            return true;
        } else if (requestUrl.indexOf("/changeOnline") == 0 || requestUrl.indexOf("/findPassWord") == 0) {
            return true;
        } else if (requestUrl.equals("/admin") || requestUrl.equals("/index")) {
            String loginName;
            String passWord;
            if (requestUrl.equals("/index")) {
                loginName = httpServletRequest.getParameter("loginName");
                passWord = httpServletRequest.getParameter("passWord");
            } else {
                loginName = httpServletRequest.getParameter("loginName1");
                passWord = httpServletRequest.getParameter("passWord1");
            }
            if ((loginName != null && !loginName.equals("") && passWord != null && !passWord.equals(""))) {
                return true;
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
                return false;
            }
        } else {
            if (user == null) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
                return false;
            } else {
                return true;
            }
        }*/
    }

    /**
     * 当前请求进行处理之后，也就是Controller 方法调用之后执行，
     * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用。
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
     * 这个方法的主要作用是用于进行资源清理工作的。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

}

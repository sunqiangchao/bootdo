package com.bootdo.system.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.R;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	@Override
    protected boolean onLoginSuccess(AuthenticationToken token,
            Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {// 不是ajax请求
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            
            out.println("{\"code\":0,\"msg\":\"登入成功\"}");
            out.flush();
            out.close();
        } 
        return false; 

    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
            AuthenticationException e, ServletRequest request,
            ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {// 不是ajax请求
            setFailureAttribute(request, e);
            return true;
        } 
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            if("IncorrectCredentialsException".equals(e.getClass().getSimpleName())) {
                out.println("{\"code\":1,\"msg\":\"登入失败\"}");
            }else if("UnknownAccountException".equals(e.getClass().getSimpleName())) {
                out.println("{\"code\":1,\"msg\":\"登入失败,账号不存在\"}");
            }
            else{
                out.println("{\"code\":1,\"msg\":\"登入失败\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } 
        return false; 
    }
}

package cn.feifei.ssm.web.filter;

import cn.feifei.ssm.util.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyFormAuthentionToken extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //用jastson的方法把JsonResult转换成需要的json字符串格式
        //耕路成功就返回false表示不再往下执行
        response.getOutputStream().print(new ObjectMapper().writeValueAsString(new JSONResult()));
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String errorMag = "登录异常";
        if (e instanceof UnknownAccountException) {
            errorMag = "用户名不存在";
        } else if (e instanceof IncorrectCredentialsException) {
            errorMag = "密码错误";
        }
        try {
            response.getWriter().print(new ObjectMapper().writeValueAsString(new JSONResult().mark(errorMag)));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断是否是登录的请求
        if(isLoginRequest(request,response)){
            //判断是否有登录状态,有就移除掉
            Subject subject = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){
                subject.logout();
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
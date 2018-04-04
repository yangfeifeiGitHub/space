package cn.feifei.ssm.util;

import cn.feifei.ssm.domain.SystemLog;
import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.mapper.SystemLogMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SystemLogUtil {
    @Autowired
    SystemLogMapper systemLogMapper;
    public void writeLog(JoinPoint joinPoint){
        SystemLog systemLog = new SystemLog();
        //操作时间
        systemLog.setOpTime(new Date());
        //操作ip
        systemLog.setOpIp(RequestUtil.getRequest().getRemoteAddr());
        //操作用户
        Object user = SecurityUtils.getSubject().getPrincipal();
        systemLog.setOpUser((User) user);
        Object[] args = joinPoint.getArgs();
        //执行参数
        try {
            systemLog.setParams(new ObjectMapper().writeValueAsString(args));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        //执行的全限定名+方法
        String function = joinPoint.getTarget().getClass().getName()+":"+
                joinPoint.getSignature().getName();
        systemLog.setFunction(function);
        systemLogMapper.insert(systemLog);
    }
}

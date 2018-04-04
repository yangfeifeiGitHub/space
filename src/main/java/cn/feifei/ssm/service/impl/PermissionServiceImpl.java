package cn.feifei.ssm.service.impl;

import cn.feifei.ssm.domain.Permission;
import cn.feifei.ssm.mapper.PermissionMapper;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.IPermissionService;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    RequestMappingHandlerMapping handlerMapping;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission entity) {
        return permissionMapper.insert(entity);
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Permission entity) {
        return permissionMapper.updateByPrimaryKey(entity);
    }

    @Override
    public void saveOrUpdate(Permission permission) {
        if (permission.getId() != null) {
            //permissionDirMapper.updateByPrimaryKey(permission.getPermissionDir());
            permissionMapper.updateByPrimaryKey(permission);
        } else {
            permissionMapper.insert(permission);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = permissionMapper.queryForCount(qo);
        List<?> list = permissionMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public void load() {
        //获取数据库中所有的权限表达式
        List<String> resources = permissionMapper.selectAllResources();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        Collection<HandlerMethod> methods = handlerMethods.values();
        for (HandlerMethod method : methods) {
            RequiresPermissions annotation = method.getMethodAnnotation(RequiresPermissions.class);
            if(annotation != null){
                //如果数据库中没有存在则保存进数据库
                if(!resources.contains(annotation)){
                    Permission permission = new Permission();
                    permission.setName(annotation.value()[1]);
                    permission.setResource(annotation.value()[0]);
                    permissionMapper.insert(permission);
                }
            }
        }
    }

    @Override
    public List<Permission> selectByRoleId(Long id) {
        return permissionMapper.selectByRoleId(id);
    }
}

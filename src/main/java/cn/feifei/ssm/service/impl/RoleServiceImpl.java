package cn.feifei.ssm.service.impl;

import cn.feifei.ssm.domain.Permission;
import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.mapper.PermissionMapper;
import cn.feifei.ssm.mapper.RoleMapper;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.IRoleService;
import cn.feifei.ssm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        roleMapper.deleteRelation(id);
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role entity) {
        return roleMapper.insert(entity);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role entity) {
        return roleMapper.updateByPrimaryKey(entity);
    }

    @Override
    public void saveOrUpdate(Role role) {
        if (role.getId() != null) {
            roleMapper.deleteRelation(role.getId());
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                roleMapper.insertRelation(role.getId(),permission.getId());
            }
            roleMapper.updateByPrimaryKey(role);
        } else {
            roleMapper.insert(role);
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                roleMapper.insertRelation(role.getId(),permission.getId());
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = roleMapper.queryForCount(qo);
        List<?> list = roleMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public List<Long> selectByUserId(Long id) {
        return roleMapper.selectByUserId(id);
    }

}

package cn.feifei.ssm.service;

import cn.feifei.ssm.domain.Permission;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.util.PageResult;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission entity);

    void saveOrUpdate(Permission permission);

    PageResult query(QueryObject qo);

    /**
     * 加载权限
     */
    void load();

    /**
     * 通过角色id查询权限
     * @param id 角色id
     */
    List<Permission> selectByRoleId(Long id);
}

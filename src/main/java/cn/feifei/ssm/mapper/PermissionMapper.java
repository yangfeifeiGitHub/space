package cn.feifei.ssm.mapper;

import cn.feifei.ssm.domain.Permission;
import cn.feifei.ssm.query.QueryObject;

import java.util.Collection;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    List<String> selectAllResources();

    List<Permission> selectByRoleId(Long id);

    Collection<String> selectByUserId(Long id);
}
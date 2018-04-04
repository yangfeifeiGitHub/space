package cn.feifei.ssm.mapper;

import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role entity);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    List<Long> selectByUserId(Long id);

    void insertRelation(@Param("roleId") Long id, @Param("permissionId") Long id1);

    void deleteRelation(Long id);

    Role selectRoleByUsername(String username);

    Collection<String> selectByUsId(Long id);
}
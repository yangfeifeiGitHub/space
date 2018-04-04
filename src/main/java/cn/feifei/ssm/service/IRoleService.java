package cn.feifei.ssm.service;

import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.util.PageResult;

import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role entity);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity);

    void saveOrUpdate(Role role);

    PageResult query(QueryObject qo);

    /**
     * 通过用户的id去查询对应的角色
     * @param id 用户id
     * @return 角色信息
     */
    List<Long> selectByUserId(Long id);
}

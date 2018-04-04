package cn.feifei.ssm.mapper;

import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User entity);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User entity);

    void resetPassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    void insertRelation(@Param("userId") Long id, @Param("roleId") Long roleId);

    void deleteRelation(Long id);

    User selectByUsername(String username);
}
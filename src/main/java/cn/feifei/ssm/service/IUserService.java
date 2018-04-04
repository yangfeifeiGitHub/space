package cn.feifei.ssm.service;

import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.util.PageResult;

import java.util.List;

public interface IUserService {
    int deleteByPrimaryKey(Long id);

    int insert(User entity);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User entity);

    void saveOrUpdate(User user);

    void resetPassword(Long id, String newPassword, String username);

    PageResult query(QueryObject qo);
}

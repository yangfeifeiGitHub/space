package cn.feifei.ssm.service.impl;

import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.mapper.UserMapper;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.IUserService;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        userMapper.deleteRelation(id);
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User entity) {
        int insert = userMapper.insert(entity);
        List<Role> roles = entity.getRoles();
        for (Role role : roles) {
            userMapper.insertRelation(entity.getId(),role.getId());
        }
        return insert;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User entity) {
        return userMapper.updateByPrimaryKey(entity);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() != null) {
            userMapper.deleteRelation(user.getId());
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                userMapper.insertRelation(user.getId(),role.getId());
            }
            userMapper.updateByPrimaryKey(user);
        } else {
            if (user.getPassword()!=null){
                Md5Hash hash = new Md5Hash(user.getPassword(),user.getUsername(),2);
                user.setPassword(hash.toString());
            }
            userMapper.insert(user);
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                userMapper.insertRelation(user.getId(),role.getId());
            }
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = userMapper.queryForCount(qo);
        List<?> list = userMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public void resetPassword(Long id, String newPassword, String username) {
        if (id != null && newPassword != null) {
            Md5Hash md5Hash = new Md5Hash(newPassword, username, 2);
            newPassword = md5Hash.toString();
            userMapper.resetPassword(id, newPassword);
        }
    }
}

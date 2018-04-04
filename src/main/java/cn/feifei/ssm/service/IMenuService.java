package cn.feifei.ssm.service;

import cn.feifei.ssm.domain.Menu;

import java.util.List;

public interface IMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(Menu entity);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu entity);

    List<Menu> getRootMenu();

    List<Menu> getChildrenMenu(Long parentId);

    void checkPermission(List<Menu> menus);

    void saveOrUpdate(Menu menu);
}

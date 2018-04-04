package cn.feifei.ssm.service.impl;

import cn.feifei.ssm.domain.Menu;
import cn.feifei.ssm.domain.Permission;
import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.mapper.MenuMapper;
import cn.feifei.ssm.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Menu entity) {
        return menuMapper.insert(entity);
    }

    @Override
    public Menu selectByPrimaryKey(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Menu entity) {
        return menuMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<Menu> getRootMenu() {
        return menuMapper.getRootMenu();
    }

    @Override
    public List<Menu> getChildrenMenu(Long parentId) {
        return menuMapper.getChildrenMenu(parentId);
    }

    @Override
    public void saveOrUpdate(Menu menu) {
        if (menu.getId() != null) {
            menuMapper.updateByPrimaryKey(menu);
        } else {
            menuMapper.insert(menu);
        }
    }
    @Override
    public void checkPermission(List<Menu> menus) {
        //遍历取出菜单集合,取出每一个菜单
        Iterator<Menu> iterator = menus.iterator();
        Subject subject = SecurityUtils.getSubject();
        while(iterator.hasNext()){
            Menu menu = iterator.next();
            Permission permission = menu.getPermission();
            //判断给菜单是否关联权限,如果关联就进行权限判断,否则就放行
            if(permission!=null){
                //如果没有对应的用户没有该权限就移除
                if(!subject.isPermitted(permission.getResource())){
                    iterator.remove();
                    continue;
                }
            }
            //递归判断子菜单
            if(menu.getChildren().size()>0){
                this.checkPermission(menu.getChildren());
            }
        }
    }


}

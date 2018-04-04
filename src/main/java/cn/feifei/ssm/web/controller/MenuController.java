package cn.feifei.ssm.web.controller;

import cn.feifei.ssm.domain.Menu;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.IMenuService;
import cn.feifei.ssm.util.JSONResult;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("view")
    public String view(){
        return "menu/view";
    }

    @RequestMapping("getRootMenu")
    @ResponseBody
    public Object getRootMenu(QueryObject qo){
        Session session = SecurityUtils.getSubject().getSession();
        Object menus = session.getAttribute("MENUS");
        //查看session中是否有缓存起来的菜单,如果有就直接返回,没有就去查询再放入session中
        if(menus==null){
            menus = menuService.getRootMenu();
            //先做权限过滤再返回给页面
            menuService.checkPermission((List<Menu>)menus);
            session.setAttribute("MENUS",menus);
        }
        return menus;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(QueryObject qo){
        return menuService.selectAll();
    }

    @RequiresPermissions(value = {"menu:saveOrUpdate", "菜单保存/更新"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Menu menu) {
        JSONResult jsonResult = new JSONResult();
        try {
            menuService.saveOrUpdate(menu);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"menu:delete", "菜单删除"}, logical = Logical.OR)
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            menuService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }


}



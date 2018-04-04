package cn.feifei.ssm.web.controller;

import cn.feifei.ssm.domain.Permission;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.IPermissionService;
import cn.feifei.ssm.util.JSONResult;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequiresPermissions(value = {"permission:view", "权限页面"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view(){
        return "permission/view";
    }

    @RequiresPermissions(value = {"permission:list", "权限列表"}, logical = Logical.OR)
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        PageResult permissions = permissionService.query(qo);
        return permissions;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(QueryObject qo){
        return permissionService.selectAll();
    }

    @RequiresPermissions(value = {"permission:load", "权限加载"}, logical = Logical.OR)
    @RequestMapping("load")
    @ResponseBody
    public Object load() {
        JSONResult jsonResult = new JSONResult();
        try {
            permissionService.load();
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"permission:delete", "权限删除"}, logical = Logical.OR)
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            permissionService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectByRoleId")
    @ResponseBody
    public List<Permission> selectByRoleId(Long id){
        return permissionService.selectByRoleId(id);
    }
}



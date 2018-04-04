package cn.feifei.ssm.web.controller;

import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.IRoleService;
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
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequiresPermissions(value = {"role:view", "角色页面"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view(){
        return "role/view";
    }

    @RequiresPermissions(value = {"role:list", "角色列表"}, logical = Logical.OR)
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        PageResult roles = roleService.query(qo);
        return roles;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(QueryObject qo){
        return roleService.selectAll();
    }

    @RequiresPermissions(value = {"role:saveOrUpdate", "角色保存/更新"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Role role) {
        JSONResult jsonResult = new JSONResult();
        try {
            roleService.saveOrUpdate(role);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"role:delete", "角色删除"}, logical = Logical.OR)
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            roleService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectByUserId")
    @ResponseBody
    public List<Long> selectByUserId(Long id) {
        return roleService.selectByUserId(id);
    }

}



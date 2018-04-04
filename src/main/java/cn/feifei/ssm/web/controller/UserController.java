package cn.feifei.ssm.web.controller;

import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.query.UserQueryObject;
import cn.feifei.ssm.service.IUserService;
import cn.feifei.ssm.util.JSONResult;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequiresPermissions(value = {"user:view", "用户页面"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view(){
        return "user/view";
    }

    @RequiresPermissions(value = {"user:list", "用户列表"}, logical = Logical.OR)
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(UserQueryObject qo){
        PageResult users = userService.query(qo);
        return users;
    }

    @RequiresPermissions(value = {"user:saveOrUpdate", "用户保存/更新"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(User user) {
        JSONResult jsonResult = new JSONResult();
        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"user:delete", "用户删除"}, logical = Logical.OR)
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            userService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"user:resetPassword", "用户设置密码"}, logical = Logical.OR)
    @RequestMapping("resetPassword")
    @ResponseBody
    public Object resetPassword(Long id, String newPassword, String username) {
        JSONResult jsonResult = new JSONResult();
        try {
            userService.resetPassword(id, newPassword, username);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }
}



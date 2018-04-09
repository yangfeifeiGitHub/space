package cn.feifei.ssm.web.controller;

import cn.feifei.ssm.domain.LandApply;
import cn.feifei.ssm.service.ILandApplyService;
import cn.feifei.ssm.util.JSONResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("landApply")
public class LandApplyController {
    @Autowired
    private ILandApplyService landApplyService;

    @RequiresPermissions(value = {"landApply:view", "用地申请页面"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view(){
        return "landApply/view";
    }


    @RequiresPermissions(value = {"landApply:saveOrUpdate", "用户保存/更新"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(LandApply landApply) {
        JSONResult jsonResult = new JSONResult();
        try {
            landApplyService.saveOrUpdate(landApply);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"landApply:delete", "用户删除"}, logical = Logical.OR)
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            landApplyService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }
}



package cn.feifei.ssm.web.controller;

import cn.feifei.ssm.domain.SiteInfo;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.query.SiteInfoQueryObject;
import cn.feifei.ssm.service.ISiteInfoService;
import cn.feifei.ssm.util.JSONResult;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("siteInfo")
public class SiteInfoController {
    @Autowired
    private ISiteInfoService siteInfoService;

    @RequestMapping("view")
    public String view(){
        return "siteInfo/view";
    }

    //@RequiresPermissions(value = {"siteInfo:list", "用户列表"}, logical = Logical.OR)
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SiteInfoQueryObject qo){
        PageResult siteInfos = siteInfoService.query(qo);
        return siteInfos;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return siteInfoService.selectAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SiteInfo siteInfo) {
        JSONResult jsonResult = new JSONResult();
        try {
            siteInfoService.saveOrUpdate(siteInfo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            siteInfoService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

}



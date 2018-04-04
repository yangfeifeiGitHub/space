package cn.feifei.ssm.service;

import cn.feifei.ssm.domain.SiteInfo;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.util.PageResult;

import java.util.List;

public interface ISiteInfoService {
    int deleteByPrimaryKey(Long id);

    int insert(SiteInfo entity);

    SiteInfo selectByPrimaryKey(Long id);

    List<SiteInfo> selectAll();

    int updateByPrimaryKey(SiteInfo entity);

    void saveOrUpdate(SiteInfo siteInfo);

    PageResult query(QueryObject qo);
}

package cn.feifei.ssm.mapper;

import cn.feifei.ssm.domain.SiteInfo;
import cn.feifei.ssm.query.QueryObject;

import java.util.List;

public interface SiteInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SiteInfo entity);

    SiteInfo selectByPrimaryKey(Long id);

    List<SiteInfo> selectAll();

    int updateByPrimaryKey(SiteInfo entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);
}
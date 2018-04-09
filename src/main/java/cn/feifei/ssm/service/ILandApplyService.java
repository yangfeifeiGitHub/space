package cn.feifei.ssm.service;

import cn.feifei.ssm.domain.LandApply;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.util.PageResult;

import java.util.List;

public interface ILandApplyService {
    int deleteByPrimaryKey(Long id);

    int insert(LandApply entity);

    LandApply selectByPrimaryKey(Long id);

    List<LandApply> selectAll();

    int updateByPrimaryKey(LandApply entity);

    void saveOrUpdate(LandApply LandApply);
}

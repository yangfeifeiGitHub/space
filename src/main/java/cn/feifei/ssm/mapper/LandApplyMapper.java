package cn.feifei.ssm.mapper;

import cn.feifei.ssm.domain.LandApply;
import java.util.List;

public interface LandApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LandApply entity);

    LandApply selectByPrimaryKey(Long id);

    List<LandApply> selectAll();

    int updateByPrimaryKey(LandApply entity);
}
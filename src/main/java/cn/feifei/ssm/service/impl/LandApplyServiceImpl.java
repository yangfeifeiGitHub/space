package cn.feifei.ssm.service.impl;

import cn.feifei.ssm.domain.LandApply;
import cn.feifei.ssm.mapper.LandApplyMapper;
import cn.feifei.ssm.service.ILandApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandApplyServiceImpl implements ILandApplyService {
    @Autowired
    private LandApplyMapper landApplyMapper;


    public int deleteByPrimaryKey(Long id) {
        return landApplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LandApply entity) {
        return landApplyMapper.insert(entity);
    }

    @Override
    public LandApply selectByPrimaryKey(Long id) {
        return landApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LandApply> selectAll() {
        return landApplyMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LandApply entity) {
        return landApplyMapper.updateByPrimaryKey(entity);
    }

    @Override
    public void saveOrUpdate(LandApply landApply) {
        if (landApply.getId() != null){
            landApplyMapper.updateByPrimaryKey(landApply);
        }else {
            landApplyMapper.insert(landApply);
        }
    }
}

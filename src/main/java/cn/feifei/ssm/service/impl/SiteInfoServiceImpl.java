package cn.feifei.ssm.service.impl;

import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.domain.SiteInfo;
import cn.feifei.ssm.mapper.SiteInfoMapper;
import cn.feifei.ssm.query.QueryObject;
import cn.feifei.ssm.service.ISiteInfoService;
import cn.feifei.ssm.util.PageResult;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SiteInfoServiceImpl implements ISiteInfoService {
    @Autowired
    private SiteInfoMapper siteInfoMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return siteInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SiteInfo entity) {
        int insert = siteInfoMapper.insert(entity);
        return insert;
    }

    @Override
    public SiteInfo selectByPrimaryKey(Long id) {
        return siteInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SiteInfo> selectAll() {
        return siteInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SiteInfo entity) {
        return siteInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    public void saveOrUpdate(SiteInfo siteInfo) {
        if (siteInfo.getId() != null) {
            siteInfoMapper.updateByPrimaryKey(siteInfo);
        } else {
            siteInfoMapper.insert(siteInfo);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = siteInfoMapper.queryForCount(qo);
        List<?> list = siteInfoMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

}

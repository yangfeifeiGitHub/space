package cn.feifei.ssm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class LandApply {
    private Long id;

    private SiteInfo siterinfo;     //用地对象

    private Date applyDate;     //申请时间

    private String timeQuantum;     //申请时间段


}
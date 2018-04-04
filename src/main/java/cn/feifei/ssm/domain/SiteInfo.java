package cn.feifei.ssm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SiteInfo {
    private Long id;

    private String sn; //用地信息编码

    private String name; //用地名称

    private String area; //用地面积

    private int status; //用地状态
}
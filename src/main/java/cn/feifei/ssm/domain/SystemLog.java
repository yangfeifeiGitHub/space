package cn.feifei.ssm.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter@Getter
public class SystemLog extends Domain{

    private User opUser;

    private Date opTime;

    private String opIp;

    private String function;

    private String params;

}
package cn.feifei.ssm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Permission extends Domain{
    private Long id;

    private String name;

    private String resource;
}
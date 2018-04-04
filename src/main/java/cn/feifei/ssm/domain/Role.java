package cn.feifei.ssm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class Role extends Domain{

    private String name;

    private String sn;

    private List<Permission> permissions = new ArrayList<>();
}
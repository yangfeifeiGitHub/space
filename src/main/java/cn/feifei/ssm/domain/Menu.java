package cn.feifei.ssm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class Menu extends Domain{

    private String text;

    private String url;

    private Menu parent;

    private List<Menu> children = new ArrayList<>();

    private Permission permission;
}
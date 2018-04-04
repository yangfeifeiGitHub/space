package cn.feifei.ssm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class User extends Domain{

    private String username;

    private String password;

    private String sn;

    private String email;

    private Boolean Admin;

    private List<Role> roles = new ArrayList<>();

}
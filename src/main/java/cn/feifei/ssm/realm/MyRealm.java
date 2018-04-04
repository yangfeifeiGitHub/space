package cn.feifei.ssm.realm;

import cn.feifei.ssm.domain.Role;
import cn.feifei.ssm.domain.User;
import cn.feifei.ssm.mapper.PermissionMapper;
import cn.feifei.ssm.mapper.RoleMapper;
import cn.feifei.ssm.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class MyRealm extends AuthorizingRealm{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("----");
        User user = (User) principals.getPrimaryPrincipal();

        /*Role r = roleMapper.selectRoleByUsername(user.getUsername());
        if(r.getSn().equals("admin")){
            info.addStringPermission("*:*");
        }else{
            //从数据库查询出来
            info.addStringPermissions(permissionMapper.selectByUserId(user.getId()));
            info.addRoles(roleMapper.selectByUsId(user.getId()));
        }*/
        if(user.getAdmin()){
            info.addStringPermission("*:*");
            info.addRole("admin");
        }else{
            //从数据库查询出来
            info.addStringPermissions(permissionMapper.selectByUserId(user.getId()));
            info.addRoles(roleMapper.selectByUsId(user.getId()));
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        Object principal = token.getPrincipal();
        User user = userMapper.selectByUsername((String)principal);
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getUsername()),getName());
    }
}

package com.kyx.shiro;

import com.kyx.blog.entity.Permission;
import com.kyx.blog.entity.Roles;
import com.kyx.blog.entity.Users;
import com.kyx.blog.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户
        Users users= (Users) SecurityUtils.getSubject().getPrincipal();
        List<String> list =new ArrayList<>();
        List<String> roleList =new ArrayList<>();
        Set<Roles> roles =users.getRoles();
        if (CollectionUtils.isNotEmpty(roles)){
            for (Roles roles1: roles){
                roleList.add(roles1.getRname());
                Set<Permission> permissionSet =roles1.getPermissionsSet();
                if(CollectionUtils.isNotEmpty(permissionSet)){
                   for (Permission permission:permissionSet){
                       list.add(permission.getPname());
                   }
                }
            }
        }
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        info.addRoles(roleList);
        info.addStringPermissions(list);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token携带了用户信息
        UsernamePasswordToken token1 =(UsernamePasswordToken) token;
        //获取前端输入的用户账号
        String username =((UsernamePasswordToken) token).getUsername();
        Users users =userService.findUserBy(username);
        if (users==null){
            throw new UnknownAccountException("用户不存在");
        }else{
            //盐值
            ByteSource credentials =ByteSource.Util.bytes(users.getUsername());
            SimpleAuthenticationInfo info =new SimpleAuthenticationInfo();
            info.setCredentials(users.getPassword());
            info.setCredentialsSalt(credentials);
            info.setPrincipals((new SimplePrincipalCollection(users,getName())));
            return info;
        }
    }
}

package com.wsf.demo.config;

import com.wsf.demo.entity.Resource;
import com.wsf.demo.entity.Role;
import com.wsf.demo.entity.User;
import com.wsf.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取用户信息
        String username = token.getPrincipal().toString();

        //按用户名查询用户对象
        User user = this.userService.findByUserName(username);
        if (user == null) {

            // 这里返回后会报出对应异常
            return null;
        } else {

            //查询用户角色和权限
            user = this.userService.findUserRoleAndResource(user);

            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPwd(), getName());
            return simpleAuthenticationInfo;
        }
    }

    /**
     * 授权
     * @param collection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {

        //获取登录用户
        User user = (User) collection.getPrimaryPrincipal();

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoleList()) {

            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleCode());
            for (Resource resource : role.getResourceList()) {

                //添加权限
                simpleAuthorizationInfo.addStringPermission(resource.getResCode());
            }
        }
        return simpleAuthorizationInfo;
    }
}

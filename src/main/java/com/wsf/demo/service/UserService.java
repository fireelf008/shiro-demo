package com.wsf.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wsf.demo.entity.*;
import com.wsf.demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    public User findByUserName(String userName) {

        //查询用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", userName);
        return this.userMapper.selectOne(userQueryWrapper);
    }

    /**
     * 查询用户权限
     * @param user
     * @return
     */
    public User findUserRoleAndResource(User user) {

        //查询用户角色关联
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", user.getId());
        List<UserRole> userRoleList = this.userRoleMapper.selectList(userRoleQueryWrapper);

        //查询角色
        List<Long> roleIdList = userRoleList.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.in("id", roleIdList);
        List<Role> roleList = this.roleMapper.selectList(roleQueryWrapper);
        user.setRoleList(roleList);

        for (Role role : user.getRoleList()) {

            //查询角色资源关联
            QueryWrapper<RoleResource> roleResourceQueryWrapper = new QueryWrapper<>();
            roleResourceQueryWrapper.eq("role_id", role.getId());
            List<RoleResource> roleResourceList = this.roleResourceMapper.selectList(roleResourceQueryWrapper);

            //查询资源
            List<Long> resourceIdList = roleResourceList.stream().map(res -> res.getResId()).collect(Collectors.toList());
            QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
            resourceQueryWrapper.in("id", resourceIdList);
            List<Resource> resourceList = this.resourceMapper.selectList(resourceQueryWrapper);
            role.setResourceList(resourceList);
        }

        System.out.println("用户" + user.getUsername() + "角色数量" + user.getRoleList().size());
        for (Role role : user.getRoleList()) {
            System.out.println("角色" + role.getRoleName() + "-" + role.getRoleCode() + "资源数量" + role.getResourceList().size());
            for (Resource resource : role.getResourceList()) {
                System.out.println("资源" + resource.getResName() + "-" + resource.getResCode() + "-" + resource.getResUrl());
            }
        }
        return user;
    }

    public User add(User user) {
        this.userMapper.insert(user);
        return user;
    }
}

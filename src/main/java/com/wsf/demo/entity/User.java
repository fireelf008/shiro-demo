package com.wsf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "tb_user")
public class User extends Model<User> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String pwd;
    private String sex;
    private Integer age;
    private Date birthday;
    private Integer enabled;
    private Integer version;

    @TableField(exist = false)
    private List<Role> roleList;

    @TableField(exist = false)
    private SimpleAuthorizationInfo authorizationInfo;
}

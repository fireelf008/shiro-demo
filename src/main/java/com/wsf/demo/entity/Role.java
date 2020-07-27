package com.wsf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "tb_role")
public class Role extends Model<Role> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String roleCode;
    private String roleName;
    private Integer enabled;
    private Integer version;

    @TableField(exist = false)
    private List<Resource> resourceList;
}

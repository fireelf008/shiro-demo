package com.wsf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName(value = "tb_role_resource")
public class RoleResource extends Model<RoleResource> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long roleId;
    private Long resId;
    private Integer enabled;
    private Integer version;
}

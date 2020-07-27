package com.wsf.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName(value = "tb_resource")
public class Resource extends Model<Resource> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String resCode;
    private String resName;
    private String resUrl;
    private Integer sort;
    private Long parentId;
    private Integer enabled;
    private Integer version;
}

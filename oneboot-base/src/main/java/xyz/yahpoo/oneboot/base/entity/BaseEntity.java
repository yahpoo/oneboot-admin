package xyz.yahpoo.oneboot.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class BaseEntity<T extends Model> extends Model{

    @TableId("id")
    private String id;


}

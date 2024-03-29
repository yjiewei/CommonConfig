package com.yjiewei.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author yjiewei
 * @date 2021/7/23
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private String id;
    private String name;

    //定义权限的集合
    private List<Perms> perms;

}

package cn.itcast.service;

import cn.itcast.domain.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole>findAllRole();

    void addRole(SysRole sysRole);


    SysRole findRoleById(Integer roleId);

    void managerRolePermission(Integer roleId, Integer[] ids);
}

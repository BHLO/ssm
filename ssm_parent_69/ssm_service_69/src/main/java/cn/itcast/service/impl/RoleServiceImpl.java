package cn.itcast.service.impl;

import cn.itcast.dao.RoleDao;
import cn.itcast.domain.SysRole;
import cn.itcast.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SysRole> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public void addRole(SysRole sysRole) {
        roleDao.addRole(sysRole);
    }

    @Override
    public SysRole findRoleById(Integer roleId) {
        return roleDao.findRoleById(roleId);
    }

    @Override
    public void managerRolePermission(Integer roleId, Integer[] ids) {
        roleDao.remove(roleId);
        if (ids != null && ids.length > 0) {
            for (Integer pid : ids) {
                roleDao.save(roleId, pid);
            }
        }
    }

}

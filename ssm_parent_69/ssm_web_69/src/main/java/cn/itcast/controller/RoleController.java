package cn.itcast.controller;

import cn.itcast.domain.Permission;
import cn.itcast.domain.SysRole;
import cn.itcast.service.PermissionService;
import cn.itcast.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.management.relation.Role;
import java.util.List;

@Controller
@RequestMapping("role")
//@RolesAllowed("ROLE_ADMIN")
@Secured("ROLE_ADMIN")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    //查询角色
    @RequestMapping("findAllRole")
    public String findAllRole(Model model) {
        List<SysRole> roleList = roleService.findAllRole();
        model.addAttribute("roleList", roleList);
        return "/role/roleList";
    }

    //接收请求跳转添加页面
    @RequestMapping("addRoleUI")
    public String addRoleUI() {
        return "/role/roleAdd";
    }

    @RequestMapping("addRole")
    public String addRole(SysRole sysRole) {
        roleService.addRole(sysRole);
        return "redirect:/role/findAllRole";
    }

    @RequestMapping("managerRolePermissionUI")
    public String managerRolePermissionUI(Integer roleId, Model model) {
        //通过id得到当前的角色对象
        SysRole role = roleService.findRoleById(roleId);
        //得到当前角色对象的权限信息
        List<Permission> rolePermissions = role.getPermissions();
        //循环遍历权限集合 拼接成权限的字符串用于页面的包含判断
        if (rolePermissions != null && rolePermissions.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Permission rolePermission : rolePermissions) {
                sb.append(rolePermission.getPermissionName() + ",");
            }
            model.addAttribute("pStr", sb.toString());
        }
        //所有的权限列表
        List<Permission> permissions = permissionService.findAllPermission();
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissions);
        return "role/managerRolePermission";
    }

    @RequestMapping("managerRolePermission")
    public String managerRolePermission(Integer roleId, Integer[] ids) {
        roleService.managerRolePermission(roleId, ids);
        return "redirect:/role/findAllRole";
    }

}

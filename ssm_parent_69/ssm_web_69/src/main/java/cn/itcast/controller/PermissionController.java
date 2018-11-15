package cn.itcast.controller;

import cn.itcast.domain.Permission;
import cn.itcast.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("permission")
@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAllPermission")
    public String findAllPermission(Model model) {
        List<Permission> permissionList = permissionService.findAllPermission();
        model.addAttribute("permissionList", permissionList);
        return "/permission/permissionList";
    }

    @RequestMapping("permissionAddUI")
    public String permissionAddUI(){
        return "/permission/permissionAdd";
    }
    @RequestMapping("permissionAdd")
    public  String permissionAdd(Permission permission){
        permissionService.savePermission(permission);
       return "redirect:/permission/findAllPermission";
    }
}

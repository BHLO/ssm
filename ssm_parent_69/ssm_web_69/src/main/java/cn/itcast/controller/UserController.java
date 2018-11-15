package cn.itcast.controller;

import cn.itcast.domain.SysRole;
import cn.itcast.domain.SysUser;
import cn.itcast.service.RoleService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("findAllUser")
    public String findAllUser(Model model) {
        List<SysUser> userList = userService.findAllUser();
        model.addAttribute("userList", userList);
        return "user/userList";
    }

    @RequestMapping("/addUserUI")
    public String addUserUI() {
        return "user/userAdd";
    }


    @RequestMapping("userAdd")
    public String userAdd(SysUser sysUser) {
        userService.userAdd(sysUser);
        return "redirect:/user/findAllUser";
    }

    //接收用户id ,查询所有角色，用户名，用户的角色三种数据
    @RequestMapping("managerUserUI")
    public String managerUserUI(Integer id, Model model) {
        //1.获取用户的信息
        SysUser sysUser =  userService.findUserById(id);

        //2.通过user对象获取所有的角色列表
        List<SysRole> userRoles = sysUser.getRoles();
        //将所有的角色信息拼装成一个角色字符串用于页面的包含判断
        if (userRoles != null && userRoles.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (SysRole userRole : userRoles) {
                sb.append(userRole.getRoleName() + ",");
            }
            model.addAttribute("rStr", sb.toString());
        }
        //3.所有的角色数据
        List<SysRole> roles = roleService.findAllRole();
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roles", roles);
        return "user/managerUserRole";
    }

    /**
     * 功能描述: 根据传递的用户id 和角色的id数组维护关系
     * @param: [userId, ids]
     * @return: java.lang.String
     * @auther: wyan
     * @date: 2018/11/10 12:31
     */
    @RequestMapping("managerUserRole")
    public  String managerUserRole(Integer userId,Integer [] ids){
        userService.managerUserRole(userId,ids);
        return "redirect:/user/findAllUser";
    }

    @RequestMapping("userDetail")
    public  String userDetail(Integer id,Model model){
        SysUser user= userService.findUserById(id);
        model.addAttribute("user",user);
        return "user/userDetail";
    }
}

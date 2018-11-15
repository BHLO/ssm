package cn.itcast.service;

import cn.itcast.domain.SysUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService {
    List<SysUser> findAllUser();

    void userAdd(SysUser sysUser);

    SysUser findUserById(Integer id);


    void managerUserRole(Integer userId, Integer[] ids);
}

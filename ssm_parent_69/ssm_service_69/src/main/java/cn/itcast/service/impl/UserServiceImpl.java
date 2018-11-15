package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.SysRole;
import cn.itcast.domain.SysUser;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    BCryptPasswordEncoder pwdEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询得到数据库用户对象
        SysUser sysUser = userDao.findUserByName(username);
        //创建集合存储用户拥有的角色权限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //获取用户角色
         List<SysRole> roles = sysUser.getRoles();
       if (roles != null && roles.size() > 0) {
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //明文密码验证
        User user = new User(sysUser.getUsername(),sysUser.getPassword(),sysUser.getStatus()==1?true:false,true,true,true,authorities);
        return user;
    }

    @Override
    public List<SysUser> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public void userAdd(SysUser sysUser) {
        //将接收的明文密码加密
        String pwd = sysUser.getPassword();
        String encode = pwdEncoder.encode(pwd);
        sysUser.setPassword(encode);
        userDao.userAdd(sysUser);
    }

    @Override
    public SysUser findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public void managerUserRole(Integer userId, Integer[] ids) {
        //先根据用户id删除原始角色
        userDao.removeRoleFromUser(userId);
        //添加角色
       if(ids!=null&&ids.length>0){
            for (Integer rid : ids) {
                userDao.saveUserRole(userId,rid);
            }

        }
    }



}

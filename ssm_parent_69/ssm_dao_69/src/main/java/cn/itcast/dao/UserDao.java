package cn.itcast.dao;

import cn.itcast.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "roles",javaType = List.class , many = @Many(select = "cn.itcast.dao.RoleDao.findUserRoleById"))
    })
    SysUser findUserByName(String username);

    @Select("select * from sys_user")
    List<SysUser> findAllUser();

    @Insert("insert into sys_user values (common_sequence.nextval,#{ username},#{email},#{password},#{phoneNum},#{status})")
    void userAdd(SysUser sysUser);

    @Select("select * from sys_user where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "roles",javaType = List.class , many = @Many(select = "cn.itcast.dao.RoleDao.findUserRoleById"))
    })
    SysUser findUserById(Integer id);

    @Insert("insert into sys_user_role values(#{userId},#{roleId})")
    void saveUserRole(@Param("userId")Integer userId, @Param("roleId") Integer roleId);

    @Delete("delete from sys_user_role where userId = #{userId}")
    void removeRoleFromUser(Integer userId);

}

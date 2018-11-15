package cn.itcast.dao;

import cn.itcast.domain.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from sys_Role")
    List<SysRole> findAllRole();

    @Insert("insert into sys_role values(common_sequence.nextval,#{roleName },#{roleDesc})")
    void addRole(SysRole sysRole);


    //根据用户id查询所有角色
    @Select("select * from sys_role where id in(select roleId from sys_user_role where userId=#{userId})")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "permissions", javaType = List.class,
                    many = @Many(select = "cn.itcast.dao.PermissionDao.findPermissionsByRoleId"))
    })
    List<SysRole> findUserRoleById(Integer userId);

    @Select("select * from sys_role where id=#{roleId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "permissions", javaType = List.class,
                    many = @Many(select = "cn.itcast.dao.PermissionDao.findPermissionsByRoleId"))
    })
    SysRole findRoleById(Integer roleId);

    @Delete("delete sys_role_permission where roleId=#{roleId}")
    void remove(Integer roleId);

    @Insert("insert into sys_role_permission values(#{pid},#{roleId})")
    void save(@Param("roleId")Integer roleId, @Param("pid")Integer pid);

}

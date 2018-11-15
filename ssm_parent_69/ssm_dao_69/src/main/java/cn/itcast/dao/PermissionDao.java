package cn.itcast.dao;

import cn.itcast.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from sys_permission")
    List<Permission> findAllPermission();


    @Insert("insert into sys_permission values(common_sequence.nextval,#{permissionName},#{url})")
    void savePermission(Permission permission);

    //根据角色id得到角色的权限集合
    @Select("select * from sys_permission where id in (select permissionid from sys_role_permission where roleId = #{roleId})")
    List<Permission> findPermissionsByRoleId(Integer roleId);
}



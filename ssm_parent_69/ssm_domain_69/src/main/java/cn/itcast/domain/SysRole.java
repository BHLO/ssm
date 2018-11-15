package cn.itcast.domain;

import java.io.Serializable;
import java.util.List;

public class SysRole implements Serializable {
    private Long id;
    private String roleName;
    private String roleDesc;
    private List<Permission>permissions;

    public SysRole(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {

        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public SysRole(Long id, String roleName, String roleDesc) {

        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    public SysRole() {

    }
}

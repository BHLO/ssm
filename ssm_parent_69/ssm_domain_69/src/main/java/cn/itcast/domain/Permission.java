package cn.itcast.domain;

import java.io.Serializable;

public class Permission implements Serializable {
    private String id;
    private String permissionName;
    private String url;

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Permission(String id, String permissionName, String url) {

        this.id = id;
        this.permissionName = permissionName;
        this.url = url;
    }

    public Permission() {

    }
}

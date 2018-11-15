<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <security:authentication property="principal.username"/>
                </p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/pages/main.jsp">
                    <i class="fa fa-dashboard"></i>
                    <span>首页</span>
                </a>
            </li>

            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="treeview">
                    <a href="#"> <i class="fa fa-cogs"></i>
                        <span>系统管理</span>
                        <span class="pull-right-container"> <i
                                class="fa fa-angle-left pull-right"></i>
				    </span>
                    </a>
                    <ul class="treeview-menu">

                        <li id="system-setting1">
                            <a href="${pageContext.request.contextPath}/user/findAllUser">
                                <i class="fa fa-circle-o"></i> 用户管理
                            </a></li>
                        <li id="system-setting2">
                            <a href="${pageContext.request.contextPath}/role/findAllRole">
                                <i class="fa fa-circle-o"></i> 角色管理
                            </a></li>
                        <li id="system-setting3">
                            <a href="${pageContext.request.contextPath}/permission/findAllPermission">
                                <i class="fa fa-circle-o"></i> 权限管理
                            </a></li>
                        <li id="system-setting4">
                            <a href="${pageContext.request.contextPath}/pages/syslog-list.jsp">
                                <i class="fa fa-circle-o"></i> 访问日志
                            </a></li>
                    </ul>
                </li>
            </security:authorize>



            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cube"></i>
                       <span>基础数据</span>
                       <span class="pull-right-container">
                          <i class="fa fa-angle-left pull-right"></i>
				       </span>
                </a>

                <ul class="treeview-menu">

                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_PRODUCT')">
                      <li id="system-setting5">
                          <a href="${pageContext.request.contextPath}/product/findAllProduct">
                             <i class="fa fa-circle-o"></i> 产品管理
                          </a>
                      </li>
                    </security:authorize>

                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_ORDERS')">
                      <li id="system-setting">
                          <a href="${pageContext.request.contextPath}/order/findAllOrder">
                              <i class="fa fa-circle-o"></i> 订单管理
                          </a>
                      </li>
                    </security:authorize>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
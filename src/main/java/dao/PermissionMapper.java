package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Permission;

import java.util.List;

public interface PermissionMapper {
    List<Permission> getPermissionList();

    List<Permission> getUserPermissions(int adminid);

    void addAdminToGroup(@Param("adminid") int adminid, @Param("groupid") int groupid);

    void deleteAdminFromGroup(@Param("adminid") int adminid, @Param("groupid") int groupid);
}

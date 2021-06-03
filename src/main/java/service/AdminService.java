package service;

import bean.Admin;
import exceptions.ServiceException;

import java.util.List;

public interface AdminService {
    Admin getAdminByToken(String username, String passwd);

    void createAdmin(Admin admin);

    List<Admin> getAdminList();
}

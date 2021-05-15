package service;

import bean.Admin;
import exceptions.ServiceException;

public interface AdminService {
    Admin getAdminByToken(String username, String passwd);

    void createAdmin(Admin admin) throws ServiceException;

}

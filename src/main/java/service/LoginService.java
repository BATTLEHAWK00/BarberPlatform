package service;

import exceptions.ServiceException;

public interface LoginService {
    String Login(String username, String passwd) throws ServiceException;
    void Logout();
}

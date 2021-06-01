package service;

import exceptions.ServiceException;
import org.springframework.transaction.annotation.Transactional;

public interface LoginService {
    String Login(String username, String passwd) throws ServiceException;

    void Logout(String accessToken);
}

package service;

public interface LoginService {
    String Login(String username, String passwd);

    void Logout(String accessToken);
}

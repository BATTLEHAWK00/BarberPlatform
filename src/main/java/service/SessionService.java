package service;

public interface SessionService {
    String Login(String username, String passwd);

    void Logout(String accessToken);

    void CleanSession();
}

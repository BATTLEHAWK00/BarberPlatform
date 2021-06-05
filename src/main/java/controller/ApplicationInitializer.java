package controller;

import dao.SessionMapper;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;
import pojo.Session;
import service.SessionService;

import javax.servlet.ServletContext;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class ApplicationInitializer implements ServletContextAware {
    public static final int SESSION_TIMEOUT = 3;

    @Autowired
    @Setter
    private SessionMapper sessionMapper;

    @Autowired
    @Setter
    private SessionService sessionService;

    private void clearOldSessions() {
        System.out.println("Clean Sessions...");
        int cnt = 0;
        for (Session session : sessionMapper.getSessionList()) {
            if (sessionService.validateToken(session.getAccess_token())) {
                sessionMapper.deleteSession(session.getAccess_token());
                cnt++;
            }
        }
        System.out.printf("Clean Sessions Complete(%d cleared out).\n", cnt);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        clearOldSessions();
    }
}

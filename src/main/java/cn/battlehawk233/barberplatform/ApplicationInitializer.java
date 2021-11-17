package cn.battlehawk233.barberplatform;

import cn.battlehawk233.barberplatform.pojo.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import cn.battlehawk233.barberplatform.service.SessionService;

import javax.servlet.ServletContext;

@Component
public class ApplicationInitializer implements ServletContextAware {
    private final Logger logger = LogManager.getLogger(ApplicationInitializer.class);

    private final SessionService sessionService;

    @Autowired
    public ApplicationInitializer(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    private void clearOldSessions() {
        logger.info("Clean Sessions...");
        int cnt = 0;
        for (Token token : sessionService.getSessionList()) {
            if (!sessionService.validateToken(token.getAccess_token())) {
                sessionService.deleteSession(token.getAccess_token());
                cnt++;
            }
        }
        logger.info(String.format("Clean Sessions Complete(%d cleared out).\n", cnt));
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        clearOldSessions();
    }
}

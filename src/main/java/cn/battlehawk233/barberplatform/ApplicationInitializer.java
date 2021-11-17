package cn.battlehawk233.barberplatform;

import cn.battlehawk233.barberplatform.pojo.Token;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import cn.battlehawk233.barberplatform.service.SessionService;

import javax.servlet.ServletContext;

@Component
@AllArgsConstructor
@Order(1)
public class ApplicationInitializer implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);
    private final SessionService sessionService;

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
    public void run(ApplicationArguments args) throws Exception {
        clearOldSessions();
    }
}

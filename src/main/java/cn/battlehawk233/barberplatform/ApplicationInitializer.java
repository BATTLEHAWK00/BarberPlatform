package cn.battlehawk233.barberplatform;

import cn.battlehawk233.barberplatform.pojo.Token;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import cn.battlehawk233.barberplatform.service.TokenService;

@Component
@AllArgsConstructor
@Order(1)
public class ApplicationInitializer implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);
    private final TokenService tokenService;

    private void clearOldSessions() {
        logger.info("Clean Sessions...");
        int cnt = 0;
        for (Token token : tokenService.getSessionList()) {
            if (!tokenService.validateToken(token.getAccessToken())) {
                tokenService.deleteSession(token.getAccessToken());
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

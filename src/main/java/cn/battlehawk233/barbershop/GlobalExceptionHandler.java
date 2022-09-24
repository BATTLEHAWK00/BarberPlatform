package cn.battlehawk233.barbershop;

import cn.battlehawk233.barbershop.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger logger = LoggerFactory.getLogger("ExceptionHandler");

    @ExceptionHandler(Exception.class)
    public Response<Object> handleAll(Exception e) {
        logger.error("Unhandled exception:", e);
        return Response.FAIL_WITH_MESSAGE(500, "Internal Server Error");
    }
}

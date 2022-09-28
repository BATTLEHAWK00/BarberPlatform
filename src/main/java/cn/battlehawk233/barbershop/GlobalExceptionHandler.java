package cn.battlehawk233.barbershop;

import cn.battlehawk233.barbershop.exception.BackendException;
import cn.battlehawk233.barbershop.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger logger = LoggerFactory.getLogger("ExceptionHandler");

    @ExceptionHandler(BackendException.class)
    public Response<Object> handleServiceException(BackendException e) {
        return Response.FAIL_WITH_MESSAGE(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Response<Object> handleUnauthorizedException(AccessDeniedException e) {
        return Response.FAIL_WITH_MESSAGE(403, "你没有权限执行此操作");
    }

    @ExceptionHandler(Exception.class)
    public Response<Object> handleAll(Exception e) {
        logger.error("Unhandled exception:", e);
        return Response.FAIL_WITH_MESSAGE(500, "Internal Server Error");
    }
}

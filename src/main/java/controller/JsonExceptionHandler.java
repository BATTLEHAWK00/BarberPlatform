package controller;

import exceptions.BackendException;
import exceptions.IllegalRequestException;
import exceptions.InternalException;
import exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ControllerAdvice
public class JsonExceptionHandler {
    @ExceptionHandler
    public ModelAndView HandleError(BackendException exception) {
        if (exception instanceof InternalException) {
            exception.printStackTrace();
        }
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        mv.addObject("code", exception.getStatus());
        mv.addObject("msg", exception.getMessage());
        mv.setStatus(HttpStatus.valueOf(exception.getStatus()));
        return mv;
    }

    @ExceptionHandler
    public ModelAndView HandleError(Exception exception) {
        if (exception instanceof MethodArgumentTypeMismatchException)
            return HandleError(new IllegalRequestException("参数错误!"));
        else if (exception instanceof MissingServletRequestParameterException)
            return HandleError(new IllegalRequestException("缺少参数!"));
        else if (exception instanceof NoHandlerFoundException)
            return HandleError(new IllegalRequestException("访问路径无效!", 404));
        else if (exception instanceof HttpRequestMethodNotSupportedException)
            return HandleError(new IllegalRequestException("请求方法不被允许!", 403));
        exception.printStackTrace();
        return HandleError(new InternalException("内部错误!"));
    }
}
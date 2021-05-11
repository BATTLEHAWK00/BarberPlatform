package controller;

import bean.Admin;
import bean.Order;
import bean.Response;
import dao.OrderMapper;
import exceptions.BackendException;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.TestService;

import java.util.List;

@RestController
public class TestController {
    @Setter
    @Autowired
    private TestService testService;
    @RequestMapping("/hello")
    public Response Hello(){
        var res=new Response();
        res.setMsg("hello");
        res.setData(testService.Test());
        return res;
    }
    @RequestMapping(value = "/hello/{asd}",method = RequestMethod.GET)
    public int asd(@PathVariable("asd") int asd){
        return asd;
    }
}
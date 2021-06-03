package controller;

import bean.Response;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.StoredValueService;

import java.math.BigDecimal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/storedvalue")
public class StoredValueController {
    @Autowired
    @Setter
    private StoredValueService storedValueService;

    @RequestMapping(value = "/log/list", method = {RequestMethod.GET})
    public Response getStoredValueList() {
        Response response = new Response();
        response.setData(storedValueService.getLogList());
        return response;
    }

    @RequestMapping(value = "/log/list/uid/{uid}", method = {RequestMethod.GET})
    public Response getStoredValueListByUser(@PathVariable("uid") int uid) {
        Response response = new Response();
        response.setData(storedValueService.getLogListByUser(uid));
        return response;
    }

    @RequestMapping(value = "/uid/{uid}/recharge", method = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST})
    public Response rechargeValue(@PathVariable("uid") int uid,
                                  @RequestParam("value") BigDecimal value,
                                  @RequestParam("type") int type,
                                  @RequestParam(value = "remark", required = false) String remark
    ) {
        Response response = new Response();
        storedValueService.recharge(uid, value, type, remark);
        return response;
    }

    @RequestMapping(value = "/uid/{uid}/cost", method = {RequestMethod.PUT, RequestMethod.GET})
    public Response costValue(@PathVariable("uid") int uid,
                              @RequestParam("value") BigDecimal value,
                              @RequestParam(value = "remark", required = false) String remark
    ) {
        Response response = new Response();
        storedValueService.costValue(uid, value, remark);
        return response;
    }
}

package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.bean.Response;
import cn.battlehawk233.barberplatform.pojo.StoredValueLog;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.battlehawk233.barberplatform.service.StoredValueService;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/storedvalue")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StoredValueController {
    private final StoredValueService storedValueService;

    @RequestMapping(value = "/log/list", method = {RequestMethod.GET})
    public List<StoredValueLog> getStoredValueList() {
        return storedValueService.getLogList();
    }

    @RequestMapping(value = "/log/list/uid/{uid}", method = {RequestMethod.GET})
    public List<StoredValueLog> getStoredValueListByUser(@PathVariable("uid") int uid) {
        return storedValueService.getLogListByUser(uid);
    }

    @RequestMapping(value = "/uid/{uid}/recharge", method = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST})
    public boolean rechargeValue(@PathVariable("uid") int uid,
                                  @RequestParam("value") BigDecimal value,
                                  @RequestParam("type") int type,
                                  @RequestParam(value = "remark", required = false) String remark
    ) {
        storedValueService.recharge(uid, value, type, remark);
        return true;
    }

    @RequestMapping(value = "/uid/{uid}/cost", method = {RequestMethod.PUT, RequestMethod.GET})
    public boolean costValue(@PathVariable("uid") int uid,
                              @RequestParam("value") BigDecimal value,
                              @RequestParam(value = "remark", required = false) String remark
    ) {
        storedValueService.costValue(uid, value, remark);
        return true;
    }
}

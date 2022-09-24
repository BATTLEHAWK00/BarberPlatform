package cn.battlehawk233.barbershop.layer.service;

import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import cn.battlehawk233.barbershop.layer.dao.MoneyFlowLogRepo;
import cn.battlehawk233.barbershop.entity.Admin;
import cn.battlehawk233.barbershop.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    private final AdminRepo adminRepo;
    private final MoneyFlowLogRepo moneyFlowLogRepo;

    public void test() {

        var admin = new Admin();
        admin.setName("asd");
        admin.setPhone("1239018239");
        admin.setPasswd("asdfasdf");
        admin.setSalt("asdfasfd");

        try {
            adminRepo.save(admin);

        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("asdasd");
        }
    }
}

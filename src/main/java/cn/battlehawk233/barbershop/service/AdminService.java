package cn.battlehawk233.barbershop.service;

import cn.battlehawk233.barbershop.dao.AdminRepository;
import cn.battlehawk233.barbershop.entity.Admin;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    private final AdminRepository adminRepository;

    public void test() {
        var admin = new Admin();
        admin.setName("asd");
        admin.setPhone("1239018239");
        admin.setPasswd("asdfasdf");
        admin.setSalt("asdfasfd");

        adminRepository.save(admin);
    }
}

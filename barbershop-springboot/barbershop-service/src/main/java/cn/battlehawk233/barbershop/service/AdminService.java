package cn.battlehawk233.barbershop.service;

import cn.battlehawk233.barbershop.entity.Admin;
import cn.battlehawk233.barbershop.dao.AdminRepo;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger("AdminService");
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    public void register(Admin admin, String passwd) {
        var salt = IdUtil.fastSimpleUUID();
        var digest = passwordEncoder.encode(passwd + salt);
        admin.setSalt(salt);
        admin.setPasswd(digest);
        adminRepo.save(admin);
        logger.info("创建管理员：{}", admin.getName());
    }

    public Collection<Admin> search(String text) {
        Collection<Admin> adminCollection = new ArrayList<>();
        adminCollection.addAll(adminRepo.findByNameStartsWith(text));
        adminCollection.addAll(adminRepo.findByPhoneStartsWith(text));
        return adminCollection;
    }

    public void test() {
        var admin = Admin.builder().name("asd").phone("1239018239").build();
        register(admin, "123456");
    }
}

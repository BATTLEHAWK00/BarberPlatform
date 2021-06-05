package service.impl;

import dao.AdminMapper;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Admin;
import service.AdminService;
import util.SecurityUtil;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    @Setter
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByToken(String username, String passwd) {
        return adminMapper.getAdminByToken(username, passwd);
    }

    @Transactional
    @Override
    public void createAdmin(Admin admin) {
        if (adminMapper.getAdminByName(admin.getName()) != null) {
            throw new ServiceException("已有相同名字的管理员注册!", 400);
        } else if (adminMapper.getAdminByPhone(admin.getPhone()) != null) {
            throw new ServiceException("已有相同手机号注册!", 400);
        }
        admin.setSalt(SecurityUtil.getInstance().genUUID8());
        admin.setPasswd(SecurityUtil.instance.getSaltMD5(admin.getPasswd(), admin.getSalt()));
        adminMapper.registerAdmin(admin);
    }

    @Override
    public List<Admin> getAdminList() {
        List<Admin> adminList = adminMapper.getAdminList();
        for (Admin i : adminList) {
            i.setPasswd(null);
            i.setSalt(null);
        }
        return adminList;
    }
}

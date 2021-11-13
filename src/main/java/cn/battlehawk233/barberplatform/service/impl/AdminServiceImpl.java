package cn.battlehawk233.barberplatform.service.impl;

import cn.battlehawk233.barberplatform.dao.AdminMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.service.AdminService;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.battlehawk233.barberplatform.pojo.Admin;

import java.util.List;

@Service("AdminService")
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final AdminMapper adminMapper;

	@Override
	public Admin getAdminByToken(String username, String passwd) {
		return adminMapper.getAdminByToken(username, passwd);
	}

	@Override
	public Admin getAdminByID(int adminid) {
		return adminMapper.getAdminByID(adminid);
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

	@Override
	public void updatePhone(int id, String phone) {
		adminMapper.updatePhone(id, phone);
	}

	@Override
	public void updatePasswd(int id, String passwd) {
		String salt = SecurityUtil.getInstance().genUUID8();
		adminMapper.updatePasswd(id, SecurityUtil.getInstance().getSaltMD5(passwd, salt), salt);
	}

	@Override
	public void updateOpenID(int id, String openid) {
		adminMapper.updateOpenID(id, openid);
	}

	@Override
	public void updateRemark(int id, String remark) {
		adminMapper.updateRemark(id, remark);
	}

	@Override
	public void updateLastOrder(int id, int orderid) {
		adminMapper.updateLastOrder(id, orderid);
	}

	@Override
	public void updateGender(int id, Integer gender) {
		adminMapper.updateGender(id, gender);
	}

	@Override
	public void updateName(int id, String name) {
		adminMapper.updateName(id, name);
	}

	@Transactional
	@Override
	public void updateAdmin(Admin admin) {
		adminMapper.updateGender(admin.getAdminId(), admin.getGender());
		if (admin.getName() != null)
			adminMapper.updateName(admin.getAdminId(), admin.getName());
		if (admin.getPhone() != null)
			adminMapper.updatePhone(admin.getAdminId(), admin.getPhone());
		if (admin.getPasswd() != null)
			updatePasswd(admin.getAdminId(), admin.getPasswd());
		if (admin.getRemark() != null)
			adminMapper.updateRemark(admin.getAdminId(), admin.getRemark());
	}


}

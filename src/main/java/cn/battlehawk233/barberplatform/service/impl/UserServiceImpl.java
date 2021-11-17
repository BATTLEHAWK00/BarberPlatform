package cn.battlehawk233.barberplatform.service.impl;

import cn.battlehawk233.barberplatform.dao.UserMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.pojo.User;
import cn.battlehawk233.barberplatform.service.UserService;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("UserService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;

	@Override
	public void registerUser(User user) throws ServiceException {
		user.setSalt(IdUtil.simpleUUID());
		user.setPasswd(SecurityUtil.getInstance().getDigestWithSalt(user.getPasswd(), user.getSalt()));
		if (userMapper.getUserByPhone(user.getPhone()) != null) {
			throw new ServiceException("手机号已被注册!", 400);
		}
		userMapper.registerUser(user);
	}

	@Override
	public void updatePasswd(int id, String passwd) throws ServiceException {
		User user = userMapper.getUserByID(id);
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		} else if (user.getPasswd().equals(SecurityUtil.getInstance().getDigestWithSalt(passwd, user.getSalt()))) {
			throw new ServiceException("新密码和原密码相同!", 400);
		}
		String salt = IdUtil.simpleUUID();
		String passwdMd5 = SecurityUtil.getInstance().getDigestWithSalt(passwd, salt);
		userMapper.updatePasswd(id, passwdMd5, salt);
	}

	@Override
	public void updateRemark(int id, String remark) throws ServiceException {
		User user = userMapper.getUserByID(id);
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		userMapper.updateRemark(id, remark);
	}

	@Override
	public void updateBirthDate(int id, Date birthdate) throws ServiceException {
		User user = userMapper.getUserByID(id);
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		} else if (user.getBirthDate() != null) {
			throw new ServiceException("生日不可再更改!", 400);
		}
		userMapper.updateBirthDate(id, birthdate);
	}

	@Override
	public void updatePhone(int id, String phone) throws ServiceException {
		User user = userMapper.getUserByID(id);
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		userMapper.updatePhone(id, phone);
	}

	@Override
	public void updateName(int id, String name) {
		User user = userMapper.getUserByID(id);
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		userMapper.updateName(id, name);
	}

	@Override
	public void updateGender(int id, Integer gender) {
		User user = userMapper.getUserByID(id);
		if (user == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		userMapper.updateGender(id, gender);
	}

	@Transactional
	@Override
	public void updateUser(User user) {
		userMapper.updateGender(user.getUserId(), user.getGender());
		if (user.getUsername() != null) {
			userMapper.updateName(user.getUserId(), user.getUsername());
		}
		if (user.getPhone() != null) {
			userMapper.updatePhone(user.getUserId(), user.getPhone());
		}
		if (user.getRemark() != null) {
			userMapper.updateRemark(user.getUserId(), user.getRemark());
		}
		if (user.getPasswd() != null) {
			updatePasswd(user.getUserId(), user.getPasswd());
		}
		if (user.getBirthDate() != null) {
			userMapper.updateBirthDate(user.getUserId(), user.getBirthDate());
		}
	}

	@Override
	public List<User> getUserList() throws ServiceException {
		return userMapper.getUserList();
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		if (userMapper.getUserByID(id) == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		userMapper.deleteUser(id);
	}

	@Override
	public boolean verifyPasswd(int id, String passwd) {
		if (userMapper.getUserByID(id) == null) {
			throw new ServiceException("用户不存在!", 400);
		}
		Map<String, Object> mp = userMapper.getUserPasswd(id);
		String passwd_md5 = SecurityUtil.getInstance().getDigestWithSalt(passwd, (String) mp.get("salt"));
		return passwd_md5.equals(mp.get("passwd"));
	}

}

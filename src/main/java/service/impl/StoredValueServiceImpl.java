package service.impl;

import dao.StoredValueMapper;
import dao.UserMapper;
import exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.StoredValueLog;
import pojo.User;
import service.StoredValueService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StoredValueServiceImpl implements StoredValueService {
    private final StoredValueMapper storedValueMapper;
    private final UserMapper userMapper;

    @Autowired
    public StoredValueServiceImpl(StoredValueMapper storedValueMapper, UserMapper userMapper) {
        this.storedValueMapper = storedValueMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void writeLog(StoredValueLog storedValueLog) throws ServiceException {
        if (userMapper.getUserByID(storedValueLog.getUserid()) == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        storedValueMapper.writeRechargeLog(storedValueLog);
    }

    @Override
    public List<StoredValueLog> getLogList() {
        return storedValueMapper.getRechargeLogList();
    }

    @Override
    public List<StoredValueLog> getLogListByUser(int uid) {
        if (userMapper.getUserByID(uid) == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        return storedValueMapper.getRechargeLogListByUser(uid);
    }

    @Transactional
    @Override
    public void recharge(int uid, BigDecimal amount, int type, String remark) {
        User user = userMapper.getUserByID(uid);
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        }
        StoredValueLog storedValueLog = new StoredValueLog();
        storedValueLog.setUserid(uid);
        storedValueLog.setRecharge_amount(amount);
        storedValueLog.setRemark(remark);
        storedValueLog.setType(type);
        storedValueMapper.updateValue(uid, user.getBalance().add(amount));
        storedValueMapper.writeRechargeLog(storedValueLog);
    }

    @Transactional
    @Override
    public void costValue(int uid, BigDecimal amount, String remark) {
        User user = userMapper.getUserByID(uid);
        if (user == null) {
            throw new ServiceException("用户不存在!", 400);
        } else if (user.getBalance().compareTo(amount) < 0) {
            throw new ServiceException("用户余额不足!", 400);
        }
        StoredValueLog storedValueLog = new StoredValueLog();
        storedValueLog.setUserid(uid);
        storedValueLog.setRecharge_amount(amount.negate());
        storedValueLog.setRemark(remark);
        storedValueMapper.updateValue(uid, user.getBalance().subtract(amount));
        storedValueMapper.writeRechargeLog(storedValueLog);
    }
}

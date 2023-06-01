package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.AccessRecordBean;
import com.javaguides.arduino.dao.AccessRecordDAO;
import com.javaguides.arduino.entity.AccessRecord;
import com.javaguides.arduino.entity.DoorLock;
import com.javaguides.arduino.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessRecordService {

    private final AccessRecordDAO accessRecordDAO;

    public AccessRecordService(AccessRecordDAO accessRecordDAO) {
        this.accessRecordDAO = accessRecordDAO;
    }

    public List<AccessRecordBean> searchAll() {
        return accessRecordDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<AccessRecordBean> getById(Integer id) {
        Optional<AccessRecord> optional = accessRecordDAO.findById(id);
        if (optional.isPresent()) {
            AccessRecord entity = optional.get();
            AccessRecordBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    private AccessRecordBean convertEntityToBean(AccessRecord accessRecord) {
        AccessRecordBean accessRecordBean = new AccessRecordBean();
        accessRecordBean.setId(accessRecord.getId());
        accessRecordBean.setUserId(accessRecord.getUser().getId());
        accessRecordBean.setUserName(accessRecord.getUser().getName());
        accessRecordBean.setLockId(accessRecord.getLock().getId());
        accessRecordBean.setLockName(accessRecord.getLock().getName());
        accessRecordBean.setAccessTime(accessRecord.getAccessTime());
        accessRecordBean.setSuccess(accessRecord.getSuccess());
        return accessRecordBean;
    }


    public List<AccessRecordBean> searchByParam(User userId, String userName, DoorLock lockId, String lockName) {
        if (userId != null && lockId != null) {
            return this.searchByUserIdAndLockId(userId, userName, lockId, lockName);
        } else if (userId != null) {
            return this.searchByUserId(userId, userName, lockName);
        } else if (lockId != null) {
            return this.searchByLockId(lockId, userName, lockName);
        } else if (userName != null && lockName != null) {
            return this.searchByUserNameAndLockName(userName, lockName);
        } else if (userName != null) {
            return this.searchByUserName(userName);
        } else if (lockName != null) {
            return this.searchByLockName(lockName);
        }
        return this.searchAll();
    }

    private List<AccessRecordBean> searchByUserId(User userId, String userName, String lockName) {
        if (userName != null && lockName != null) {
            return accessRecordDAO.getByUserId(userId)
                    .stream()
                    .filter(p -> p.getUser().getName().contains(userName))
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else if (userName != null) {
            return accessRecordDAO.getByUserId(userId)
                    .stream()
                    .filter(p -> p.getUser().getName().contains(userName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else if (lockName != null) {
            return accessRecordDAO.getByUserId(userId)
                    .stream()
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else {
            return accessRecordDAO.getByUserId(userId)
                    .stream()
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        }
    }

    private List<AccessRecordBean> searchByUserName(String userName) {
        return accessRecordDAO.findAll()
                .stream()
                .filter(p -> p.getUser().getName().contains(userName))
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    private List<AccessRecordBean> searchByLockId(DoorLock lockId, String userName, String lockName) {
        if (userName != null && lockName != null) {
            return accessRecordDAO.getByLockId(lockId)
                    .stream()
                    .filter(p -> p.getUser().getName().contains(userName))
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else if (userName != null) {
            return accessRecordDAO.getByLockId(lockId)
                    .stream()
                    .filter(p -> p.getUser().getName().contains(userName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else if (lockName != null) {
            return accessRecordDAO.getByLockId(lockId)
                    .stream()
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else {
            return accessRecordDAO.getByLockId(lockId)
                    .stream()
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        }
    }

    private List<AccessRecordBean> searchByLockName(String lockName) {
        return accessRecordDAO.findAll()
                .stream()
                .filter(p -> p.getLock().getName().contains(lockName))
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }


    private List<AccessRecordBean> searchByUserIdAndLockId(User userId, String userName, DoorLock lockId, String lockName) {
        if (userName != null && lockName != null) {
            return accessRecordDAO.getByUserIdAndLockId(userId, lockId)
                    .stream()
                    .filter(p -> p.getUser().getName().contains(userName))
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else if (userName != null) {
            return accessRecordDAO.getByUserIdAndLockId(userId, lockId)
                    .stream()
                    .filter(p -> p.getUser().getName().contains(userName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else if (lockName != null) {
            return accessRecordDAO.getByUserIdAndLockId(userId, lockId)
                    .stream()
                    .filter(p -> p.getLock().getName().contains(lockName))
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        } else {
            return accessRecordDAO.getByUserIdAndLockId(userId, lockId)
                    .stream()
                    .map(this::convertEntityToBean)
                    .collect(Collectors.toList());
        }
    }

    private List<AccessRecordBean> searchByUserNameAndLockName(String username, String lockName) {
        return accessRecordDAO.findAll()
                .stream()
                .filter(p -> p.getUser().getName().contains(username))
                .filter(p -> p.getLock().getName().contains(lockName))
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }
}

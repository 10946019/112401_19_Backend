package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.LockBean;
import com.javaguides.arduino.dao.LockDAO;
import com.javaguides.arduino.entity.Lock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LockService {

    private final LockDAO lockDAO;

    public LockService(LockDAO lockDAO) {
        this.lockDAO = lockDAO;
    }

    public void save(LockBean lockBean) {
        Lock saveResult = lockDAO.saveAndFlush(convertBeanToEntity(lockBean));
        lockDAO.save(saveResult);
    }

    public List<LockBean> searchAll(){
        return lockDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<LockBean> getById(Integer id) {
        Optional<Lock> optional = lockDAO.findById(id);
        if (optional.isPresent()) {
            Lock entity = optional.get();
            LockBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(LockBean lockBean) {
        Optional<Lock> optional = lockDAO.findById(lockBean.getId());
        Lock lock = optional.get();
        lock.setName(lockBean.getName());
        lockDAO.update(lock);
    }

    public void delete(Integer id) {
        lockDAO.deleteById(id);
    }

    private LockBean convertEntityToBean(Lock lock){
        LockBean lockBean = new LockBean();
        lockBean.setId(lock.getId());
        lockBean.setName(lock.getName());
        return lockBean;
    }

    private Lock convertBeanToEntity(LockBean lockBean){
        Lock lock = new Lock();
        lock.setId(lockBean.getId());
        lock.setName(lockBean.getName());
        return lock;
    }
}

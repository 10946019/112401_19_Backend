package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.DoorLockBean;
import com.javaguides.arduino.dao.DoorLockDAO;
import com.javaguides.arduino.entity.DoorLock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoorLockService {

    private final DoorLockDAO doorLockDAO;

    public DoorLockService(DoorLockDAO doorLockDAO) {
        this.doorLockDAO = doorLockDAO;
    }

    public void save(DoorLockBean doorLockBean) {
        DoorLock saveResult = doorLockDAO.saveAndFlush(convertBeanToEntity(doorLockBean));
        doorLockDAO.save(saveResult);
    }

    public List<DoorLockBean> searchAll() {
        return doorLockDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<DoorLockBean> getById(Integer id) {
        Optional<DoorLock> optional = doorLockDAO.findById(id);
        if (optional.isPresent()) {
            DoorLock entity = optional.get();
            DoorLockBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public List<DoorLockBean> getByNameKeyword(String name) {
        return doorLockDAO.findAll()
                .stream()
                .filter(p -> p.getName().contains(name))
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public void update(DoorLockBean doorLockBean) {
        Optional<DoorLock> optional = doorLockDAO.findById(doorLockBean.getId());
        DoorLock doorLock = optional.get();
        doorLock.setName(doorLockBean.getName());
        doorLockDAO.update(doorLock);
    }

    public void delete(Integer id) {
        doorLockDAO.deleteById(id);
    }

    private DoorLockBean convertEntityToBean(DoorLock doorLock) {
        DoorLockBean doorLockBean = new DoorLockBean();
        doorLockBean.setId(doorLock.getId());
        doorLockBean.setName(doorLock.getName());
        return doorLockBean;
    }

    private DoorLock convertBeanToEntity(DoorLockBean doorLockBean) {
        DoorLock doorLock = new DoorLock();
        doorLock.setId(doorLockBean.getId());
        doorLock.setName(doorLockBean.getName());
        return doorLock;
    }
}

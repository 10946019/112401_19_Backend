package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.DoorLockBean;
import com.javaguides.arduino.dao.DoorLockDAO;
import com.javaguides.arduino.entity.DoorLock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//告訴spring這是一個service
@Service
public class DoorLockService {

    private final DoorLockDAO doorLockDAO;

    //DAO為建構元參數(DAO不一定只有一個，依需求)
    public DoorLockService(DoorLockDAO doorLockDAO) {
        this.doorLockDAO = doorLockDAO;
    }

    // 新增，不一定要寫，可以要用到再回來寫
    public void save(DoorLockBean doorLockBean) {
        // convertBeanToEntity，bean 接受到的資料轉換成 entity
        DoorLock saveResult = doorLockDAO.saveAndFlush(convertBeanToEntity(doorLockBean));
        doorLockDAO.save(saveResult);
    }

    // 查詢DoorLock全部資料
    public List<DoorLockBean> searchAll() {
        return doorLockDAO.findAll()
                .stream()
                //把 input stream 的每一個元素，映射成 output stream 的另外一個元素
                // DAO 從 entity(對應資料庫) 查到的資料轉換成 bean(前端資料需要的格式)
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<DoorLockBean> getById(Integer id) {
        // Optional，避免null造成程式壞掉，所以會先用optional包裝，最後要用的時候再檢查
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
                // 過濾，符合條件的留下
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

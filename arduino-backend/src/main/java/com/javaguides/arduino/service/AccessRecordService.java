package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.AccessRecordBean;
import com.javaguides.arduino.dao.AccessRecordDAO;
import com.javaguides.arduino.entity.AccessRecord;
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

    public List<AccessRecordBean> searchAll(){
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

    private AccessRecordBean convertEntityToBean(AccessRecord accessRecord){
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

}

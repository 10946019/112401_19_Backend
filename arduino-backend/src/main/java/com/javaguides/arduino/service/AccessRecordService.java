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

    public void save(AccessRecordBean accessRecordBean) {
        AccessRecord saveResult = accessRecordDAO.saveAndFlush(convertBeanToEntity(accessRecordBean));
        accessRecordDAO.save(saveResult);
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

    public void update(AccessRecordBean accessRecordBean) {
        Optional<AccessRecord> optional = accessRecordDAO.findById(accessRecordBean.getId());
        AccessRecord accessRecord = optional.get();
        accessRecord.setUserId(accessRecordBean.getUserId());
        accessRecord.setLockId(accessRecordBean.getLockId());
        accessRecord.setAccessTime(accessRecordBean.getAccessTime());
        accessRecord.setSuccess(accessRecordBean.getSuccess());
        accessRecordDAO.update(accessRecord);
    }

    public void delete(Integer id) {
        accessRecordDAO.deleteById(id);
    }

    private AccessRecordBean convertEntityToBean(AccessRecord accessRecord){
        AccessRecordBean accessRecordBean = new AccessRecordBean();
        accessRecordBean.setId(accessRecord.getId());
        accessRecordBean.setUserId(accessRecord.getUserId());
        accessRecordBean.setLockId(accessRecord.getLockId());
        accessRecordBean.setAccessTime(accessRecord.getAccessTime());
        accessRecordBean.setSuccess(accessRecord.getSuccess());
        return accessRecordBean;
    }

    private AccessRecord convertBeanToEntity(AccessRecordBean accessRecordBean){
        AccessRecord accessRecord = new AccessRecord();
        accessRecord.setId(accessRecordBean.getId());
        accessRecord.setUserId(accessRecordBean.getUserId());
        accessRecord.setLockId(accessRecordBean.getLockId());
        accessRecord.setAccessTime(accessRecordBean.getAccessTime());
        accessRecord.setSuccess(accessRecordBean.getSuccess());
        return accessRecord;
    }
}

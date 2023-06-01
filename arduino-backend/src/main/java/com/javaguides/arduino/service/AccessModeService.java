package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.AccessModeBean;
import com.javaguides.arduino.dao.AccessModeDAO;
import com.javaguides.arduino.entity.AccessMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessModeService {

    private final AccessModeDAO accessModeDAO;

    public AccessModeService(AccessModeDAO accessModeDAO) {
        this.accessModeDAO = accessModeDAO;
    }

    public void save(AccessModeBean accessModeBean) {
        AccessMode saveResult = accessModeDAO.saveAndFlush(convertBeanToEntity(accessModeBean));
        accessModeDAO.save(saveResult);
    }

    public List<AccessModeBean> searchAll(){
        return accessModeDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<AccessModeBean> getById(Integer id) {
        Optional<AccessMode> optional = accessModeDAO.findById(id);
        if (optional.isPresent()) {
            AccessMode entity = optional.get();
            AccessModeBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(AccessModeBean accessModeBean) {
        Optional<AccessMode> optional = accessModeDAO.findById(accessModeBean.getId());
        AccessMode accessMode = optional.get();
        accessMode.setName(accessModeBean.getName());
        accessModeDAO.update(accessMode);
    }

    public void delete(Integer id) {
        accessModeDAO.deleteById(id);
    }

    private AccessModeBean convertEntityToBean(AccessMode accessMode){
        AccessModeBean accessModeBean = new AccessModeBean();
        accessModeBean.setId(accessMode.getId());
        accessModeBean.setName(accessMode.getName());
        return accessModeBean;
    }

    private AccessMode convertBeanToEntity(AccessModeBean accessModeBean){
        AccessMode accessMode = new AccessMode();
        accessMode.setId(accessModeBean.getId());
        accessMode.setName(accessModeBean.getName());
        return accessMode;
    }
}

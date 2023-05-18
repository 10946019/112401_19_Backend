package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.AccessLogsBean;
import com.javaguides.arduino.dao.AccessLogsDAO;
import com.javaguides.arduino.entity.AccessLogs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessLogsService {

    private final AccessLogsDAO accessLogsDAO;

    public AccessLogsService(AccessLogsDAO accessLogsDAO) {
        this.accessLogsDAO = accessLogsDAO;
    }

    public void save(AccessLogsBean accessLogsBean) {
        AccessLogs saveResult = accessLogsDAO.saveAndFlush(convertBeanToEntity(accessLogsBean));
        accessLogsDAO.save(saveResult);
    }

    public List<AccessLogsBean> searchAll() {
        return accessLogsDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<AccessLogsBean> getById(Integer id) {
        Optional<AccessLogs> optional = accessLogsDAO.findById(id);
        if (optional.isPresent()) {
            AccessLogs entity = optional.get();
            AccessLogsBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(AccessLogsBean accessLogsBean) {
        Optional<AccessLogs> optional = accessLogsDAO.findById(accessLogsBean.getId());
        AccessLogs accessLogs = optional.get();
        accessLogs.setUserAccount(accessLogsBean.getUserAccount());
        accessLogs.setDoorId(accessLogsBean.getDoorId());
        accessLogs.setAccessTime(accessLogsBean.getAccessTime());
        accessLogs.setAccessResult(accessLogsBean.getAccessResult());
        accessLogs.setAccessMethod(accessLogsBean.getAccessMethod());
        accessLogsDAO.update(accessLogs);
    }

    public void delete(Integer id) {
        accessLogsDAO.deleteById(id);
    }

    private AccessLogsBean convertEntityToBean(AccessLogs accessLogs) {
        AccessLogsBean accessLogsBean = new AccessLogsBean();
        accessLogsBean.setId(accessLogs.getId());
        accessLogsBean.setUserAccount(accessLogs.getUserAccount());
        accessLogsBean.setDoorId(accessLogs.getDoorId());
        accessLogsBean.setAccessTime(accessLogs.getAccessTime());
        accessLogsBean.setAccessMethod(accessLogs.getAccessMethod());
        accessLogsBean.setAccessResult(accessLogs.getAccessResult());
        return accessLogsBean;
    }

    private AccessLogs convertBeanToEntity(AccessLogsBean accessLogsBean) {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setId(accessLogsBean.getId());
        accessLogs.setUserAccount(accessLogsBean.getUserAccount());
        accessLogs.setDoorId(accessLogsBean.getDoorId());
        accessLogs.setAccessTime(accessLogsBean.getAccessTime());
        accessLogs.setAccessMethod(accessLogsBean.getAccessMethod());
        accessLogs.setAccessResult(accessLogsBean.getAccessResult());
        return accessLogs;
    }
}

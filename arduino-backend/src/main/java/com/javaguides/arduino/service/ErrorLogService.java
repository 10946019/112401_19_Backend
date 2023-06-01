package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.ErrorLogBean;
import com.javaguides.arduino.dao.ErrorLogDAO;
import com.javaguides.arduino.entity.ErrorLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ErrorLogService {

    private final ErrorLogDAO errorLogDAO;

    public ErrorLogService(ErrorLogDAO errorLogDAO) {
        this.errorLogDAO = errorLogDAO;
    }

    public void save(ErrorLogBean errorLogBean) {
        ErrorLog saveResult = errorLogDAO.saveAndFlush(convertBeanToEntity(errorLogBean));
        errorLogDAO.save(saveResult);
    }

    public List<ErrorLogBean> searchAll(){
        return errorLogDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<ErrorLogBean> getById(Integer id) {
        Optional<ErrorLog> optional = errorLogDAO.findById(id);
        if (optional.isPresent()) {
            ErrorLog entity = optional.get();
            ErrorLogBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(ErrorLogBean errorLogBean) {
        Optional<ErrorLog> optional = errorLogDAO.findById(errorLogBean.getId());
        ErrorLog errorLog = optional.get();
        errorLog.setLockId(errorLogBean.getLockId());
        errorLog.setErrorMessage(errorLogBean.getErrorMessage());
        errorLogDAO.update(errorLog);
    }

    public void delete(Integer id) {
        errorLogDAO.deleteById(id);
    }

    private ErrorLogBean convertEntityToBean(ErrorLog errorLog){
        ErrorLogBean errorLogBean = new ErrorLogBean();
        errorLogBean.setId(errorLog.getId());
        errorLogBean.setLockId(errorLog.getLockId());
        errorLogBean.setErrorMessage(errorLog.getErrorMessage());
        return errorLogBean;
    }

    private ErrorLog convertBeanToEntity(ErrorLogBean errorLogBean){
        ErrorLog errorLog = new ErrorLog();
        errorLog.setId(errorLogBean.getId());
        errorLog.setLockId(errorLogBean.getLockId());
        errorLog.setErrorMessage(errorLogBean.getErrorMessage());
        return errorLog;
    }
}

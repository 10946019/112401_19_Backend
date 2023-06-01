package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.AuthorizationBean;
import com.javaguides.arduino.dao.AuthorizationDAO;
import com.javaguides.arduino.entity.Authorization;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorizationService {

    private final AuthorizationDAO authorizationDAO;

    public AuthorizationService(AuthorizationDAO authorizationDAO) {
        this.authorizationDAO = authorizationDAO;
    }

    public void save(AuthorizationBean authorizationBean) {
        Authorization saveResult = authorizationDAO.saveAndFlush(convertBeanToEntity(authorizationBean));
        authorizationDAO.save(saveResult);
    }

    public List<AuthorizationBean> searchAll(){
        return authorizationDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<AuthorizationBean> getById(Integer id) {
        Optional<Authorization> optional = authorizationDAO.findById(id);
        if (optional.isPresent()) {
            Authorization entity = optional.get();
            AuthorizationBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(AuthorizationBean authorizationBean) {
        Optional<Authorization> optional = authorizationDAO.findById(authorizationBean.getId());
        Authorization authorization = optional.get();
        authorization.setUserId(authorizationBean.getUserId());
        authorization.setLockId(authorizationBean.getLockId());
        authorization.setAccessModeId(authorizationBean.getAccessModeId());
        authorizationDAO.update(authorization);
    }

    public void delete(Integer id) {
        authorizationDAO.deleteById(id);
    }

    private AuthorizationBean convertEntityToBean(Authorization authorization){
        AuthorizationBean authorizationBean = new AuthorizationBean();
        authorizationBean.setId(authorization.getId());
        authorizationBean.setUserId(authorization.getUserId());
        authorizationBean.setLockId(authorization.getLockId());
        authorizationBean.setAccessModeId(authorization.getAccessModeId());
        return authorizationBean;
    }

    private Authorization convertBeanToEntity(AuthorizationBean authorizationBean){
        Authorization authorization = new Authorization();
        authorization.setId(authorizationBean.getId());
        authorization.setUserId(authorizationBean.getUserId());
        authorization.setLockId(authorizationBean.getLockId());
        authorization.setAccessModeId(authorizationBean.getAccessModeId());
        return authorization;
    }
}

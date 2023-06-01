package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.AuthorizationBean;
import com.javaguides.arduino.dao.AuthorizationDAO;
import com.javaguides.arduino.dao.UserDAO;
import com.javaguides.arduino.entity.Authorization;
import com.javaguides.arduino.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorizationService {

    private final AuthorizationDAO authorizationDAO;
    private final UserDAO userDAO;

    public AuthorizationService(AuthorizationDAO authorizationDAO, UserDAO userDAO) {
        this.authorizationDAO = authorizationDAO;
        this.userDAO = userDAO;
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

    private AuthorizationBean convertEntityToBean(Authorization authorization){
        AuthorizationBean authorizationBean = new AuthorizationBean();
        authorizationBean.setId(authorization.getId());
        authorizationBean.setUserId(authorization.getUser().getId());
        authorizationBean.setUserName(authorization.getUser().getName());
        authorizationBean.setLockId(authorization.getLock().getId());
        authorizationBean.setLockName(authorization.getLock().getName());
        authorizationBean.setAccessModeId(authorization.getAccessMode().getId());
        authorizationBean.setAccessModeName(authorization.getAccessMode().getName());
        return authorizationBean;
    }


}

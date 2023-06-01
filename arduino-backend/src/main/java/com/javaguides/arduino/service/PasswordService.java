package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.PasswordBean;
import com.javaguides.arduino.bean.UserBean;
import com.javaguides.arduino.dao.PasswordDAO;
import com.javaguides.arduino.dao.UserDAO;
import com.javaguides.arduino.entity.Password;
import com.javaguides.arduino.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasswordService {

    private final PasswordDAO passwordDAO;

    public PasswordService(PasswordDAO passwordDAO) {
        this.passwordDAO = passwordDAO;
    }

    public void save(PasswordBean passwordBean) {
        Password saveResult = passwordDAO.saveAndFlush(convertBeanToEntity(passwordBean));
        passwordDAO.save(saveResult);
    }

    public List<PasswordBean> searchAll(){
        return passwordDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<PasswordBean> getById(Integer id) {
        Optional<Password> optional = passwordDAO.findById(id);
        if (optional.isPresent()) {
            Password entity = optional.get();
            PasswordBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(PasswordBean passwordBean) {
        Optional<Password> optional = passwordDAO.findById(passwordBean.getId());
        Password password = optional.get();
        password.setUserId(passwordBean.getUserId());
        password.setPassword(passwordBean.getPassword());
        passwordDAO.update(password);
    }

    public void delete(Integer id) {
        passwordDAO.deleteById(id);
    }

    private PasswordBean convertEntityToBean(Password password){
        PasswordBean passwordBean = new PasswordBean();
        passwordBean.setId(password.getId());
        passwordBean.setUserId(password.getUserId());
        passwordBean.setPassword(password.getPassword());
        return passwordBean;
    }

    private Password convertBeanToEntity(PasswordBean passwordBean){
        Password password = new Password();
        password.setId(passwordBean.getId());
        password.setUserId(passwordBean.getUserId());
        password.setPassword(passwordBean.getPassword());
        return password;
    }
}

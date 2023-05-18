package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.UserBean;
import com.javaguides.arduino.dao.UserDAO;
import com.javaguides.arduino.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void save(UserBean userBean) {
        User saveResult = userDAO.saveAndFlush(convertBeanToEntity(userBean));
        userDAO.save(saveResult);
    }

    public List<UserBean> searchAll(){
        return userDAO.findAll()
                .stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    public Optional<UserBean> getById(String id) {
        Optional<User> optional = userDAO.findById(id);
        if (optional.isPresent()) {
            User entity = optional.get();
            UserBean bean = convertEntityToBean(entity);
            return Optional.of(bean);
        } else {
            return Optional.empty();
        }
    }

    public void update(UserBean userBean) {
        Optional<User> optional = userDAO.findById(userBean.getAccount());
        User user = optional.get();
        user.setPassword(userBean.getPassword());
        userDAO.update(user);
    }

    public void delete(String id) {
        userDAO.deleteById(id);
    }

    private UserBean convertEntityToBean(User user){
        UserBean userBean = new UserBean();
        userBean.setAccount(user.getAccount());
        userBean.setPassword(user.getPassword());
        return userBean;
    }

    private User convertBeanToEntity(UserBean userBean){
        User user = new User();
        user.setAccount(userBean.getAccount());
        user.setPassword(userBean.getPassword());
        return user;
    }
}

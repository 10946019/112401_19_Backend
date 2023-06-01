package com.javaguides.arduino.service;

import com.javaguides.arduino.bean.UserBean;
import com.javaguides.arduino.dao.UserDAO;
import com.javaguides.arduino.entity.User;
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

    public Optional<UserBean> getById(Integer id) {
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
        Optional<User> optional = userDAO.findById(userBean.getId());
        User user = optional.get();
        user.setName(userBean.getName());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        userDAO.update(user);
    }

    public void delete(Integer id) {
        userDAO.deleteById(id);
    }

    private UserBean convertEntityToBean(User user){
        UserBean userBean = new UserBean();
        userBean.setId(user.getId());
        userBean.setName(user.getName());
        userBean.setEmail(user.getEmail());
        userBean.setPassword(user.getPassword());
        return userBean;
    }

    private User convertBeanToEntity(UserBean userBean){
        User user = new User();
        user.setId(userBean.getId());
        user.setName(userBean.getName());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        return user;
    }
}

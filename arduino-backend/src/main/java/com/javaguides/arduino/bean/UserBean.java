package com.javaguides.arduino.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserBean {
    public Integer id;
    public String name;
    public String email;
    public String password;
}

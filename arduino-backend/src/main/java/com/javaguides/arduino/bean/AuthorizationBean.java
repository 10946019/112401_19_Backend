package com.javaguides.arduino.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaguides.arduino.entity.AccessMode;
import com.javaguides.arduino.entity.DoorLock;
import com.javaguides.arduino.entity.User;
import lombok.Data;

@Data
public class AuthorizationBean {
    public Integer id;
    @JsonIgnore
    public User user;
    public Integer userId;
    public String userName;
    @JsonIgnore
    public DoorLock lock;
    public Integer lockId;
    public String lockName;
    @JsonIgnore
    public AccessMode accessMode;
    @JsonIgnore
    public Integer accessModeId;
    public String accessModeName;
}

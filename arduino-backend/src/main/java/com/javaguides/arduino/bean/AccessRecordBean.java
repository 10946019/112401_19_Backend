package com.javaguides.arduino.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaguides.arduino.entity.DoorLock;
import com.javaguides.arduino.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessRecordBean {
    public Integer id;
    @JsonIgnore
    public User user;
    public Integer userId;
    public String userName;
    @JsonIgnore
    public DoorLock lock;
    public Integer lockId;
    public String lockName;
    public LocalDateTime accessTime;
    public Boolean success;
}

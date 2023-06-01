package com.javaguides.arduino.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessRecordBean {
    public Integer id;
    public Integer userId;
    public Integer lockId;
    public LocalDateTime accessTime;
    public Boolean success;
}

package com.javaguides.arduino.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessLogsBean {
    public Integer id;
    public String userAccount;
    public Integer doorId;
    public LocalDateTime accessTime;
    public Boolean accessResult;
    public String accessMethod;
}

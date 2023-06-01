package com.javaguides.arduino.bean;

import lombok.Data;

@Data
public class AuthorizationBean {
    public Integer id;
    public Integer lockId;
    public Integer userId;
    public Integer accessModeId;
}

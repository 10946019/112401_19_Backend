package com.javaguides.arduino.bean;

import lombok.Data;

@Data
public class ErrorLogBean {
    public Integer id;
    public Integer lockId;
    public String errorMessage;
}

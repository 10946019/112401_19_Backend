package com.javaguides.arduino.bean;

import lombok.Data;

@Data
// 前端資料需要的格式，基本上會跟entity一樣，但是可以因為需求而增加變數
public class DoorLockBean {
    public Integer id;
    public String name;
}

package com.javaguides.arduino.dao;

import com.javaguides.arduino.entity.DoorLock;

//DAO的介面，要繼承BaseDAO<entity, pk型態>
public interface DoorLockDAO extends BaseDAO<DoorLock, Integer> {
}

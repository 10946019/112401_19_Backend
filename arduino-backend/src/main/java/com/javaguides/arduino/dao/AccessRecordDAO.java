package com.javaguides.arduino.dao;

import com.javaguides.arduino.entity.AccessRecord;
import com.javaguides.arduino.entity.DoorLock;
import com.javaguides.arduino.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccessRecordDAO extends BaseDAO<AccessRecord, Integer> {
    @Query("FROM AccessRecord d where d.user =:userId")
    List<AccessRecord> getByUserId(@Param("userId") User userId);

    @Query("FROM AccessRecord d where d.lock =:lockId")
    List<AccessRecord> getByLockId(@Param("lockId") DoorLock lockId);

    @Query("FROM AccessRecord d where d.user =:userId AND d.lock =:lockId")
    List<AccessRecord> getByUserIdAndLockId(@Param("userId") User userId,@Param("lockId") DoorLock lockId);

}

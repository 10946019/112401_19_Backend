package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.AccessRecordBean;
import com.javaguides.arduino.entity.DoorLock;
import com.javaguides.arduino.entity.User;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.AccessRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/accessRecord")
public class AccessRecordController {

    private final AccessRecordService accessRecordService;

    @GetMapping(path = "")
    public ResponseEntity<List<AccessRecordBean>> searchAll() {
        return ResponseEntity.ok(accessRecordService.searchAll());
    }

    @GetMapping(path = "/param")
    public ResponseEntity<List<AccessRecordBean>> searchByParam(
            @RequestParam(required = false,name = "userId") User userId,
            @RequestParam(required = false,name = "userName") String userName,
            @RequestParam(required = false,name = "lockId") DoorLock lockId,
            @RequestParam(required = false,name = "lockName") String lockName
    ) {
        return ResponseEntity.ok(accessRecordService.searchByParam(userId,userName,lockId,lockName));
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<AccessRecordBean> getAccessRecord(@RequestParam(name = "id") Integer id) {
        AccessRecordBean accessRecordBean = accessRecordService.getById(id).orElseThrow(() -> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(accessRecordBean);
    }

}

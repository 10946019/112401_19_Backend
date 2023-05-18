package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.AccessLogsBean;
import com.javaguides.arduino.bean.DoorBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.AccessLogsService;
import com.javaguides.arduino.service.DoorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/accessLogs")
public class AccessLogsController {

    private final AccessLogsService accessLogsService;

    @GetMapping(path = "")
    public ResponseEntity<List<AccessLogsBean>> searchAll(){
        return ResponseEntity.ok(accessLogsService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<AccessLogsBean> getAccessLogs(@RequestParam(name = "id") Integer id){
        AccessLogsBean accessLogsBean = accessLogsService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(accessLogsBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<AccessLogsBean> createAccessLogs(@RequestBody AccessLogsBean accessLogsBean){
        accessLogsService.save(accessLogsBean);
        return ResponseEntity.ok(accessLogsBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<AccessLogsBean> updateAccessLogs(@RequestBody AccessLogsBean accessLogsBean){
        accessLogsService.update(accessLogsBean);
        return ResponseEntity.ok(accessLogsBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteAccessLogs(@RequestParam(name = "id") Integer id){
        accessLogsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.AccessRecordBean;
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
    public ResponseEntity<List<AccessRecordBean>> searchAll(){
        return ResponseEntity.ok(accessRecordService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<AccessRecordBean> getAccessRecord(@RequestParam(name = "id") Integer id){
        AccessRecordBean accessRecordBean = accessRecordService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(accessRecordBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<AccessRecordBean> createAccessRecord(@RequestBody AccessRecordBean accessRecordBean){
        accessRecordService.save(accessRecordBean);
        return ResponseEntity.ok(accessRecordBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<AccessRecordBean> updateAccessRecord(@RequestBody AccessRecordBean accessRecordBean){
        accessRecordService.update(accessRecordBean);
        return ResponseEntity.ok(accessRecordBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteAccessRecord(@RequestParam(name = "id") Integer id){
        accessRecordService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.AccessModeBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.AccessModeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/accessMode")
public class AccessModeController {

    private final AccessModeService accessModeService;

    @GetMapping(path = "")
    public ResponseEntity<List<AccessModeBean>> searchAll(){
        return ResponseEntity.ok(accessModeService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<AccessModeBean> getAccessMode(@RequestParam(name = "id") Integer id){
        AccessModeBean accessModeBean = accessModeService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(accessModeBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<AccessModeBean> createAccessMode(@RequestBody AccessModeBean accessModeBean){
        accessModeService.save(accessModeBean);
        return ResponseEntity.ok(accessModeBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<AccessModeBean> updateAccessMode(@RequestBody AccessModeBean accessModeBean){
        accessModeService.update(accessModeBean);
        return ResponseEntity.ok(accessModeBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteAccessMode(@RequestParam(name = "id") Integer id){
        accessModeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

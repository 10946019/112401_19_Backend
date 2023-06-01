package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.PasswordBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/password")
public class PasswordController {

    private final PasswordService passwordService;

    @GetMapping(path = "")
    public ResponseEntity<List<PasswordBean>> searchAll(){
        return ResponseEntity.ok(passwordService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<PasswordBean> getPassword(@RequestParam(name = "id") Integer id){
        PasswordBean PasswordBean = passwordService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(PasswordBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<Map<String,String>>  createPassword(@RequestBody PasswordBean PasswordBean){
        passwordService.save(PasswordBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "新增成功"));
    }

    @PatchMapping(path = "")
    public ResponseEntity<Map<String,String>>  updatePassword(@RequestBody PasswordBean PasswordBean){
        passwordService.update(PasswordBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "修改成功"));
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<Map<String,String>>  deletePassword(@RequestParam(name = "id") Integer id){
        passwordService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("result", "刪除成功"));
    }
}

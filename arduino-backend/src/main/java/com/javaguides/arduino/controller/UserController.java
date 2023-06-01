package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.UserBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "")
    public ResponseEntity<List<UserBean>> searchAll(){
        return ResponseEntity.ok(userService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<UserBean> getUser(@RequestParam(name = "id") Integer id){
        UserBean userBean = userService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此帳號不存在"));
        return ResponseEntity.ok(userBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<UserBean> createUser(@RequestBody UserBean userBean){
        userService.save(userBean);
        return ResponseEntity.ok(userBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<UserBean> updateUser(@RequestBody UserBean userBean){
        userService.update(userBean);
        return ResponseEntity.ok(userBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(name = "id") Integer id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

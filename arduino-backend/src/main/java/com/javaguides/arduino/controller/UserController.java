package com.javaguides.arduino.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaguides.arduino.bean.UserBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/login")
    public ResponseEntity<UserBean> login(@RequestBody UserBean userBean) {
        UserBean userLoginBean = userService.login(userBean.getId(), userBean.getPassword()).orElseThrow(() -> new ResourceNotFoundException("登入失敗"));
        return ResponseEntity.ok(userLoginBean);
    }


    @PostMapping(path = "")
    public ResponseEntity<Map<String,String>> createUser(@RequestBody UserBean userBean) {
        userService.save(userBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "新增成功"));
    }

    @PatchMapping(path = "")
    public ResponseEntity<Map<String,String>> updateUser(@RequestBody UserBean userBean) {
        userService.update(userBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "修改成功"));
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<Map<String,String>> deleteUser(@RequestParam(name = "id") Integer id) {
        userService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("result", "刪除成功"));
    }
}

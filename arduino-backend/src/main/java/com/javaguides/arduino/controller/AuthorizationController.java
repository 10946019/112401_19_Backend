package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.AuthorizationBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @GetMapping(path = "")
    public ResponseEntity<List<AuthorizationBean>> searchAll(){
        return ResponseEntity.ok(authorizationService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<AuthorizationBean> getAuthorization(@RequestParam(name = "id") Integer id){
        AuthorizationBean authorizationBean = authorizationService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(authorizationBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<AuthorizationBean> createAuthorization(@RequestBody AuthorizationBean authorizationBean){
        authorizationService.save(authorizationBean);
        return ResponseEntity.ok(authorizationBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<AuthorizationBean> updateAuthorization(@RequestBody AuthorizationBean authorizationBean){
        authorizationService.update(authorizationBean);
        return ResponseEntity.ok(authorizationBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteAuthorization(@RequestParam(name = "id") Integer id){
        authorizationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

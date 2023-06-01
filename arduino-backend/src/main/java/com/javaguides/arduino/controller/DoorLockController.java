package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.LockBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.LockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/lock")
public class LockController {

    private final LockService lockService;

    @GetMapping(path = "")
    public ResponseEntity<List<LockBean>> searchAll(){
        return ResponseEntity.ok(lockService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<LockBean> getLock(@RequestParam(name = "id") Integer id){
        LockBean lockBean = lockService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(lockBean);
    }

    @GetMapping(path = "", params = {"name"})
    public ResponseEntity<List<LockBean>> getLock(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(lockService.getByNameKeyword(name));
    }

    @PostMapping(path = "")
    public ResponseEntity<LockBean> createLock(@RequestBody LockBean lockBean){
        lockService.save(lockBean);
        return ResponseEntity.ok(lockBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<LockBean> updateLock(@RequestBody LockBean lockBean){
        lockService.update(lockBean);
        return ResponseEntity.ok(lockBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteLock(@RequestParam(name = "id") Integer id){
        lockService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

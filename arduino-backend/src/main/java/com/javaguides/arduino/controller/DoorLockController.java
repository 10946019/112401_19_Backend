package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.DoorLockBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.DoorLockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/doorLock")
public class DoorLockController {

    private final DoorLockService doorLockService;

    @GetMapping(path = "")
    public ResponseEntity<List<DoorLockBean>> searchAll(){
        return ResponseEntity.ok(doorLockService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<DoorLockBean> getLock(@RequestParam(name = "id") Integer id){
        DoorLockBean doorLockBean = doorLockService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(doorLockBean);
    }

    @GetMapping(path = "", params = {"name"})
    public ResponseEntity<List<DoorLockBean>> getLock(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(doorLockService.getByNameKeyword(name));
    }

    @PostMapping(path = "")
    public ResponseEntity<DoorLockBean> createLock(@RequestBody DoorLockBean doorLockBean){
        doorLockService.save(doorLockBean);
        return ResponseEntity.ok(doorLockBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<DoorLockBean> updateLock(@RequestBody DoorLockBean doorLockBean){
        doorLockService.update(doorLockBean);
        return ResponseEntity.ok(doorLockBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteLock(@RequestParam(name = "id") Integer id){
        doorLockService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

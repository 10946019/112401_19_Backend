package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.DoorBean;
import com.javaguides.arduino.bean.UserBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.DoorService;
import com.javaguides.arduino.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/door")
public class DoorController {

    private final DoorService doorService;

    @GetMapping(path = "")
    public ResponseEntity<List<DoorBean>> searchAll(){
        return ResponseEntity.ok(doorService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<DoorBean> getDoor(@RequestParam(name = "id") Integer id){
        DoorBean doorBean = doorService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(doorBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<DoorBean> createDoor(@RequestBody DoorBean doorBean){
        doorService.save(doorBean);
        return ResponseEntity.ok(doorBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<DoorBean> updateDoor(@RequestBody DoorBean doorBean){
        doorService.update(doorBean);
        return ResponseEntity.ok(doorBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteDoor(@RequestParam(name = "id") Integer id){
        doorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

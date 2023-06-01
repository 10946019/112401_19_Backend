package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.ErrorLogBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.ErrorLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/errorLog")
public class ErrorLogController {

    private final ErrorLogService errorLogService;

    @GetMapping(path = "")
    public ResponseEntity<List<ErrorLogBean>> searchAll(){
        return ResponseEntity.ok(errorLogService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<ErrorLogBean> getErrorLog(@RequestParam(name = "id") Integer id){
        ErrorLogBean errorLogBean = errorLogService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(errorLogBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<ErrorLogBean> createErrorLog(@RequestBody ErrorLogBean errorLogBean){
        errorLogService.save(errorLogBean);
        return ResponseEntity.ok(errorLogBean);
    }

    @PatchMapping(path = "")
    public ResponseEntity<ErrorLogBean> updateErrorLog(@RequestBody ErrorLogBean errorLogBean){
        errorLogService.update(errorLogBean);
        return ResponseEntity.ok(errorLogBean);
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<HttpStatus> deleteErrorLog(@RequestParam(name = "id") Integer id){
        errorLogService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

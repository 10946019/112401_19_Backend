package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.ErrorLogBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.ErrorLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,String>> createErrorLog(@RequestBody ErrorLogBean errorLogBean){
        errorLogService.save(errorLogBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "新增成功"));
    }

    @PatchMapping(path = "")
    public ResponseEntity<Map<String,String>> updateErrorLog(@RequestBody ErrorLogBean errorLogBean){
        errorLogService.update(errorLogBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "修改成功"));
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<Map<String,String>> deleteErrorLog(@RequestParam(name = "id") Integer id){
        errorLogService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("result", "刪除成功"));
    }
}

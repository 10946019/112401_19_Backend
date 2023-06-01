package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.AccessModeBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.AccessModeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/accessMode")
public class AccessModeController {

    private final AccessModeService accessModeService;

    @GetMapping(path = "")
    public ResponseEntity<List<AccessModeBean>> searchAll() {
        return ResponseEntity.ok(accessModeService.searchAll());
    }

    @GetMapping(path = "", params = {"id"})
    public ResponseEntity<AccessModeBean> getAccessMode(@RequestParam(name = "id") Integer id) {
        AccessModeBean accessModeBean = accessModeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(accessModeBean);
    }

    @PostMapping(path = "")
    public ResponseEntity<Map<String, String>> createAccessMode(@RequestBody AccessModeBean accessModeBean) {
        accessModeService.save(accessModeBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "新增成功"));
    }

    @PatchMapping(path = "")
    public ResponseEntity<Map<String, String>> updateAccessMode(@RequestBody AccessModeBean accessModeBean) {
        accessModeService.update(accessModeBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "修改成功"));
    }

    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<Map<String, String>> deleteAccessMode(@RequestParam(name = "id") Integer id) {
        accessModeService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("result", "刪除成功"));
    }
}

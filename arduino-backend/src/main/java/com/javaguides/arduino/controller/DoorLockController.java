package com.javaguides.arduino.controller;

import com.javaguides.arduino.bean.DoorLockBean;
import com.javaguides.arduino.exception.ResourceNotFoundException;
import com.javaguides.arduino.service.DoorLockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// 建檔順序: entity => bean => DAO => service => controller
// 駝峰式命名

// 建立所有參數的建構元
@AllArgsConstructor
// 告訴spring這是一個controller
@RestController
// 定義url，url以/doorLock開頭的都會進到這裡
@RequestMapping("/doorLock")
public class DoorLockController {

    // 建構controller會用到要使用的service
    private final DoorLockService doorLockService;

    // 查詢使用get，url為/doorLock的會進入這裡
    // http://localhost:8080/doorLock
    @GetMapping(path = "")
    // 回傳多筆用search、單筆get(看團隊習慣)
    public ResponseEntity<List<DoorLockBean>> searchAll(){
        return ResponseEntity.ok(doorLockService.searchAll());
    }

    // url如果一樣，但是參數不一樣可以這樣寫，增加進入條件
    // params: url參數(因為遇到url一樣才要寫，平常寫@RequestPram就可以了)
    // http://localhost:8080/doorLock?id=XXX
    @GetMapping(path = "", params = {"id"})
    // @RequestParam，/url&id=XXX，&後面為請求參數
    public ResponseEntity<DoorLockBean> getLock(@RequestParam(name = "id") Integer id){
        DoorLockBean doorLockBean = doorLockService.getById(id).orElseThrow(()-> new ResourceNotFoundException("此id不存在"));
        return ResponseEntity.ok(doorLockBean);
    }

    // http://localhost:8080/doorLock?name=XXX
    @GetMapping(path = "", params = {"name"})
    public ResponseEntity<List<DoorLockBean>> getLock(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(doorLockService.getByNameKeyword(name));
    }

    //不同的請求方式可以使用同個url
    // http://localhost:8080/doorLock
    /* 附帶 Body
        {
            "name":"XXX"
        }
     */
    @PostMapping(path = "")
      /*
        @RequestBody = 調整取資料的位置(像是get在url上)，要用json的格式(基本資料型態不行)
        @RequestBody拿掉指用formData來傳送資料(檔案上傳會用)
     */
    public ResponseEntity<Map<String,String>> createLock(@RequestBody DoorLockBean doorLockBean){
        doorLockService.save(doorLockBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "新增成功"));
    }

    // http://localhost:8080/doorLock
    /* 附帶 Body，
        {
            "id":"XXX",
            "name":"XXX"
        }
    @PostMapping的Body不需要寫id，
    因為在DoorLock的entity有@GeneratedValue(strategy = GenerationType.IDENTITY)，
    會自動產生流水號。
    @PatchMapping要看使用者想修改哪筆資料所以需要id(PK)
     */
    @PatchMapping(path = "")
    public ResponseEntity<Map<String,String>> updateLock(@RequestBody DoorLockBean doorLockBean){
        doorLockService.update(doorLockBean);
        return ResponseEntity.ok(Collections.singletonMap("result", "修改成功"));
    }

    /* url動態帶編號(不是&參數)
    // http://localhost:8080/doorLock/{id}
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> deleteLock(@PathVariable Integer id){
        doorLockService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("result", "刪除成功"));
    }
    */
    // http://localhost:8080/doorLock?id=XXX
    @DeleteMapping(path = "", params = {"id"})
    public ResponseEntity<Map<String,String>> deleteLock(@RequestParam(name = "id") Integer id){
        doorLockService.delete(id);
        return ResponseEntity.ok(Collections.singletonMap("result", "刪除成功"));
    }
}

package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
// 對應資料表的名稱
@Table(name = "doorlock")
public class DoorLock {
    /**
     * 鎖id
     *
     * @Since 1.0.1
     */
    // PK
    @Id
    // PK自動生成，類似流水號
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 對應欄位資料
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 鎖名稱
     *
     * @Since 1.0.1
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;


}

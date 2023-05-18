package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "access_logs")
public class AccessLogs {
    /**
     * 開鎖紀錄id
     *
     * @Since 1.0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    /**
     * 開鎖的使用者
     *
     * @Since 1.0.1
     */
    @Column(name = "user_account", length = 45,nullable = false)
    private String userAccount;

    /**
     * 開哪一個門
     *
     * @Since 1.0.1
     */
    @Column(name = "door_id")
    private Integer doorId;

    /**
     * 開鎖時間
     *
     * @Since 1.0.1
     */
    @Column(name = "access_time", nullable = false)
    private LocalDateTime accessTime;

    /**
     * 開鎖結果(成功/失敗)
     *
     * @Since 1.0.1
     */
    @Column(name = "access_result", nullable = false)
    private Boolean accessResult;

    /**
     * 開鎖方式(0:密碼/1:指紋/2:影像/3:聲音)
     *
     * @Since 1.0.1
     */
    @Column(name = "access_method", length = 3, nullable = false)
    private String accessMethod;

}

package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorization")
public class Authorization {
    /**
     * id
     *
     * @Since 1.0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 使用者id
     *
     * @Since 1.0.1
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 鎖id
     *
     * @Since 1.0.1
     */
    @Column(name = "lock_id")
    private Integer lockId;

    /**
     * 訪問方式id
     *
     * @Since 1.0.1
     */
    @Column(name = "access_mode_id")
    private Integer accessModeId;
}

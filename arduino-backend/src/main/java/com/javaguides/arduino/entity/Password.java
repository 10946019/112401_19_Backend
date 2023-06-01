package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "password")
public class Password {
    /**
     * 密碼id
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
     * 密碼
     *
     * @Since 1.0.1
     */
    @Column(name = "password", length = 50, nullable = false)
    private String password;
}

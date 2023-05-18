package com.javaguides.arduino.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    /**
     * 使用者帳號
     *
     * @Since 1.0.1
     */
    @Id
    @Column(name = "account", length = 45,nullable = false)
    private String account;

    /**
     * 使用者密碼
     *
     * @Since 1.0.1
     */
    @Column(name = "password", length = 45,nullable = false)
    private String password;
}

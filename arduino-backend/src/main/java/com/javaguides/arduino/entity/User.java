package com.javaguides.arduino.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    /**
     * 使用者id
     *
     * @Since 1.0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 使用者名稱
     *
     * @Since 1.0.1
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 使用者email
     *
     * @Since 1.0.1
     */
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    /**
     * 使用者密碼
     *
     * @Since 1.0.1
     */
    @Column(name = "password", length = 50, nullable = false)
    private String password;

}

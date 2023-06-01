package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "errorlog")
public class ErrorLog {
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
     * 鎖id
     *
     * @Since 1.0.1
     */
    @Column(name = "lock_id")
    private Integer lockId;

    /**
     * 鎖id
     *
     * @Since 1.0.1
     */
    @Lob
    @Column(name = "name", columnDefinition = "text")
    private String errorMessage;
}

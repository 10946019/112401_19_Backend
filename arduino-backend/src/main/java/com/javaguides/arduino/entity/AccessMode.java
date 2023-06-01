package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accessmode")
public class AccessMode {
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
     * 訪問方式名稱
     *
     * @Since 1.0.1
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

}

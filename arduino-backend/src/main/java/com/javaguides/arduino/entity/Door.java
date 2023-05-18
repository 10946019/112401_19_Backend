package com.javaguides.arduino.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "door")
public class Door {
    /**
     * 門的id
     *
     * @Since 1.0.1
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    /**
     * 門的名稱
     *
     * @Since 1.0.1
     */
    @Column(name = "name", length = 45,nullable = false)
    private String name;

    /**
     * 門的位置
     *
     * @Since 1.0.1
     */
    @Column(name = "location", length = 100,nullable = false)
    private String location;

    /**
     * 詳細描述
     *
     * @Since 1.0.1
     */
    @Column(name = "description", length = 200)
    private String description;
}

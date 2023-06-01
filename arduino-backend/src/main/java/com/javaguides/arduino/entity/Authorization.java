package com.javaguides.arduino.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    /**
     * 鎖id
     *
     * @Since 1.0.1
     */
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="lock_id", referencedColumnName="id")
    private DoorLock lock;

    /**
     * 訪問方式id
     *
     * @Since 1.0.1
     */
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="access_mode_id", referencedColumnName="id")
    private AccessMode accessMode;
}

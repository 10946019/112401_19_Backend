package com.javaguides.arduino.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accessrecord")
public class AccessRecord {
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
    @Column(name = "success", nullable = false)
    private Boolean success;

}

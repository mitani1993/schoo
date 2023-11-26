package com.example.demo3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * participantテーブルのEntityクラスです
 */
@Data
@Entity
@Table(name = "Participant")
public class Participant {
    // 参加者ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Integer participantId;
    // 参加者名
    @Column(name = "participant_name")
    private String participantName;
}

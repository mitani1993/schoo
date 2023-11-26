package com.example.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.entity.Participant;

/**
 * participantテーブルを操作するためのRepositoryクラスです
 */
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

}

package com.example.demo3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.entity.Event;

/**
 * eventテーブルを操作するためのRepositoryクラスです
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
    /**
     * 指定したイベントIDからイベントの情報を検索します.
     *
     * @param eventId イベントID
     * @return イベント一覧
     */
    public List<Event> findByEventId(Integer eventId);
}

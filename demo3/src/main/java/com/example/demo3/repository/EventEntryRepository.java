package com.example.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.entity.EventEntry;
import java.util.List;

/**
 * event_entryテーブルを操作するためのRepositoryクラスです
 */
public interface EventEntryRepository extends JpaRepository<EventEntry, Integer> {
    /**
     * 指定したイベントIDからイベント参加者の情報を検索します.
     *
     * @param eventId イベントID
     * @return イベント参加者一覧
     */
    public List<EventEntry> findByEventId(Integer eventId);
}

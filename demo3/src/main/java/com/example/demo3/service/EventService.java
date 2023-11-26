package com.example.demo3.service;

import java.util.List;

import com.example.demo3.entity.Event;
import com.example.demo3.form.EventForm;

/**
 * イベントのServiceインターフェースです.
 */
public interface EventService {
    /**
     * DBに登録されている全てのイベント情報を検索します.
     *
     * @return イベント一覧
     */
    public List<Event> findAll();

    /**
     * 指定されたイベントIDからDBに登録されているイベント情報を検索します.
     *
     * @return イベント一覧
     */
    public List<Event> findByEventId(Integer eventId);

    /**
     * イベント情報をDBに登録します.
     */
    public void save(EventForm eventForm);
}

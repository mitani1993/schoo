package com.example.demo3.form;

import lombok.Data;

/**
 * イベント情報のFormクラスです
 */
@Data
public class EventForm {
    // イベント名
    private String eventName;
    // イベント開催日
    private String eventDate;
    // イベント開催場所
    private String eventVenue;
}

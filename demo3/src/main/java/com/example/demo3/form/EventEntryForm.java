package com.example.demo3.form;

import lombok.Data;

/**
 * イベント参加情報のFormクラスです
 */
@Data
public class EventEntryForm {
    // イベントID
    private Integer eventId;
    // イベント参加者名
    private String participantName;
}

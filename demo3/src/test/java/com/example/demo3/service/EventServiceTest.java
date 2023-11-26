package com.example.demo3.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo3.entity.Event;
import com.example.demo3.form.EventForm;

/**
 * EventServiceクラスのテストクラスです.
 */
@SpringBootTest
@Transactional
public class EventServiceTest {
    // テスト対象
    @Autowired
    EventService eventService;

    // テスト1:DBに登録された2件のデータが取得できること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータ2件を登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')"
    })
    public void test001() {
        List<Event> eventList = eventService.findAll();
        assertThat(eventList.size(), is(2));
    }

    // テスト2:DBに登録されたデータの中から指定したIDのイベント情報が取得できること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータ2件を登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')"
    })
    public void test002() {
        List<Event> event = eventService.findByEventId(1);
        assertThat(event.size(), is(1));
    }

    // テスト3:DBに登録されたデータに存在しないIDで検索できないこと
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータ2件を登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')"
    })
    public void test003() {
        List<Event> event = eventService.findByEventId(3);
        assertThat(event.isEmpty(), is(true));
    }

    // テスト4:DBにイベントの情報を登録
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
    })
    public void test004() {
        // テストデータの準備
        Integer eventId = 1;
        String eventName = "イベント1";
        String eventVenue = "渋谷";
        String eventDate = "2021-04-01";
        // テストデータをFormにセット
        EventForm eventForm = new EventForm();
        eventForm.setEventName(eventName);
        eventForm.setEventVenue(eventVenue);
        eventForm.setEventDate(eventDate);
        // Formの情報をDBに登録
        eventService.save(eventForm);

        // DBに登録された情報を取得
        List<Event> event = eventService.findByEventId(eventId);
        Event eventData = event.get(0);

        // 値の検証
        assertThat(eventData.getEventId(), is(eventId));
        assertThat(eventData.getEventName(), is(eventName));
        assertThat(eventData.getEventDate(), is(Date.valueOf(eventDate)));
        assertThat(eventData.getEventVenue(), is(eventVenue));
    }
}

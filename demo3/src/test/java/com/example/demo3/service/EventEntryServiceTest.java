package com.example.demo3.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo3.entity.Participant;
import com.example.demo3.form.EventEntryForm;

/**
 * EventEntryServiceクラスのテストクラスです.
 */
@SpringBootTest
@Transactional
public class EventEntryServiceTest {
    // 単体テスト対象
    @Autowired
    EventEntryService eventEntryService;

    // 単体テスト結果検証用
    @Autowired
    EventService eventService;

    // テスト1:DBに登録された2件のデータが取得できること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータを登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')",
            "DELETE FROM participant",
            "ALTER TABLE participant auto_increment = 1",
            "INSERT INTO participant(participant_name) VALUES ('スクー太郎')",
            "INSERT INTO participant(participant_name) VALUES ('スクー次郎')",
            "DELETE FROM event_entry",
            "ALTER TABLE event_entry auto_increment = 1",
            "INSERT INTO event_entry(event_id, participant_id) VALUES (1, 1)",
            "INSERT INTO event_entry(event_id, participant_id) VALUES (1, 2)"
    })
    public void test001() {
        List<Participant> eventList = eventEntryService.findByEventId(1);
        assertThat(eventList.size(), is(2));
    }

    // テスト2:DBに存在しないイベントIDで検索し、結果が0件であること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータを登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')",
            "DELETE FROM participant",
            "ALTER TABLE participant auto_increment = 1",
            "INSERT INTO participant(participant_name) VALUES ('スクー太郎')",
            "INSERT INTO participant(participant_name) VALUES ('スクー次郎')",
            "DELETE FROM event_entry",
            "ALTER TABLE event_entry auto_increment = 1",
            "INSERT INTO event_entry(event_id, participant_id) VALUES (1, 1)",
            "INSERT INTO event_entry(event_id, participant_id) VALUES (1, 2)"
    })
    public void test002() {
        List<Participant> eventList = eventEntryService.findByEventId(3);
        assertThat(eventList.size(), is(0));
    }

    // テスト3:DBにイベントの情報を登録
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')",
            "DELETE FROM participant",
            "ALTER TABLE participant auto_increment = 1",
            "DELETE FROM event_entry",
            "ALTER TABLE event_entry auto_increment = 1"
    })
    public void test003() {
        // テストデータの準備
        Integer eventId = 1;
        Integer participantId = 1;
        String participantName = "スクー太郎";

        // テストデータをFormにセット
        EventEntryForm eventEntryForm = new EventEntryForm();
        eventEntryForm.setEventId(eventId);
        eventEntryForm.setParticipantName(participantName);

        // Formの情報をDBに登録
        eventEntryService.save(eventEntryForm);

        // DBに登録された情報を取得
        List<Participant> participantList = eventEntryService.findByEventId(1);
        Participant participant = participantList.get(0);

        // 値の検証
        assertThat(participant.getParticipantId(), is(participantId));
        assertThat(participant.getParticipantName(), is(participantName));
    }
}

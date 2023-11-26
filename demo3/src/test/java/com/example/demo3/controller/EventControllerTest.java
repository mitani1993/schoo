package com.example.demo3.controller;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo3.entity.Event;
import com.example.demo3.entity.Participant;
import com.example.demo3.form.EventEntryForm;
import com.example.demo3.form.EventForm;
import com.example.demo3.service.EventEntryService;
import com.example.demo3.service.EventService;

@SpringBootTest
public class EventControllerTest {
    // 単体テスト用モッククラス
    private MockMvc mockMvc;

    // 単体テスト対象クラス
    @Autowired
    private EventController eventController;

    // 単体テスト結果検証用
    // ControllerクラスのテストはServiceクラスが完成していることを前提としています
    @Autowired
    private EventService eventService;
    @Autowired
    private EventEntryService eventEntryService;

    /**
     * 単体テストクラスの事前準備
     */
    @BeforeEach
    public void setup() {
        // 単体テスト対象クラスのモックを作成する
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    // テスト1:トップページの表示ができること
    @Test
    public void test001() throws Exception {
        mockMvc.perform(get("/")) // リクエストを送信
                .andExpect(status().isOk()) // HTTPステータスが200であること
                .andExpect(view().name("eventlist")); // 次に表示する画面がeventlistであること
    }

    // テスト2:DBに登録された2件のデータが取得できること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータを登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')"
    })
    public void test002() throws Exception {
        Map<String, Object> model = mockMvc.perform(get("/")) // リクエストを送信
                .andReturn() // レスポンスを取得
                .getModelAndView() // レスポンスにセットされた値を取得
                .getModel(); // Modelにセットされた値はModelAndViewから取得

        // Modelから値を取得
        // @SuppressWarningsは型変換の警告を表示しない設定
        // 今回は処理に影響はないため無視する設定にしています
        @SuppressWarnings("unchecked")
        List<Event> eventList = (List<Event>) model.get("eventList");

        // コントローラーで値がセットされていることを確認
        assertThat(eventList.size(), is(2));
    }

    // テスト3:イベント登録画面が表示できること
    @Test
    public void test003() throws Exception {
        mockMvc.perform(get("/regist/")) // リクエストを送信
                .andExpect(status().isOk()) // HTTPステータスが200であること
                .andExpect(view().name("regist")); // 次に表示する画面がregistであること
    }

    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')"
    })
    public void test004() throws Exception {
        mockMvc.perform(get("/entry/1/")) // リクエストを送信
                .andExpect(status().isOk()) // HTTPステータスが200であること
                .andExpect(view().name("eventdetail")); // 次に表示する画面がeventdetailであること
    }

    // テスト5:DBにイベントが登録できること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1"
    })
    public void test005() throws Exception {
        // テストデータの準備
        String eventName = "テスト用イベント";
        String eventDate = "2021-04-01";
        String eventVenue = "渋谷";

        EventForm eventForm = new EventForm();
        eventForm.setEventName(eventName);
        eventForm.setEventDate(eventDate);
        eventForm.setEventVenue(eventVenue);

        mockMvc.perform(post("/eventregist/") // リクエストを送信
                .flashAttr("eventForm", eventForm)) // リクエストパラメータをセット
                .andExpect(status().is3xxRedirection()); // HTTPステータスが302（リダイレクト）であること

        // DBにイベントが登録されているかを確認
        List<Event> eventList = eventService.findByEventId(1);
        Event event = eventList.get(0);

        assertThat(event.getEventId(), is(1));
        assertThat(event.getEventName(), is(eventName));
        assertThat(event.getEventDate(), is(Date.valueOf(eventDate)));
        assertThat(event.getEventVenue(), is(eventVenue));
    }

    // テスト6:登録されたイベントへ参加処理ができること
    // テストデータの準備
    // 他のテストへの影響が無いようにデータ全消去→IDリセット→テストデータを登録
    @Test
    @Sql(statements = {
            "DELETE FROM event",
            "ALTER TABLE event auto_increment = 1",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント1', '2021-04-01', '渋谷')",
            "INSERT INTO event(event_name, event_date, event_venue) VALUES ('イベント2', '2021-04-01', '池袋')"
    })
    public void test006() throws Exception {
        // テストデータの準備
        Integer eventId = 1;
        String participantName = "スクー太郎";

        EventEntryForm eventEntryForm = new EventEntryForm();
        eventEntryForm.setEventId(eventId);
        eventEntryForm.setParticipantName(participantName);

        mockMvc.perform(post("/entry/add/") // リクエストを送信
                .flashAttr("eventEntryForm", eventEntryForm)) // リクエストパラメータをセット
                .andExpect(status().is3xxRedirection()); // HTTPステータスが302（リダイレクト）であること

        // DBに参加者が登録されているかを確認
        List<Participant> participantList = eventEntryService.findByEventId(eventId);
        Participant participant = participantList.get(0);

        assertThat(participant.getParticipantId(), is(1));
        assertThat(participant.getParticipantName(), is(participantName));
    }
}

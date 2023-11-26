package com.example.demo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo3.entity.Event;
import com.example.demo3.entity.Participant;
import com.example.demo3.form.EventEntryForm;
import com.example.demo3.form.EventForm;
import com.example.demo3.service.EventEntryService;
import com.example.demo3.service.EventService;

/**
 * イベントコントローラクラスです.
 */
@Controller
public class EventController {
    @Autowired
    EventService eventService;
    @Autowired
    EventEntryService eventEntryService;

    /**
     * トップページ（イベント一覧画面）を表示します.
     *
     * @param model
     * @return イベント一覧画面のパス
     */
    @GetMapping("/")
    public String index(Model model) {
        // DBに登録されているイベントの一覧を取得
        List<Event> eventList = eventService.findAll();
        // modelにイベントの一覧をセット
        model.addAttribute("eventList", eventList);
        // 次に表示する画面のパス（htmlファイルの名称）を返却
        return "eventlist";
    }

    /**
     * イベントの登録画面を表示します.
     *
     * @param model
     * @return イベント登録画面のパス
     */
    @GetMapping("regist")
    public String regist(Model model) {
        // 次に表示する画面のパス（htmlファイルの名称）を返却
        return "regist";
    }

    /**
     * 指定されたイベントIDの詳細画面を表示します.
     *
     * @param eventId イベントID
     * @param model
     * @return イベント詳細画面のパス
     */
    @GetMapping("entry/{eventId}")
    public String entry(@PathVariable("eventId") Integer eventId,
            Model model) {
        // 引数で受け取ったイベントIDからイベントの情報を取得
        // イベントの情報は1件の前提で処理
        List<Event> event = eventService.findByEventId(eventId);
        // modelに結果をセット
        model.addAttribute("event", event.get(0));
        // 引数で受け取ったイベントIDからイベントの参加者情報を取得
        List<Participant> participantList = eventEntryService.findByEventId(eventId);
        // modelに結果をセット
        model.addAttribute("participantList", participantList);
        // 次に表示する画面のパス（htmlファイルの名称）を返却
        return "eventdetail";
    }

    /**
     * イベントの登録処理です.
     *
     * @param eventForm イベント情報
     * @return イベント一覧画面のパス（output.htmlへのリダイレクト）
     */
    @PostMapping("eventregist")
    public String registrationEvent(@ModelAttribute EventForm eventForm) {
        // フォームから値を取得してデータベース登録処理へ
        eventService.save(eventForm);
        // 次に表示する画面のパス（リダイレクト先のページ）を返却
        return "redirect:/";
    }

    /**
     * イベントの参加処理です.
     *
     * @param eventEntryForm イベント参加者情報
     * @return イベント詳細画面のパス
     */
    @PostMapping("entry/add")
    public String entryEvent(
            @ModelAttribute EventEntryForm eventEntryForm) {
        // フォームから値を取得してデータベース登録処理へ
        eventEntryService.save(eventEntryForm);
        // 次に表示する画面のパス（リダイレクト先のページ）を返却
        return "redirect:/entry/" + eventEntryForm.getEventId();
    }
}

package com.example.demo3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo3.entity.EventEntry;
import com.example.demo3.entity.Participant;
import com.example.demo3.form.EventEntryForm;
import com.example.demo3.repository.EventEntryRepository;
import com.example.demo3.repository.ParticipantRepository;

/**
 * イベント参加者のService実装クラスです.
 */
@Service
public class EventEntryServiceImpl implements EventEntryService {
    @Autowired
    EventEntryRepository eventEntryRepository;
    @Autowired
    ParticipantRepository participantRepository;

    /**
     * 指定したイベントIDに紐づく参加者の一覧を取得します.
     *
     * @param eventId イベントID
     * @return 参加者の一覧
     */
    @Override
    public List<Participant> findByEventId(Integer eventId) {
        // 指定したイベントIDからイベント参加者の情報を取得
        List<EventEntry> eventEntryList = eventEntryRepository.findByEventId(eventId);

        // イベント参加者のIDを取得
        // イベント参加者は複数いる可能性がある
        List<Integer> ids = new ArrayList<Integer>();
        for (EventEntry eventEntry : eventEntryList) {
            ids.add(eventEntry.getParticipantId());
        }

        // イベント参加者の情報を検索して返却
        List<Participant> participantList = participantRepository.findAllById(ids);
        return participantList;
    }

    /**
     * イベント参加者の情報をDBに登録します.
     *
     * @param eventEntryForm イベント参加者情報
     */
    @Override
    public void save(EventEntryForm eventEntryForm) {
        // イベント参加者の情報を登録
        Participant participant = new Participant();
        participant.setParticipantName(eventEntryForm.getParticipantName());
        participantRepository.save(participant);

        // イベント参加情報を登録
        EventEntry eventEntry = new EventEntry();
        eventEntry.setEventId(eventEntryForm.getEventId());
        eventEntry.setParticipantId(participant.getParticipantId());
        eventEntryRepository.save(eventEntry);
    }
}

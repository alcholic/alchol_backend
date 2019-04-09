package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.repositories.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyService {
    private final PartyRepository partyRepository;
    private final DiaryService diaryService;

    public PartyService(PartyRepository partyRepository, DiaryService diaryService) {
        this.partyRepository = partyRepository;
        this.diaryService = diaryService;
    }

    @Transactional
    public void insertParty(Party party) {
        partyRepository.insertParty(party);
        partyRepository.insertPartyMember(party.getPartyId(), party.getCreatedBy());
    }

    public void updateParty(Party party) {
        partyRepository.updateParty(party);
    }

    @Transactional
    public void deleteParty(long partyId) {

       partyRepository.deleteParty(partyId);
       partyRepository.deletePartyMember(partyId);

       diaryService.selectDiaryList(0, partyId, null).stream()
                .peek(i -> diaryService.deleteDiary(i.getDiaryId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void insertPartyMember(long partyId, List<User> userList) {
        for(User user : userList) partyRepository.insertPartyMember(partyId, user.getUserId());
    }

    public List<Party> selectPartyByUser(long userId) {
        return partyRepository.selectPartyList(userId);
    }

    public Party selectParty(long partyId, String partyName) {
        return partyRepository.selectParty(partyId, partyName);
    }

    public List<User> selectUserListByParty(long partyId) {
        return partyRepository.selectUserList(partyId);
    }
}

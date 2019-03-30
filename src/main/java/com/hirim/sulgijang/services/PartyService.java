package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.repositories.PartyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartyService {
    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) { this.partyRepository = partyRepository; }

    @Transactional
    public void insert(Party party) {
        partyRepository.insertParty(party);
        partyRepository.insertPartyMember(party.getPartyId(), party.getCreatedBy());
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

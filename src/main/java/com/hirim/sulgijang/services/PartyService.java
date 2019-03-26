package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.repositories.PartyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartyService {
    private final PartyMapper partyMapper;

    public PartyService(PartyMapper partyMapper) { this.partyMapper = partyMapper; }

    public void insert(Party party) {
        partyMapper.insertParty(party);
    }

    @Transactional
    public void insertPartyMember(long partyId, List<User> userList) {
        for(User user : userList) partyMapper.insertPartyMember(partyId, user.getUserId());
    }
}

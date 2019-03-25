package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.repositories.PartyMapper;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    private final PartyMapper partyMapper;

    public PartyService(PartyMapper partyMapper) { this.partyMapper = partyMapper; }

    public void insert(Party party) {
        partyMapper.insertParty(party);
    }
}

package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Party;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PartyMapper {
    void insertParty(Party party);
    void insertPartyMember(long partyId, long userId);
}

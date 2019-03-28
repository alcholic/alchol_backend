package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.PartyMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PartyRepository {
    void insertParty(Party party);
    void insertPartyMember(@Param("partyId") long partyId, @Param("userId") long userId);
    List<Party> selectPartyList(PartyMember partyMember);
    Party selectParty(Party party);
}

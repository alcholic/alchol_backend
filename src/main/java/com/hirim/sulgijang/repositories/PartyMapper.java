package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Party;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PartyMapper {
    void insertParty(Party party);
    void insertPartyMember(@Param("partyId") long partyId, @Param("userId") long userId);
}

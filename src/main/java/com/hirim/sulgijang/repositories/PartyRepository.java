package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.PartyMember;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.param.PartyParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PartyRepository {
    void insertParty(Party party);
    void insertPartyMember(@Param("partyId") long partyId, @Param("userId") long userId);
    void updateParty(Party party);
    void deleteParty(long partyId);
    void deletePartyMember(long partyId);
    List<PartyParam> selectPartyList(long userId);
    Party selectParty(@Param("partyName") String partyName);
}

package com.hirim.sulgijang.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PartyMember extends BaseModel {
    private long partyMemberId;
    private List<User> userList;
    private long partyId;

    public PartyMember(List<User> userList, long partyId) {
        this.userList = userList;
        this.partyId = partyId;
    }
}

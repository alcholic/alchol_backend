package com.hirim.sulgijang.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PartyMember extends BaseModel {
    private long partyMemberId;
    private User user;
    private long partyId;

    public PartyMember(User user) {
        this.user = user;
    }

    public PartyMember(User user, long partyId) {
        this.user = user;
        this.partyId = partyId;
    }
}

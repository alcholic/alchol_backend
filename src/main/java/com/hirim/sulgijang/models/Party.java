package com.hirim.sulgijang.models;

import lombok.Data;

import java.util.List;

@Data
public class Party extends BaseModel{
    private long partyId;
    private String partyName;
    private List<User> userList;

    public Party(String partyName, long createdBy) {
        this.partyName = partyName;
        this.createdBy = createdBy;
    }
}

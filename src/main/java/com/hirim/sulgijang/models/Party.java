package com.hirim.sulgijang.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party extends BaseModel{
    private long partyId;
    private String partyName;

    public Party(String partyName, long createdBy) {
        this.partyName = partyName;
        this.createdBy = createdBy;
    }
}

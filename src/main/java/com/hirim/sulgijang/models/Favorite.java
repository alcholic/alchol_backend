package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class Favorite extends BaseModel{
    private long favoriteId;
    private long partyId;
    private long userId;
}

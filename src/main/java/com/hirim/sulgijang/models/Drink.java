package com.hirim.sulgijang.models;

import lombok.Data;

@Data
public class Drink extends BaseModel{
    private long drinkId;
    private long diaryCotentId;
    private String drinkName;
    private long amount;
}

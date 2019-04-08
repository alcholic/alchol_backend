package com.hirim.sulgijang.models;

import lombok.Data;

import java.util.List;

@Data
public class DrinkParam {
    private long drinkId;
    private long diaryContentId;
    private List<Drink> drinkList;
}

package com.hirim.sulgijang.models;

import lombok.Data;

import java.util.List;

@Data
public class DiaryContent extends BaseModel{
    private long diaryContentId;
    private long diaryId;
    private long placeId;
    private String snack;
    private long depth;
    private List<Drink> drinkList;
}

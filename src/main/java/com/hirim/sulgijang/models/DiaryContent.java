package com.hirim.sulgijang.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class DiaryContent extends BaseModel{
    private long diaryContentId;
    private long diaryId;
    private long placeId;
    private String snack;
    private int level;
    private List<Drink> drinkList;
}

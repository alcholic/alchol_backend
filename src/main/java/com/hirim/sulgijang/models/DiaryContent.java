package com.hirim.sulgijang.models;

import lombok.Data;

import java.util.List;

@Data
public class DiaryContent extends BaseModel{
    private long diaryContentId;
    private long diaryId;
    private String placeName;
    private String summary;
    private String snack;
    private long depth;
    private List<Drink> drinkList;
}

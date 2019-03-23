package com.hirim.sulgijang.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Diary extends BaseModel{
    private long diaryId;
    private long groupId;
    private String diaryName;
    private String snack;
    private Timestamp meetingDate;
    private int level;
    private List<Drink> drinkList;
}

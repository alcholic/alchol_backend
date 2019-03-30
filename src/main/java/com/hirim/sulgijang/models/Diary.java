package com.hirim.sulgijang.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class Diary extends BaseModel{
    private long diaryId;
    private long partyId;
    private String diaryName;
    private Timestamp meetingDate;
    private long loser;
}

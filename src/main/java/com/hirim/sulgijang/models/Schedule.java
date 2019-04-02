package com.hirim.sulgijang.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule extends BaseModel {
    private long scheduleId;
    private String contents;
    private Timestamp scheduleAt;
}

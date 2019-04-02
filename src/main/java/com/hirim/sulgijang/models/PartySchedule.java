package com.hirim.sulgijang.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartySchedule extends BaseModel {
    private long id;
    private long scheduleId;
    private long parytId;
}

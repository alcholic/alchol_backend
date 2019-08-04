package com.hirim.sulgijang.models.param;

import com.hirim.sulgijang.models.BaseModel;
import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.PartyMember;
import lombok.Data;

import java.util.List;

@Data
public class PartyParam extends BaseModel {
    private long partyId;
    private String partyName;
    private List<PartyMember> partyMemberList;
    private List<Diary> diaryList;
}


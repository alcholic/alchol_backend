package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiaryRepository {
    void insertDiary(Diary diary);
    void insertDiaryContent(DiaryContent diaryContent);
    void updateDiary(Diary diary);
    void updateDiaryContent(DiaryContent diaryContent);
    void deleteDiary(long diaryId);
    void deleteDiaryContent(long diaryContentId);
    List<Diary> selectDiaryListByParty(@Param("partyId") long partyId, @Param("privateYn") String privateYn);
    List<DiaryContent> selectDiaryContentList(@Param("diaryId") long diaryId, @Param("diaryContentId") long diaryContentId);
}

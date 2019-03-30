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
    List<Diary> selectDiaryList(long partyId);
    List<DiaryContent> selectDiaryContentList(long diaryId);
    Diary selectDiary(@Param("diaryId") long diaryId, @Param("diaryName") String diaryName);
}

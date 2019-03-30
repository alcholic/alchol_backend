package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiaryRepository {
    void insertDiary(Diary diary);
    List<Diary> selectDiaryList(long partyId);
    Diary selectDiary(@Param("diaryId") long diaryId, @Param("diaryName") String diaryName);
}

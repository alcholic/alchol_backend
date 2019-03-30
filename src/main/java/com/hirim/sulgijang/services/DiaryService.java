package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.repositories.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }


    public void insertDiary(Diary diary) {
        diaryRepository.insertDiary(diary);
    }

    public List<Diary> selectDiaryList(long diaryId) {
        return diaryRepository.selectDiaryList(diaryId);
    }

    public Diary selectDiary(long diaryId, String diaryName) {
        return diaryRepository.selectDiary(diaryId, diaryName);
    }
}
